package com.crm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.crm.entity.Employee;
import com.crm.entity.EmployeeProject;
import com.crm.entity.EmployeeProjectId;
import com.crm.entity.PerformanceReview;
import com.crm.entity.Project;
import com.crm.repository.EmployeeProjectRepository;
import com.crm.repository.EmployeeRepository;
import com.crm.repository.PerformanceReviewRepository;
import com.crm.repository.ProjectRepository;
import com.crm.service.DataPopulationService;

@Service
public class DataPopulationServiceImpl implements DataPopulationService {

    private PerformanceReviewRepository performanceReviewRepository;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private EmployeeProjectRepository employeeProjectRepository;

    private static final Logger LOG = LogManager.getLogger(DataPopulationServiceImpl.class);

    public DataPopulationServiceImpl(PerformanceReviewRepository performanceReviewRepository,
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository,
            EmployeeProjectRepository employeeProjectRepository) {
        this.performanceReviewRepository = performanceReviewRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.employeeProjectRepository = employeeProjectRepository;
    }

    @Override
    public Boolean populateEmployeeProjectData() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            List<Project> projects = projectRepository.findAll();
            Map<String, List<String>> departmentRoleMap = new HashMap<>();
            departmentRoleMap.put("Human Resources", List.of("Manager", "Executive"));
            departmentRoleMap.put("Engineering", List.of("Developer", "Tester", "Manager"));
            departmentRoleMap.put("Finance", List.of("Accountant", "Analyst", "Manager"));
            departmentRoleMap.put("Marketing", List.of("Executive", "Manager"));
            departmentRoleMap.put("Sales", List.of("Executive", "Manager"));
            departmentRoleMap.put("IT Support", List.of("Executive", "Technician", "Manager"));

            Map<Integer, List<Project>> departmentProjects = projects.stream().collect(
                    Collectors.groupingBy(
                            project -> project.getDepartment().getId(),
                            HashMap::new,
                            Collectors.toList()));
            for (Employee employee : employees) {
                List<Project> employeeProjects = departmentProjects.get(employee.getDepartment().getId());
                EmployeeProject employeeProject = new EmployeeProject();
                employeeProject.setEmployee(employee);
                if (employeeProjects != null && !employeeProjects.isEmpty()) {
                    Project randomProject = employeeProjects.get((int) (Math.random() * (employeeProjects.size() - 1)));
                    employeeProject.setProject(randomProject);
                    employeeProject.setId(new EmployeeProjectId(employee.getId(), randomProject.getId()));
                }
                long currentTime = System.currentTimeMillis();
                long yearInMillis = 365L * 24 * 60 * 60 * 1000;
                long randomMillisAgo = (long) (Math.random() * yearInMillis);
                Date randomDate = new Date(currentTime - randomMillisAgo);
                employeeProject.setAssignedDate(randomDate);
                List<String> roleList = departmentRoleMap.get(employee.getDepartment().getName());
                employeeProject.setRole(roleList.get((int) (Math.random() * (roleList.size() - 1))));
                LOG.info("Employee: {}, Project: {}, Role: {}, Assigned Date: {}",
                        employee.getName(),
                        employeeProject.getProject() != null ? employeeProject.getProject().getName() : "N/A",
                        employeeProject.getRole(),
                        employeeProject.getAssignedDate());
                employeeProjectRepository.save(employeeProject);
            }
        } catch (Exception e) {
            LOG.error("Error populating employee project data", e);
            return false;
        }
        return true;
    }

    public Boolean populatePerformanceReviewData() {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = employeeRepository.findAll();
            for (Employee employee : employees) {
                PerformanceReview performanceReview = new PerformanceReview();
                performanceReview.setEmployee(employee);
                long currentTime = System.currentTimeMillis();
                long yearInMillis = 365L * 24 * 60 * 60 * 1000;
                long randomMillisAgo = (long) (Math.random() * yearInMillis);
                Date randomDate = new Date(currentTime - randomMillisAgo);
                performanceReview.setReviewDate(randomDate);
                double randomScoreDouble = 6 + (Math.random() * 4); // Random double between 6 (inclusive) and 10
                                                                    // (exclusive)
                double roundedScore = Math.round(randomScoreDouble * 100.0) / 100.0; // Round to 2 decimals
                BigDecimal randomScore = BigDecimal.valueOf(roundedScore);
                String reviewComment = "Performance review for " + employee.getName() + " with score: " + randomScore;
                performanceReview.setReviewComments(reviewComment);
                performanceReview.setScore(randomScore);
                performanceReviewRepository.save(performanceReview);
                LOG.info("Performance review for employee: {}, Score: {}", employee.getName(), randomScore);
            }
        } catch (Exception e) {
            LOG.error("Error populating performance review data", e);
            return false;
        }
        return true;
    }

}
