package com.crm.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.crm.dto.EmployeeDto;
import com.crm.entity.Employee;
import com.crm.entity.PerformanceReview;
import com.crm.exception.ResourceNotFoundException;
import com.crm.mapper.EmployeeMapper;
import com.crm.repository.EmployeeRepository;
import com.crm.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  private static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);

  @Value("${config.maxReviewsCount}")
  private Integer maxReviewsCount;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public EmployeeDto fetchEmployeeData(Long id) {
    LOG.info("Fetching employee data for ID: {}", id);
    EmployeeDto employeeDto = new EmployeeDto();
    try {
      Employee employee = employeeRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", String.valueOf(id)));

      Set<PerformanceReview> reviews = employee.getPerformanceReviews();
      if (reviews != null && !reviews.isEmpty()) {
        if (reviews.size() > maxReviewsCount) {
          LOG.info("Limiting reviews to the latest {} for employee ID: {}", maxReviewsCount, id);
          reviews = reviews.stream()
              .sorted((r1, r2) -> r1.getReviewDate().compareTo(r2.getReviewDate()))
              .limit(maxReviewsCount)
              .collect(Collectors.toSet());
        }
      } else {
        LOG.warn("No performance reviews found for employee ID: {}", id);
        reviews = new HashSet<>();
      }
      employeeDto = EmployeeMapper.mapToEmployeeDto(employee, employee.getEmployeeProjects(), reviews);
      return employeeDto;
    } catch (Exception e) {
      LOG.error("Error fetching employee data for ID: {}", id, e);
      if (e instanceof EntityNotFoundException) {
        throw new ResourceNotFoundException("Employee", "id", String.valueOf(id));
      } else {
        throw new RuntimeException("An unexpected error occurred while fetching employee data.");
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<EmployeeDto> fetchAllEmployees(JSONObject filterCriteria) {
    LOG.info("Fetching all employees with filter criteria: {}", filterCriteria);

    List<Employee> employees = employeeRepository.findAll(new Specification<Employee>() {
      @Override
      public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        LOG.info("Creating specification for employee filtering with criteria: {}", filterCriteria);
        List<Predicate> predicates = new ArrayList<>();
        if (filterCriteria.containsKey("department")) {
          List<String> departments = (List<String>) filterCriteria.get("department");
          if (departments != null && !departments.isEmpty()) {
            LOG.info("Filtering employees by departments: {}", departments);
            predicates.add(root.get("department").get("name").in(departments));
          }
        }
        if (filterCriteria.containsKey("projects")) {
          List<String> projects = (List<String>) filterCriteria.get("projects");
          if (projects != null && !projects.isEmpty()) {
            LOG.info("Filtering employees by projects: {}", projects);
            predicates.add(root.join("employeeProjects").join("project").get("name").in(projects));
          }
        }

        if (filterCriteria.containsKey("reviewDate")) {
          String reviewDateStr = (String) filterCriteria.get("reviewDate");
          if (reviewDateStr != null && !reviewDateStr.isEmpty()) {
             java.sql.Date reviewDate = java.sql.Date.valueOf(reviewDateStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            predicates.add(criteriaBuilder.equal(root.join("performanceReviews").get("reviewDate"), reviewDate));
            // try {
            // LOG.info("Filtering employees by review date: {}", reviewDateStr);
            // Date reviewDate = dateFormat.parse(reviewDateStr);
            // } catch (ParseException e) {
            // LOG.error("Error parsing review date: {}", reviewDateStr, e);
            // }
          }
        }

        if (predicates.isEmpty()) {
          LOG.info("No filter criteria provided, fetching all employees.");
          return criteriaBuilder.conjunction();
        } else {
          LOG.info("Applying filter criteria to fetch employees.");
          return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }

      }

    });

    if (employees.isEmpty()) {
      LOG.warn("No employees found matching the filter criteria.");
      throw new ResourceNotFoundException("Employee", "filterCriteria", filterCriteria.toString());
    } else {
      LOG.info("Total employees fetched: {}", employees.size());
      List<EmployeeDto> employeeDtos = new ArrayList<>();
      employees.forEach(employee -> {
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee, employee.getEmployeeProjects(),
            employee.getPerformanceReviews());
        employeeDtos.add(employeeDto);
      });
      return employeeDtos;
    }

  }
}