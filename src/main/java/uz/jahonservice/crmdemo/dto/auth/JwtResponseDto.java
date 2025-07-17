package uz.jahonservice.crmdemo.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponseDto {

    private String accessToken;

    private String refreshToken;

}
