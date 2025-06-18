package com.crm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;
    private String email;
    private String dateOfJoining;
    private Double salary;
    private String managerName;
    private String departmentName;
    private List<ReviewDto> performanceReviews;
    private List<ProjectDto> projects;

}
