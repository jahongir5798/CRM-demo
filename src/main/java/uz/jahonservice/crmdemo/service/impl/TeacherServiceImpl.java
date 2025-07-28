package uz.jahonservice.crmdemo.service.impl;

import org.springframework.stereotype.Service;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.UserForResponse;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;
import uz.jahonservice.crmdemo.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Override
    public ApiResponse<UserForResponse> createTeacher(UserDto userDto) {
        return null;
    }
}
