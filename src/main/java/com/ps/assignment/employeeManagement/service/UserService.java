package com.ps.assignment.employeeManagement.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ps.assignment.employeeManagement.dto.UserDto;
import com.ps.assignment.employeeManagement.model.User;

public interface UserService {

    public Boolean loadUsersFromExternalAPI(JSONArray usersArray);

    public List<UserDto> findByFirstName(String firstName);

    public List<UserDto> findByLastName(String lastName);

    public JSONObject getDataFromExternalAPI();

    public List<UserDto> findAllUser();

    public List<UserDto> findBySsn(String ssn);

    public UserDto findUserById(Integer id);

    public Boolean doInitialSetup();

    public void addUser(org.json.simple.JSONObject requestData);
}
