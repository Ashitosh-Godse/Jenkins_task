package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dtos.BatchesDTO;
import com.epam.service.BatchesService;


@RestController
@RequestMapping("rd/batches")
public class BatchesController {
	
	@Autowired
	BatchesService batchesService;

	@PostMapping
	public ResponseEntity<BatchesDTO> addBatches(@RequestBody BatchesDTO batchesDTO){
		return new ResponseEntity<>(batchesService.addBatches(batchesDTO),HttpStatus.CREATED);
	}
}
