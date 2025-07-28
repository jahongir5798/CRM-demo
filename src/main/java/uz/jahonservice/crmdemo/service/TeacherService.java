package uz.jahonservice.crmdemo.service;

import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.UserForResponse;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;

public interface TeacherService {
    ApiResponse<UserForResponse> createTeacher(UserDto userDto);
}
