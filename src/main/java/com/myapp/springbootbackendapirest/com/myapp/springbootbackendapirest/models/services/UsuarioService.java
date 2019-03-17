package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.services;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.dao.IUsuarioDao;
import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Usuario;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.slf4j.Logger;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {
    @Autowired
    IUsuarioDao usuarioDao;

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null){
            logger.error("Error en el login no existe el usuario ingresado "+username);
            throw new UsernameNotFoundException("Error en el login no existe el usuario ingresado '"+username+"'");
        }
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.error("Error: "+authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(),usuario.getPassword(),usuario.getEnabled(),true,true,true,authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
}
