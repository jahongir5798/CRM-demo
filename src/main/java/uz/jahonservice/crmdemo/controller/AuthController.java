package uz.jahonservice.crmdemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.auth.AuthRequestDto;
import uz.jahonservice.crmdemo.dto.auth.JwtResponseDto;
import uz.jahonservice.crmdemo.dto.auth.RefreshTokenRequestDto;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;
import uz.jahonservice.crmdemo.service.AuthService;

@Slf4j
@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("sign-up")
    public ApiResponse<UserDto> registration(@RequestBody @Valid  UserDto userDto) {
        log.info("auth controller registration method invoked");
        ApiResponse<UserDto> registration = authService.registration(userDto);
        log.info("auth controller registration method completed");
        return registration;
    }

    @PostMapping("sign-in")
    public ApiResponse<JwtResponseDto> AuthenticateAndGetToken(@RequestBody @Valid  AuthRequestDto authRequestDto) {
        log.info("auth controller authentication method invoked");
        ApiResponse<JwtResponseDto> jwtResponseDto = authService.authenticateAndGetToken(authRequestDto);
        log.info("auth controller authentication method completed");
        return jwtResponseDto;
    }

    @PostMapping("/refreshToken")
    public ApiResponse<JwtResponseDto> refreshToken(@RequestBody @Valid  RefreshTokenRequestDto refreshTokenRequestDTO) {
        log.info("auth controller refresh token method invoked");
        ApiResponse<JwtResponseDto> jwtResponseDto = authService.refreshToken(refreshTokenRequestDTO);
        log.info("auth controller refresh token method completed");
        return jwtResponseDto;
    }
}

