package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.services;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.dao.IClienteDao;
import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.dao.IUsuarioDao;
import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Cliente;
import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    public IUsuarioDao usuarioDao;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }
}
