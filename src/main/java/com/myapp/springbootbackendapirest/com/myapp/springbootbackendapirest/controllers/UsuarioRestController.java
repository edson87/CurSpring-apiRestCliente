package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.controllers;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Usuario;
import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiUsuario")
public class UsuarioRestController {

    @Autowired(required = true)
    public UsuarioServiceImpl usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> Index(){
        return usuarioService.findAll();
    }

}
