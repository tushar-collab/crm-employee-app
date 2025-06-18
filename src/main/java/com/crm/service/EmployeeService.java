package com.crm.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.crm.dto.EmployeeDto;

public interface EmployeeService {
    
    public EmployeeDto fetchEmployeeData(Long id);

    public List<EmployeeDto> fetchAllEmployees(JSONObject filterCriteria);
}
