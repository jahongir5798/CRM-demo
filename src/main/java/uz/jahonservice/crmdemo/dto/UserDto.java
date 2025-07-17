package uz.jahonservice.crmdemo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.jahonservice.crmdemo.entity.enums.RoleEnum;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private UUID id;

    @Column(unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(nullable = false)
    private String phoneNumber;

    private String phoneNumberSecond;

}
