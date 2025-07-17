package uz.jahonservice.crmdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jahonservice.crmdemo.config.MyUserDetailsService;
import uz.jahonservice.crmdemo.dto.UserDto;
import uz.jahonservice.crmdemo.dto.authDto.SignInDto;
import uz.jahonservice.crmdemo.dto.response.ApiResponse;
import uz.jahonservice.crmdemo.dto.response.JwtResponse;
import uz.jahonservice.crmdemo.entity.Users;
import uz.jahonservice.crmdemo.repository.UserRepository;
import uz.jahonservice.crmdemo.service.auth.JWTService;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @PostMapping("sign-up")
    public ApiResponse<Void> registration(@RequestBody UserDto userDto) {
        Users users = new Users();
        users.setUserName(userDto.getUserName());
        users.setPassword(passwordEncoder.encode(userDto.getPassword()));
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setRole(userDto.getRole());
        users.setPhoneNumber(userDto.getPhoneNumber());
        users.setPhoneNumberSecond(userDto.getPhoneNumberSecond());
        userRepository.save(users);

        return ApiResponse.<Void>builder()
                .code(0)
                .message("Registration successful")
                .success(true)
                .build();
    }

    @PostMapping("sign-in")
    public ApiResponse<String> login(@RequestBody SignInDto signInDto) {
        Users user = userRepository.findByUserName(signInDto.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        try {
            if (passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
//                JwtResponse jwtResponse = new JwtResponse(jwtService.generateAccessToken(signInDto.getUsername()));
                String jwtResponse = jwtService.generateAccessToken(signInDto.getUsername());
                return ApiResponse.<String>builder()
                        .code(0)
                        .message("Login successful")
                        .success(true)
                        .result(jwtResponse)
                        .build();
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return null;
    }
}

