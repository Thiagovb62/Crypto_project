package com.example.cryptokeeper.Controller;

import com.example.cryptokeeper.Entity.User;
import com.example.cryptokeeper.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    @CrossOrigin
    public User save(@RequestBody User user) {
        return userService.save(user);
    }
    @GetMapping("/find")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
