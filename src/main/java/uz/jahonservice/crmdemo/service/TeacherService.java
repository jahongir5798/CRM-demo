package uz.jahonservice.crmdemo.service;

import uz.jahonservice.crmdemo.dto.TeacherCreateDto;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;

import java.util.UUID;

public interface TeacherService {

     ApiResponse<UserDto> createTeacher(TeacherCreateDto teacherCreateDto);

    ApiResponse<UserDto> changePassword(UUID id, String oldPassword, String newPassword);
}
