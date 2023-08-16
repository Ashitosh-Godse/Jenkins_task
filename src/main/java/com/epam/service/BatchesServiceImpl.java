package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dao.BatchesRepo;
import com.epam.dtos.BatchesDTO;
import com.epam.entity.Batches;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatchesServiceImpl implements BatchesService {
	
	@Autowired
	BatchesRepo batchesRepo;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public BatchesDTO addBatches(BatchesDTO batchesDTO) {
		log.info("entered into service method addBatches with batchesDTO as:{}",batchesDTO);
		return mapper.map(batchesRepo.save(mapper.map(batchesDTO, Batches.class)),BatchesDTO.class);
	}

}
