package com.fancyliu.security.user;

import java.util.List;

public interface UserService {

    UserDTO create(UserDTO user);

    UserDTO update(UserDTO user);

    void delete(Long id);

    UserDTO get(Long id);

    List<UserDTO> findByName(String name);

    UserDTO findById(Long id);
}
