package com.fancyliu.security.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private String username;

    private String password;
}
