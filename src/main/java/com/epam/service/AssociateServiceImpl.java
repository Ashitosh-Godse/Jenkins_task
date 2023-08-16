package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.customexception.AssociateException;
import com.epam.dao.AssociateRepo;
import com.epam.dao.BatchesRepo;
import com.epam.dtos.AssociatesDTO;
import com.epam.entity.Associates;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssociateServiceImpl implements AssociateService {
	
	@Autowired
	AssociateRepo associateRepo;
	
	@Autowired
	BatchesRepo batchRepo;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public AssociatesDTO addAssociate(AssociatesDTO associatesDTO) throws AssociateException{
		log.info("entered into service method addAssociate with associatesDTO as :{}",associatesDTO);
		if(!batchRepo.existsById(associatesDTO.getBatchId())) {
			log.error("batch id is invalid!!");
			throw new AssociateException("batch with id doesnt exists ");
		}
		return mapper.map(associateRepo.save(mapper.map(associatesDTO, Associates.class)),AssociatesDTO.class);

	}
    
	@Override 
	public AssociatesDTO updateAssociate(AssociatesDTO associatesDTO) throws AssociateException {
		log.info("entered into service method updateAssociate with associatesDTO as :{}",associatesDTO);
		return associateRepo.findById(associatesDTO.getAssociatesId())
			.map(associate-> 
			 mapper.map(associateRepo.save(mapper.map(associatesDTO, Associates.class)),AssociatesDTO.class))
			.orElseThrow(
			()->new AssociateException("Invalid id...associate with id "+associatesDTO.getAssociatesId()+" not found!"));
	}  
	

	@Override
	public void deleteAssociateById(int associateId) {
		log.info("entered into service method deleteAssociateById with associatesId as :{}",associateId);
		associateRepo.deleteById(associateId);
		log.info("exiting from deleteAssociateById");
	} 

	@Override
	public List<AssociatesDTO> getAssociatesByGender(String gender){
		log.info("entered into service method getAssociateByGender with gender as :{}",gender); 
		return 	associateRepo.findAllByGender(gender)
				.stream()
				.map(associate->mapper.map(associate, AssociatesDTO.class))
				.toList();

				

	}	
	

	public AssociatesDTO getById(int id) throws AssociateException  {
		log.info("entered into service method getById with id as :{}",id);
		return associateRepo.findById(id).map(associate->mapper.map(associate, AssociatesDTO.class))
				.orElseThrow(()->new AssociateException("Invalid id...associate with id "+id+" not found!"));
		 
	}
}
