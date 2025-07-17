package uz.jahonservice.crmdemo.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;
import uz.jahonservice.crmdemo.entity.Users;
import uz.jahonservice.crmdemo.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("get-all")
    public ApiResponse<List<Users>> findAll() {
        List<Users> users = userRepository.findAll();
        return ApiResponse.<List<Users>>builder()
                .code(0)
                .message("successfully")
                .success(true)
                .result(users)
                .build();
    }

}
