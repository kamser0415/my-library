package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
//
//    private final UserServiceV1 userServiceV1;
//
//    public UserController(UserServiceV1 userServiceV1) {
//        this.userServiceV1 = userServiceV1;
//    }

    private final UserServiceV2 userServiceV1;

    public UserController(UserServiceV2 userServiceV1) {
        this.userServiceV1 = userServiceV1;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
//        users.add(new User(request.getName(), request.getAge()));
        if (request.getAge() < 0) {
            throw new IllegalArgumentException("나이는 0살 이상만 가능합니다.");
        }

        userServiceV1.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUser() {
//        ArrayList<UserResponse> response = new ArrayList<>();
//        for (int i = 0; i < users.size(); i++) {
//            //            response.add(new UserResponse(i + 1L, user.getName(), user.getAge()));
//            response.add(new UserResponse(i + 1L, users.get(i)));
//        }
//        return response;
        //language=MySQL
        return userServiceV1.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userServiceV1.updateUSer(request);
    }

    @DeleteMapping("/user")
    public void updateUser(@RequestParam String name) {
        userServiceV1.deleteUser(name);
    }



    public static class UserUpdateRequest {
        private long id;
        private String name;

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
