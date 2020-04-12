package com.fancyliu.security.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO create(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
//        user.setUsername(null);
        userRepository.save(user);
        userDTO.setId(user.getId());

        return userDTO;
    }

    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    public void delete(Long id) {

    }

    public UserDTO get(Long id) {
        return this.userRepository.findById(id).get().buildUserDTO();
    }

    public List<UserDTO> findByName(String name) {
        return null;
    }

    public UserDTO findById(Long id) {
        return this.userRepository.findById(id).get().buildUserDTO();
    }
}
