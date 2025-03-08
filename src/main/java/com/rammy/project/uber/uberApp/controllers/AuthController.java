package com.rammy.project.uber.uberApp.controllers;


import com.rammy.project.uber.uberApp.dto.*;
import com.rammy.project.uber.uberApp.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto inputUser){
        return new ResponseEntity<>(authService.signup(inputUser), HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/onBoardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnBoardDriveDTO onBoardDriveDTO){
        return new ResponseEntity<>(authService.onBoardNewDriver(userId, onBoardDriveDTO.getVehicleId()),HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO
    , HttpServletResponse httpServletResponse) {
        String[] tokens = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        Cookie cookie = new Cookie("token",tokens[1]);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request){
        String refreshToken= Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getName)
                .orElseThrow(()->new AuthenticationServiceException("Refresh token not found inside the Cookies"));
        String accessToken = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(new LoginResponseDTO(accessToken));
    }

}
