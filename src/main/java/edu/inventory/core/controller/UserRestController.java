package edu.inventory.core.controller;

import edu.inventory.core.entity.User;
import edu.inventory.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    //Metodo registrar
    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User user){

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    //Metodo Loggear
    @PostMapping("/signin")
    public ResponseEntity<User> loginUser(@RequestBody User user){
        List<User> userList = userRepository.findAll();
        for(User userTemp: userList){
            if(userTemp.getUsername().equals(user.getUsername())){
                if(userTemp.getPassword().equals(user.getPassword())){
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(user);
    }

}
