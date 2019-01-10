package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.dao;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario,Long> {
}
