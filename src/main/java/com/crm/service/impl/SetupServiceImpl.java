package com.crm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.dto.DropDownDto;
import com.crm.entity.Department;
import com.crm.entity.Project;
import com.crm.repository.DepartmentRepository;
import com.crm.repository.ProjectRepository;
import com.crm.service.SetupService;

@Service
public class SetupServiceImpl implements SetupService {

    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;

    public SetupServiceImpl(DepartmentRepository departmentRepository, ProjectRepository projectRepository) {
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<DropDownDto> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        if (departments.size() == 0) {
            return new ArrayList<>();
        }
        List<DropDownDto> departmentDtos = new ArrayList<>();
        departments.forEach(dep -> {
            DropDownDto dto = new DropDownDto();
            dto.setId(dep.getId());
            dto.setName(dep.getName());
            departmentDtos.add(dto);
        });
        departmentDtos.sort(Comparator.comparing(DropDownDto::getName));

        return departmentDtos;
    }

    @Override
    public List<DropDownDto> getProjects() {
        List<Project> projects = projectRepository.findAll();
        if (projects.size() == 0) {
            return new ArrayList<>();
        }
        List<DropDownDto> projectDtos = new ArrayList<>();
        projects.forEach(proj -> {
            DropDownDto dto = new DropDownDto();
            dto.setId(proj.getId());
            dto.setName(proj.getName());
            projectDtos.add(dto);
        });

        Collections.sort(projectDtos, Comparator.comparing(DropDownDto::getName));
        return projectDtos;
    }

}
