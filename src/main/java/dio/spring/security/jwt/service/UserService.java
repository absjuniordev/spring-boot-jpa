package dio.spring.security.jwt.service;

import dio.spring.security.jwt.model.AppUser;
import dio.spring.security.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public
class UserService {
    @Autowired
    private
    UserRepository repository;
    @Autowired
    private
    PasswordEncoder encoder;

    public void createUser(AppUser user){
        String pass = user.getPassword();
        user.setPassword(encoder.encode(pass));
        repository.save(user);
    }
}
