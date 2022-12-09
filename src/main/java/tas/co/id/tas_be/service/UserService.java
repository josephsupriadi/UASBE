/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tas.co.id.tas_be.service;

import java.util.List;
import lombok.AllArgsConstructor;
import tas.co.id.tas_be.model.User;
import tas.co.id.tas_be.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author MSI-JO
 */
@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "User id not found"
                        ));
    }

    public User create(User user) {
        user.getEmployee().setUser(user);
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    public User delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
}
