package com.fancyliu.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public UserDTO create(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable Long id, HttpServletRequest request) {

        User user = (User) request.getAttribute("user");

        if (user == null || !id.equals(user.getId())) {
            throw new RuntimeException("身份认证信息异常,获取用户信息失败");
        }

        return userService.get(id);
    }


    @GetMapping("/list")
    public List list(String name) {

        List<UserDTO> list = userService.findByName(name);

        return list;
    }
}
