package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.services;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);
}
