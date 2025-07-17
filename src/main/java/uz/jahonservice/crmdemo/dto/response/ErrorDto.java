package uz.jahonservice.crmdemo.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class ErrorDto {

    private String field;

    private String message;


}
