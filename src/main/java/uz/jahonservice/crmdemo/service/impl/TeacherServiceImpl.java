package uz.jahonservice.crmdemo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.jahonservice.crmdemo.dto.TeacherCreateDto;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;
import uz.jahonservice.crmdemo.entity.Teacher;
import uz.jahonservice.crmdemo.entity.TeacherSubjects;
import uz.jahonservice.crmdemo.entity.Users;
import uz.jahonservice.crmdemo.entity.enums.RoleEnum;
import uz.jahonservice.crmdemo.exception.MyException;
import uz.jahonservice.crmdemo.repository.TeacherRepository;
import uz.jahonservice.crmdemo.repository.TeacherSubjectsRepository;
import uz.jahonservice.crmdemo.repository.UserRepository;
import uz.jahonservice.crmdemo.service.TeacherService;
import uz.jahonservice.crmdemo.service.mapper.UserMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TeacherSubjectsRepository teacherSubjectsRepository;

    @Override
    public ApiResponse<UserDto> createTeacher(TeacherCreateDto teacherCreateDto) {
        try {
            Users users = userMapper.TeacherCreateDtoToUsers(teacherCreateDto);
            users.setRole(RoleEnum.TEACHER);
            String password = users.getPassword();
            users.setPassword(passwordEncoder.encode(password));


            Users savedUser = userRepository.save(users);

            Teacher teacher = new Teacher();
            teacher.setUsers(savedUser);
            Teacher savedTeacher = teacherRepository.save(teacher);

            teacherCreateDto.getSubjects().stream().forEach(subject -> {
                TeacherSubjects teacherSubjects = new TeacherSubjects();
                teacherSubjects.setTeacher(savedTeacher);
                teacherSubjects.setSubjectName(subject);
                teacherSubjectsRepository.save(teacherSubjects);
            });

            UserDto userDto = userMapper.toUserDto(savedUser);
            userDto.setPassword(password);

            return ApiResponse.<UserDto>builder()
                    .code(0)
                    .success(true)
                    .message("Teacher created successfully")
                    .result(userDto)
                    .build();
        } catch (Exception e) {
            throw new MyException("teacher create failed" + e.getMessage());
        }
    }

    @Override
    public ApiResponse<UserDto> changePassword(UUID id, String oldPassword, String newPassword) {
        try {

            Users users = userRepository.findById(id).orElseThrow(() -> new MyException("user not found"));
            if (!passwordEncoder.matches(oldPassword, users.getPassword())) throw new MyException("Password incorrect");
            users.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(users);

            return ApiResponse.<UserDto>builder()
                    .code(0)
                    .success(true)
                    .message("Password changed successfully")
                    .result(userMapper.toUserDto(users))
                    .build();
        }catch (Exception e){
            throw new MyException("change password failed " + e.getMessage());
        }
    }
}
