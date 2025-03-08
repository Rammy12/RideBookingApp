package com.rammy.project.uber.uberApp.services;

import com.rammy.project.uber.uberApp.dto.DriverDto;
import com.rammy.project.uber.uberApp.dto.LoginResponseDTO;
import com.rammy.project.uber.uberApp.dto.SignupDto;
import com.rammy.project.uber.uberApp.dto.UserDto;

public interface AuthService {
    String[] login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onBoardNewDriver(Long userId,String vehicleId);

    String refreshToken(String refreshToken);
}
