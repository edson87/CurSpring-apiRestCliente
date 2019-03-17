package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.services;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.controllers.ClienteRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadFileService implements IUploadFileService {
    private final Logger log = LoggerFactory.getLogger(UploadFileService.class);
    private final static String DIRECTORIO_UPLOAD = "uploads";
    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Resource recurso = null;
        Path rutaArchivo = getPath(nombreFoto);

        recurso = new UrlResource(rutaArchivo.toUri());


        if (!recurso.exists() && !recurso.isReadable()){
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("noUser.png").toAbsolutePath();

            recurso = new UrlResource(rutaArchivo.toUri());

            log.error("No existe el la imagen: "+nombreFoto);
        }
        return recurso;
    }

    @Override
    public String copiar(MultipartFile archivo) throws IOException {
        String nombreArchivo = UUID.randomUUID()+"_"+archivo.getOriginalFilename().replace(" ","");
        Path rutaArchivo = getPath(nombreArchivo);
        log.info("Ruta del archivo: "+rutaArchivo.toString());

        Files.copy(archivo.getInputStream(),rutaArchivo);

        return nombreArchivo;
    }

    @Override
    public boolean eliminar(String nombreFoto) {
        if (nombreFoto != null && nombreFoto.length() > 0){
            Path rutaFotoAnterior = getPath(nombreFoto);
            File archivoFotoAnterior = rutaFotoAnterior.toFile();

            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                archivoFotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        Path rutaArchivo = Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
        return rutaArchivo;
    }
}
