package uz.jahonservice.crmdemo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherCreateDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String phoneNumberSecond;

    private List<String> subjects;

}
