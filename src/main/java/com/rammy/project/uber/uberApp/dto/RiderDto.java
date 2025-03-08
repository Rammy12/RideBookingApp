package com.rammy.project.uber.uberApp.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiderDto {
    private Long id;
    private UserDto user;
    private Double rating;
}
