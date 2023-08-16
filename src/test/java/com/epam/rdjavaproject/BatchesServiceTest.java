package com.epam.rdjavaproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.customexception.AssociateException;
import com.epam.dao.BatchesRepo;
import com.epam.dtos.AssociatesDTO;
import com.epam.dtos.BatchesDTO;
import com.epam.entity.Associates;
import com.epam.entity.Batches;
import com.epam.service.BatchesServiceImpl;

@ExtendWith(MockitoExtension.class)
class BatchesServiceTest {
	
	@Mock
	BatchesRepo batchesRepo;
	
	@Mock
	ModelMapper modelMapper;
	
	
	@InjectMocks
	BatchesServiceImpl batchesServiceImpl;
	    
	   AssociatesDTO associatesDTO;

	    Batches batches;
	    
		Associates associates;

		BatchesDTO batchesDTO;
		
		List<Associates> list;

		@BeforeEach
		void initialize() {
			associatesDTO = new AssociatesDTO("ashu","ashitoshgodse007@gmail.com", "male","viit", "active");
			list= new ArrayList<>();
	    	associates=new Associates(0, "ashu","ashitoshgodse007@gmail.com", "male","viit", "active",batches);
			list.add(associates);
	        Date date =new Date();
			batchesDTO=new BatchesDTO("name", "asdsdd", date,date, list);
		}

		
	
	    @Test
	    void testaddAssociate() throws AssociateException {
	        Mockito.when(modelMapper.map(batchesDTO,Batches.class)).thenReturn(batches);
	        Mockito.when(batchesRepo.save(batches)).thenReturn(batches);
	        Mockito.when(modelMapper.map(batches,BatchesDTO.class)).thenReturn(batchesDTO);
	        BatchesDTO expected=batchesServiceImpl.addBatches(batchesDTO);
	    	assertEquals(batchesDTO, expected);
	    	
	    
	    }
	    @Test
	    void testEntityBatches() {
	    	Date date=new Date();
	    	Batches batches=new Batches();
	    	batches.setName("java");
	    	batches.setBatchId(1);
	    	batches.setPractice("java");
	    	batches.setStartDate(date);
	    	batches.setEndDate(date);
	    	batches.setAssociateList(list);
	    	
	    	assertEquals("java",batches.getName());
	    	assertEquals(1,batches.getBatchId());
	    	assertEquals("java",batches.getPractice());
	    	assertEquals(date,batches.getStartDate());
	    	assertEquals(date,batches.getEndDate());
	    	assertEquals(list,batches.getAssociateList());
	    	
	    	
	    }

}
