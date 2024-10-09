package dio.spring.security.jwt.controller;

import dio.spring.security.jwt.dtos.Login;
import dio.spring.security.jwt.dtos.Sessao;
import dio.spring.security.jwt.model.AppUser;
import dio.spring.security.jwt.repository.UserRepository;
import dio.spring.security.jwt.security.JWTCreator;
import dio.spring.security.jwt.security.JWTObject;
import dio.spring.security.jwt.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
        AppUser appUser = repository.findByUsername(login.getUsername());
        if(appUser!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), appUser.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(appUser.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION  )));
            jwtObject.setRoles(appUser.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}