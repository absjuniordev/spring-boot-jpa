package dio.spring.security.jwt.controller;



import dio.spring.security.jwt.model.AppUser;
import dio.spring.security.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping
    public AppUser postUser(@RequestBody AppUser user){
        service.createUser(user);

        return user;
    }
}