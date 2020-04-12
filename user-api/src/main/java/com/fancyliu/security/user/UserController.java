package com.fancyliu.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("")
    public User create(@RequestBody User user) {
        return user;
    }

    @PutMapping("")
    public User update(@RequestBody User user) {
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return new User();
    }


    @GetMapping("/list")
    public List list(String name) {
        String sql ="SELECT id, name FROM user WHERE name = '" + name +"'";
        userRepository.findByName(name);

        List<User> list = userRepository.findByName(name);
        return list;
    }
}
