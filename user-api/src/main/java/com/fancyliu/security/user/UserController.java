package com.fancyliu.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


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

        // 演示sql 注入,传入参数 ' or 1=1 or name ='
        String sql ="SELECT id, name FROM user WHERE name = '" + name +"'";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
