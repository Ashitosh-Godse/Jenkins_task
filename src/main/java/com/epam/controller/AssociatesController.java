package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.customexception.AssociateException;
import com.epam.dtos.AssociatesDTO;
import com.epam.service.AssociateServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rd/associates")
@Slf4j
public class AssociatesController {

	@Autowired
	AssociateServiceImpl associateServiceImpl;

	@GetMapping("/{gender}")
	public ResponseEntity<List<AssociatesDTO>> getAssociatesByGender(@PathVariable String gender) {
		log.info("Entered into method getAssociatesByGender with parameter gender:{}",gender);
		return new ResponseEntity<>(associateServiceImpl.getAssociatesByGender(gender), HttpStatus.OK);
	} 


    @GetMapping("/associate/{id}")
    public ResponseEntity<AssociatesDTO> getAssociatesById(@PathVariable int id) throws AssociateException {
		log.info("Entered into method getAssociatesByGender with parameter id:{}",id);
		return new ResponseEntity<>(associateServiceImpl.getById(id), HttpStatus.OK);
	} 
     
	@PostMapping
	public ResponseEntity<AssociatesDTO> addAssociate(@RequestBody @Valid AssociatesDTO associatesDTO) throws AssociateException {
		log.info("Entered into method addAssociate with parameter associatesDTO:{}",associatesDTO);
		return new ResponseEntity<>(associateServiceImpl.addAssociate(associatesDTO), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<AssociatesDTO> updateBatch(@RequestBody @Valid AssociatesDTO associatesDTO) throws AssociateException {
		log.info("Entered into method updateBatch with parameter associatesDTO:{}",associatesDTO);
		return new ResponseEntity<>(associateServiceImpl.updateAssociate(associatesDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteBatch(@PathVariable("id") int associateId) {
		log.info("Entered into method deleteBatch with parameter deleteBatch:{}",associateId);
		associateServiceImpl.deleteAssociateById(associateId);
	}

}
