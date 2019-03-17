package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.auth;

import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.entity.Usuario;
import com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.models.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private IUsuarioService usuarioService;


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        Map<String,Object> info = new HashMap<>();
        info.put("info_adicional","hola que tal".concat(authentication.getName()));
        info.put("Nombre_Usuario", usuario.getId()+" : "+usuario.getUsername()+" : "+usuario.getEnabled());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
