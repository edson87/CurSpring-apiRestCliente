package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.services;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> findAll();
}
