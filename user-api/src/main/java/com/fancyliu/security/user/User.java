package com.fancyliu.security.user;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotBlank(message = "用户名不能为 null")
    @Column(unique = true)
    private String username;

    private String password;

    private String permissions;

    public UserDTO buildUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        return userDTO;
    }

    public boolean hasPermission(String method) {

        boolean result = false;
        if (StringUtils.endsWithIgnoreCase("get", method)){
            result = StringUtils.contains(permissions, "r");
        } else {
            result = StringUtils.contains(permissions, "w");
        }

        return result;
    }
}
