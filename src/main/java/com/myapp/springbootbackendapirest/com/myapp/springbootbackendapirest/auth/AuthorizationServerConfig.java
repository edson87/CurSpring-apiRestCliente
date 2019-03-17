package com.myapp.springbootbackendapirest.com.myapp.springbootbackendapirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

//Auth O2
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private InfoAdicionalToken infoAdicionalToken;

    //autentificacion al iniciar sesion
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");
    }

    //autentifica al cliente  password,permisos,autrefresh de token,tiempo de caducidad del token y el refresh
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("angularapp")
                .secret(passwordEncoder.encode("123456"))
                .scopes("read","write")
                .authorizedGrantTypes("password","refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600);
    }

    //autentifica desde el login la utentificacion etc..
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken,accessTokenConverter()));
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(tokenEnhancerChain);
    }

    @Bean
    public JwtTokenStore tokenStore() {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(accessTokenConverter());
        return jwtTokenStore;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //se puede quitar desde jwtConfig
        jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);
        jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
        //hasta aqui
        return jwtAccessTokenConverter;
    }
}
