package com.crm.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.crm.dto.EmployeeDto;
import com.crm.dto.ProjectDto;
import com.crm.dto.ReviewDto;
import com.crm.entity.Employee;
import com.crm.entity.EmployeeProject;
import com.crm.entity.PerformanceReview;
import com.crm.entity.Project;

public class EmployeeMapper {

    private static final Logger LOG = LogManager.getLogger(EmployeeMapper.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static EmployeeDto mapToEmployeeDto(Employee employee, Set<EmployeeProject> employeeProjects,
            Set<PerformanceReview> reviews) {
        LOG.debug("Mapping Employee to EmployeeDto for employee ID: {}", employee.getId());
        EmployeeDto employeeDto = new EmployeeDto();
        try {
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setEmail(employee.getEmail());
            Date dateOfJoining = employee.getDateOfJoining();
            if (dateOfJoining != null) {
                LOG.debug("Formatting date of joining for employee ID: {}", employee.getId());
                employeeDto.setDateOfJoining(dateFormat.format(dateOfJoining));
            }
            employeeDto.setSalary(employee.getSalary());
            employeeDto.setManagerName(employee.getManager() != null ? employee.getManager().getName() : "No Manager");
            employeeDto
                    .setDepartmentName(
                            employee.getDepartment() != null ? employee.getDepartment().getName() : "No Department");

            if (employeeProjects != null && !employeeProjects.isEmpty()) {
                LOG.debug("Mapping {} projects for employee ID: {}", employeeProjects.size(), employee.getId());
                List<ProjectDto> projectDtos = employeeProjects.stream()
                        .map(ep -> {
                            LOG.debug("Mapping project ID: {} for employee ID: {}", ep.getProject().getId(),
                                    employee.getId());
                            Project project = ep.getProject();
                            ProjectDto projectDto = new ProjectDto();
                            projectDto.setProjectId(Long.valueOf(project.getId()));
                            projectDto.setProjectName(project.getName());
                            Date startDate = project.getStartDate();
                            Date endDate = project.getEndDate();
                            if (startDate != null) {
                                projectDto.setStartDate(dateFormat.format(startDate));
                            }
                            if (endDate != null) {
                                projectDto.setEndDate(dateFormat.format(endDate));
                            }
                            projectDto.setDepartmentName(
                                    project.getDepartment() != null ? project.getDepartment().getName()
                                            : "No Department");
                            return projectDto;
                        })
                        .toList();
                employeeDto.setProjects(projectDtos);
            } else {
                employeeDto.setProjects(List.of());
            }

            List<ReviewDto> reviewDtos = new ArrayList<>();
            if (reviews != null && !reviews.isEmpty()) {
                LOG.debug("Mapping {} performance reviews for employee ID: {}", reviews.size(), employee.getId());
                reviewDtos = reviews.stream()
                        .map(review -> {
                            LOG.debug("Mapping review ID: {} for employee ID: {}", review.getId(),
                                    employee.getId());
                            ReviewDto reviewDto = new ReviewDto();
                            reviewDto.setReviewId(review.getId());
                            reviewDto.setReviewDate(dateFormat.format(review.getReviewDate()));
                            reviewDto.setScore(review.getScore());
                            reviewDto.setComments(review.getReviewComments());
                            return reviewDto;
                        })
                        .toList();
            }
            employeeDto.setPerformanceReviews(reviewDtos);
        } catch (Exception e) {
            LOG.error("Error mapping Employee to EmployeeDto for employee ID: {} :: {}", employee.getId(),
                    e.getMessage(), e);
        }
        return employeeDto;
    }

}
