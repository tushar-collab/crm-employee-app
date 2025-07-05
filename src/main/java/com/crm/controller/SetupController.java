package com.crm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.dto.DropDownDto;
import com.crm.dto.ResponseDto;
import com.crm.service.SetupService;

@RequestMapping(path = "/setup", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*")
@RestController
@Validated
public class SetupController {

    private SetupService setupService;

    public SetupController(SetupService setupService) {
        this.setupService = setupService;
    }

    @GetMapping("/fetch/department")
    public ResponseEntity<ResponseDto> getDepartments() {
        List<DropDownDto> depDto = setupService.getDepartments();
        ResponseDto dto = new ResponseDto();
        if (depDto.size() != 0) {
            dto.setSuccess(true);
            dto.setCount(depDto.size());
            dto.setData(depDto);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            dto.setSuccess(false);
            dto.setCount(0);
            dto.setErrorCode(HttpStatus.NOT_FOUND.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
        }
    }

    @GetMapping("/fetch/project")
    public ResponseEntity<ResponseDto> geProjects() {
        List<DropDownDto> depDto = setupService.getProjects();
        ResponseDto dto = new ResponseDto();
        if (depDto.size() != 0) {
            dto.setSuccess(true);
            dto.setCount(depDto.size());
            dto.setData(depDto);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            dto.setSuccess(false);
            dto.setCount(0);
            dto.setErrorCode(HttpStatus.NOT_FOUND.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
        }
    }
}