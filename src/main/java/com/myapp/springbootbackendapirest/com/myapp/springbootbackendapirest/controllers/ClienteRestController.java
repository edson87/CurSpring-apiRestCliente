package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.controllers;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Cliente;
import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.services.IClienteService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;
    Cliente cliente;
    private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page,4);
        return clienteService.findAll(pageable);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = clienteService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null){
            response.put("mensaje","El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos!"));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            /*List<String> errores = new ArrayList<>();
            for (FieldError err : result.getFieldErrors()){
                errores.add("El campo '"+err.getField()+"' "+err.getDefaultMessage());
            }*/
            List<String> errores = result.getFieldErrors()
                    .stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors",errores);
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            clienteNew = clienteService.save(cliente);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente a sido creado con exito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, @PathVariable Long id, BindingResult result) {
        Cliente clienteActual = clienteService.findById(id);
        Cliente clienteUpdated = null;

        Map<String, Object> response = new HashMap<>();

         if (result.hasErrors()){
             List<String> errores = result.getFieldErrors()
                     .stream()
                     .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                     .collect(Collectors.toList());
             response.put("errors",errores);

             return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
         }


         if (clienteActual == null){
            response.put("mensaje","No se  puede actualizar el cliente ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmail(cliente.getEmail());
            //clienteActual.setCreateAt(cliente.getCreateAt());

            clienteUpdated = clienteService.save(clienteActual);
        }catch(DataAccessException e){
            response.put("mensaje","Error al realizar la actualizacion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente se actualizo con exito");
        response.put("cliente",clienteUpdated);
        return new ResponseEntity< Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        Map<String, Object> response = new HashMap<>();
        try{
            String nombreFotoAnterior = cliente.getFoto();
            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();

                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }
            }
            clienteService.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje","No se pudo eliminar al cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","Se elimino con exito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") Long id){

            Map<String, Object> response = new HashMap<>();
            Cliente cliente = clienteService.findById(id);
            if (!archivo.isEmpty()){
                String nombreArchivo = UUID.randomUUID()+"_"+archivo.getOriginalFilename().replace(" ","");
                Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
                log.info(rutaArchivo.toString());
                try{
                    Files.copy(archivo.getInputStream(),rutaArchivo);

                }catch (IOException e){
                    response.put("error","No se pudo subir la imagen: "+nombreArchivo);
                    response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                    return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
                }

                String nombreFotoAnterior = cliente.getFoto();
                if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
                    Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                    File archivoFotoAnterior = rutaFotoAnterior.toFile();

                    if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                        archivoFotoAnterior.delete();
                    }
                }

                cliente.setFoto(nombreArchivo);
                clienteService.save(cliente);

                response.put("cliente",cliente);
                response.put("mensaje","Se subio correctamente la foto: "+nombreArchivo);
            }
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Resource recurso = null;
        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();

        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (!recurso.exists() && !recurso.isReadable()){
            throw new RuntimeException("No existe el la imagen: "+nombreFoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso.getFilename()+"\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
}
