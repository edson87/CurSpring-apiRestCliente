package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.dao;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends JpaRepository<Cliente,Long> {
}
