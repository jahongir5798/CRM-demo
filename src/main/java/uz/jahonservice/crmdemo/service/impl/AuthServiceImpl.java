package uz.jahonservice.crmdemo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.auth.AuthRequestDto;
import uz.jahonservice.crmdemo.dto.auth.JwtResponseDto;
import uz.jahonservice.crmdemo.dto.auth.RefreshTokenRequestDto;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;
import uz.jahonservice.crmdemo.entity.RefreshToken;
import uz.jahonservice.crmdemo.entity.Users;
import uz.jahonservice.crmdemo.exception.MyException;
import uz.jahonservice.crmdemo.repository.UserRepository;
import uz.jahonservice.crmdemo.service.AuthService;
import uz.jahonservice.crmdemo.service.auth.JwtService;
import uz.jahonservice.crmdemo.service.auth.RefreshTokenService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;


    @Override
    public ApiResponse<UserDto> registration(UserDto userDto) {
        try {
            Users users = new Users();
            users.setUserName(userDto.getUserName());
            users.setPassword(passwordEncoder.encode(userDto.getPassword()));
            users.setFirstName(userDto.getFirstName());
            users.setLastName(userDto.getLastName());
            users.setRole(userDto.getRole());
            users.setPhoneNumber(userDto.getPhoneNumber());
            users.setPhoneNumberSecond(userDto.getPhoneNumberSecond());
            userRepository.save(users);

            return ApiResponse.<UserDto>builder()
                    .code(0)
                    .message("Registration successful")
                    .success(true)
                    .result(userDto)
                    .build();
        } catch (Exception e) {
            throw new MyException("registration failed" + e.getMessage());
        }
    }

    @Override
    public ApiResponse<JwtResponseDto> authenticateAndGetToken(AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDto.getUsername());
            JwtResponseDto jwtResponseDto = new JwtResponseDto();
            jwtResponseDto.setAccessToken(jwtService.GenerateToken(authRequestDto.getUsername()));
            jwtResponseDto.setRefreshToken(refreshToken.getToken());
            return ApiResponse.<JwtResponseDto>builder()
                    .code(0)
                    .message("successfully logged in")
                    .success(true)
                    .result(jwtResponseDto)
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @Override
    public ApiResponse<JwtResponseDto> refreshToken(RefreshTokenRequestDto refreshTokenRequestDTO) {
        JwtResponseDto jwtResponseDto = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsers)
                .map(userInfo -> {
                    String accessToken = jwtService.GenerateToken(userInfo.getUserName());
                    return JwtResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
        return ApiResponse.<JwtResponseDto>builder()
                .code(0)
                .message("successfully refreshed token")
                .success(true)
                .result(jwtResponseDto)
                .build();
    }


}
