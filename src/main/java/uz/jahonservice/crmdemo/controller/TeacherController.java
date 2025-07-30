package uz.jahonservice.crmdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.jahonservice.crmdemo.dto.TeacherCreateDto;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;
import uz.jahonservice.crmdemo.service.TeacherService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/teacher/")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("create")
    public ApiResponse<UserDto> createTeacher
    (
            @RequestBody TeacherCreateDto teacherCreateDto
    ) {
        return teacherService.createTeacher(teacherCreateDto);
    }

    @PatchMapping("change-info")
    public ApiResponse<UserDto> changeTeacher(@RequestBody UserDto userDto) {
        return null;
    }

    @PatchMapping("change-password/{userid}")
    public ApiResponse<UserDto> changeTeacherPassword(
            @PathVariable UUID userid,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        return teacherService.changePassword(userid, oldPassword, newPassword);
    }


}
