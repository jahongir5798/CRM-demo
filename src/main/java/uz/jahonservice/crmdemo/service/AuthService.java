package uz.jahonservice.crmdemo.service;

import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.auth.AuthRequestDto;
import uz.jahonservice.crmdemo.dto.auth.JwtResponseDto;
import uz.jahonservice.crmdemo.dto.auth.RefreshTokenRequestDto;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;

public interface AuthService {
    ApiResponse<UserDto> registration(UserDto userDto);

    ApiResponse<JwtResponseDto> authenticateAndGetToken(AuthRequestDto authRequestDto);

    ApiResponse<JwtResponseDto> refreshToken(RefreshTokenRequestDto refreshTokenRequestDTO);

}
