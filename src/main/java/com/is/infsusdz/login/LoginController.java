package com.is.infsusdz.login;

import com.is.infsusdz.users.CarFindUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    LoginService ls = new LoginService();

    @PostMapping(path="/api/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity verifyUser(@RequestBody LoginData userLogin) {
        return ls.verify(userLogin);
    }

    @PostMapping(path="/api/signup")
    public ResponseEntity signupUser(@RequestBody SignupData userSignup) {
        return ls.signup(userSignup);
    }
}
