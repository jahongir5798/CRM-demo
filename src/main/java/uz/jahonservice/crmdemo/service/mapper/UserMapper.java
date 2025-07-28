package uz.jahonservice.crmdemo.service.mapper;

import org.springframework.stereotype.Component;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.entity.Users;

@Component
public class UserMapper {

    public UserDto toUserDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPhoneNumberSecond(user.getPhoneNumberSecond());
        return userDto;
    }

}
