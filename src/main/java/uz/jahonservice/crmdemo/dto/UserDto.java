package uz.jahonservice.crmdemo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "username cannot be null")
    private String username;

    @NotNull(message = "password cannot be null")
    private String password;

    @NotNull(message = "first name cannot be null")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "role cannot be null")
    private RoleEnum role;

    @Column(nullable = false)
    @NotNull(message = "at least one phone number")
    private String phoneNumber;

    private String phoneNumberSecond;

}
