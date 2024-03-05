package com.group.libraryapp.service.user;

import com.group.libraryapp.controller.user.UserController;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void updateUser(UserController.UserUpdateRequest request) {
        if (!userJdbcRepository.isExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.updateUserName(request.getId(), request.getName());

    }

    public void deleteUser(String name) {
        boolean isUserNotExist = userJdbcRepository.isUserNotExist(name);
        if (isUserNotExist) {
            throw new IllegalArgumentException("오류");
        }
        userJdbcRepository.deleteUserByName(name);
    }

    public void saveUser(String name, Integer age) {
        userJdbcRepository.saveUser(name, age);
    }

    public List<UserResponse> findByAll() {
        return userJdbcRepository.findByAll();
    }
}
