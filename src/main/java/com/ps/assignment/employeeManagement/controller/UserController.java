package com.ps.assignment.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ps.assignment.employeeManagement.dto.ErrorResponse;
import com.ps.assignment.employeeManagement.dto.GenericResult;
import com.ps.assignment.employeeManagement.dto.UserDto;
import com.ps.assignment.employeeManagement.exception.SearchException;
import com.ps.assignment.employeeManagement.exception.SetupException;
import com.ps.assignment.employeeManagement.exception.UserNotFoundException;
import com.ps.assignment.employeeManagement.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@Tag(name = "User CRUD operations", description = "User CRUD operations")
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
        JSONObject obj = (JSONObject) binder.getTarget();
        if (!obj.isEmpty()) {
            Set<String> keys = (Set<String>) obj.keySet();
        }
        binder.registerCustomEditor(String.class, trimmer);
    }

    @Operation(summary = "Load Users from external API", description = "Load users from external API", tags = {
            "User CRUD operations" })
    @ApiResponse(responseCode = "200", description = "Users loaded successfully")
    @PostMapping("/doInitialSetup")
    public GenericResult doInitialSetup() {
        LOG.info("Finding all users");
        GenericResult result = new GenericResult();
        Boolean status = userService.doInitialSetup();
        if (status) {
            result.setStatus("SUCCESS");
            result.setMessage("Users loaded successfully");
            result.setSuccess(true);
        } else {
            throw new SetupException("Failed to load users");
        }
        return result;
    }

    @PostMapping("/doSearch")
    @Operation(summary = "Search Users", description = "Search users based on search term", tags = {
            "User CRUD operations" })
    @ApiResponse(responseCode = "200", description = "Users fetched successfully")
    public GenericResult searchUsers(@RequestBody JSONObject requestData) {
        LOG.info("Searching users :: " + requestData.get("searchTerm"));
        GenericResult result = new GenericResult();
        List<UserDto> users = new ArrayList<>();
        try {
            String searchType = requestData.get("searchType") != null ? requestData.get("searchType").toString() : "";
            if ("All".equalsIgnoreCase(searchType)) {
                users.addAll(userService.findByFirstName(requestData.get("name").toString()));
                users.addAll(userService.findByLastName(requestData.get("name").toString()));
                users.addAll(userService.findBySsn(requestData.get("name").toString()));
            } else if ("Fname".equalsIgnoreCase(searchType)) {
                users.addAll(userService.findByFirstName(requestData.get("name").toString()));
            } else if ("Lname".equalsIgnoreCase(searchType)) {
                users.addAll(userService.findByLastName(requestData.get("name").toString()));
            } else if ("Ssn".equalsIgnoreCase(searchType)) {
                users.addAll(userService.findBySsn(requestData.get("name").toString()));
            } else {
                users = userService.findAllUser();
            }
            if (!users.isEmpty()) {
                result.setStatus("SUCCESS");
                result.setMessage("Users found successfully");
                result.setSuccess(true);
                result.setData(users);
            } else {
                throw new SearchException("No users found for search term :: " + requestData.get("searchTerm")
                        + " and search type :: " + searchType);
            }
        } catch (Exception e) {
            if (e instanceof SearchException) {
                throw e;
            }
            result.setStatus("FAILURE");
            result.setMessage("Failed to find users");
            result.setSuccess(false);
            LOG.error("Error searching users :: " + e.getMessage());
        }
        return result;
    }

    @PostMapping("/findUserById")
    @Operation(summary = "Load User based on id", description = "Load users to grid based on user's id from DB", tags = {
            "User CRUD operations" })
    @ApiResponse(responseCode = "200", description = "Users fetched successfully")
    public ResponseEntity<GenericResult> findUserById(@RequestBody JSONObject requestData) {
        LOG.info("Finding user by id :: " + requestData.get("id"));
        GenericResult result = new GenericResult();
        UserDto user = new UserDto();
        try {
            Integer id = Integer.parseInt(requestData.get("id").toString());
            user = userService.findUserById(id);
            if (user != null) {
                result.setStatus("SUCCESS");
                result.setMessage("User found successfully");
                result.setSuccess(true);
                result.setData(user);
            } else {
                throw new UserNotFoundException("User not found :: " + id);
            }
        } catch (Exception e) {
            if (e instanceof UserNotFoundException) {
                throw e;
            }
            result.setStatus("FAILURE");
            result.setMessage("Failed to find user");
            result.setSuccess(false);
            LOG.error("Error finding user by id :: " + e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleSetupException(SetupException exc) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exc) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleSearchException(SearchException exc) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    public GenericResult saveUser(@RequestBody JSONObject requestData){
        GenericResult res = new GenericResult();
        try {
            userService.addUser(requestData);
            res.setMessage("User saved");
            res.setSuccess(true);
            res.setStatus("SUCCESS");
        } catch (Exception e) {
            LOG.error("Exceptin " ,e);
            res.setMessage("failed to add user");
            res.setStatus("FAILED");
        }
        return res;
    }

}
