package com.crm.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.dto.EmployeeDto;
import com.crm.dto.ResponseDto;
import com.crm.service.EmployeeService;
import com.crm.service.impl.EmployeeServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/crm", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class EmployeeController {

    private EmployeeService employeeService;

    private static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<ResponseDto> fetchEmployeeData(
            @NotNull @Pattern(regexp = "^[0-9]+$", message = "ID must be a nunber") @PathVariable("id") String id) {
        LOG.info("Fetching employee data for ID: {}", id);
        Long employeeId = Long.parseLong(id);
        if (employeeId <= 0) {
            LOG.warn("Invalid employee ID: {}", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(false, 0, "employee id must be greater than 0", "BAD REQUEST", null));
        }
        EmployeeDto employeeDto = employeeService.fetchEmployeeData(employeeId);
        if (employeeDto != null) {
            LOG.info("Employee data fetched successfully for ID: {}", id);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setMessage("Employee data fetched successfully.");
            responseDto.setData(employeeDto);
            responseDto.setCount(1);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } else {
            LOG.error("Error fetching employee data for ID: {}", id);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(false);
            responseDto.setCount(0);
            responseDto.setMessage("Employee not found.");
            responseDto.setErrorCode("EMPLOYEE_NOT_FOUND");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @PostMapping("/fetch")
    public ResponseEntity<ResponseDto> filterEmployees(@Valid @RequestBody JSONObject filterCriteria) {
        LOG.info("Fetching all employees");
        List<EmployeeDto> employees = employeeService.fetchAllEmployees(filterCriteria);
        LOG.info("Total employees fetched: {}", employees.size());
        if (employees.isEmpty()) {
            LOG.info("No employees found matching the criteria.");
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(false);
            responseDto.setMessage("No employees found matching the criteria.");
            responseDto.setErrorCode("NO_EMPLOYEES_FOUND");
            responseDto.setCount(0);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setMessage("All employees fetched successfully.");
            responseDto.setData(employees);
            responseDto.setCount(employees.size());
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

}
