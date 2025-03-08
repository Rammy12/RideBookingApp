package com.rammy.project.uber.uberApp.dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    private String name;
    private String email;
    private String password;
}
