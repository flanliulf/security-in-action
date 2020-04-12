package com.fancyliu.security.user;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaSpecificationExecutor<User>, CrudRepository<User, Long> {

    List<UserDTO> findByName(String name);

    User findByUsername(String username);
}
