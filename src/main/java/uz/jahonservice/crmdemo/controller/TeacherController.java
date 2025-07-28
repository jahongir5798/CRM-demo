package uz.jahonservice.crmdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.UserForResponse;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;
import uz.jahonservice.crmdemo.service.TeacherService;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    @RequestMapping("create-teacher")
//    @PreAuthorize('hasRole("ADMIN")')
    public ApiResponse<UserForResponse> createTeacher(@RequestBody UserDto userDto) {
        return teacherService.createTeacher(userDto);
    }



}
