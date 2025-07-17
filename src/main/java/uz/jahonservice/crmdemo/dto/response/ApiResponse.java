package uz.jahonservice.crmdemo.dto.response;

import lombok.*;
import uz.jahonservice.crmdemo.dto.ErrorDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private int code;

    private String message;

    private boolean success;

    private T result;

    private List<ErrorDto> errorList;

}
