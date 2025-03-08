package com.rammy.project.uber.uberApp.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointDTO {
    private double[] coordinates;
    private String type = "Point";

    public PointDTO(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
