package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.dao;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Cliente;
import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente,Long> {

    @Query("from Region")
    public List<Region> findAllRegiones();
}
