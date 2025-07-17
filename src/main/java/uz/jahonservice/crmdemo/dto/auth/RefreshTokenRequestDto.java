package uz.jahonservice.crmdemo.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenRequestDto {

    @NotNull(message = "token cannot be null")
    private String token;

}
