package com.crm.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.dto.ResponseDto;
import com.crm.service.DataPopulationService;

@RestController
@RequestMapping(path = "/populate", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DataPopulationController {

    private static final Logger LOG = LogManager.getLogger(DataPopulationController.class);

    private DataPopulationService dataPopulationService;

    public DataPopulationController(DataPopulationService dataPopulationService) {
        this.dataPopulationService = dataPopulationService;
    }

    @PostMapping("/assignProjects")
    public ResponseEntity<ResponseDto> assignProjects() {
        LOG.info("test");
        Boolean status = dataPopulationService.populateEmployeeProjectData();
        if (status) {
            LOG.info("Employee project data populated successfully.");
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setMessage("Employee project data populated successfully.");
            return ResponseEntity.ok(responseDto);
        } else {
            LOG.error("Failed to populate employee project data.");
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(false);
            responseDto.setMessage("Failed to populate employee project data.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    @PostMapping("/populateReviews")
    public ResponseEntity<ResponseDto> populateReviews() {
        LOG.info("getReviews");
        Boolean status = dataPopulationService.populatePerformanceReviewData();
        if (status) {
            LOG.info("Performance reviews populated successfully.");
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setMessage("Performance reviews populated successfully.");
            return ResponseEntity.ok(responseDto);
        } else {
            LOG.error("Failed to populate performance reviews.");
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(false);
            responseDto.setMessage("Failed to populate performance reviews.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

}
