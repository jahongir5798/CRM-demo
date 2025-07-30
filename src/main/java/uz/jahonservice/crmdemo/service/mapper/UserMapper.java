package uz.jahonservice.crmdemo.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.jahonservice.crmdemo.dto.TeacherCreateDto;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.entity.Users;
import uz.jahonservice.crmdemo.service.validation.UsernameAndPasswordMaker;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UsernameAndPasswordMaker usernameAndPasswordMaker;



    public UserDto toUserDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUserName());
//        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPhoneNumberSecond(user.getPhoneNumberSecond());
        return userDto;
    }

    public Users TeacherCreateDtoToUsers(TeacherCreateDto teacherCreateDto) {
        Users users = new Users();
        users.setFirstName(teacherCreateDto.getFirstName());
        users.setLastName(teacherCreateDto.getLastName());
        users.setPhoneNumber(teacherCreateDto.getPhoneNumber());
        users.setPhoneNumberSecond(teacherCreateDto.getPhoneNumberSecond());
        users.setUserName(usernameAndPasswordMaker.UsernameMaker());
        users.setPassword(usernameAndPasswordMaker.PasswordMaker());

        return users;
    }

}
