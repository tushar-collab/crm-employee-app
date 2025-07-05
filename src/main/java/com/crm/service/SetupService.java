package com.crm.service;

import java.util.List;

import com.crm.dto.DropDownDto;

public interface SetupService {

    public List<DropDownDto> getDepartments();
    public List<DropDownDto> getProjects();



}