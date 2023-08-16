package com.epam.rdjavaproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.BatchesController;
import com.epam.dtos.AssociatesDTO;
import com.epam.dtos.BatchesDTO;
import com.epam.entity.Associates;
import com.epam.entity.Batches;
import com.epam.service.BatchesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BatchesController.class)
class BatchesControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
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
		list.add(associates);
        Date date =new Date();
		batchesDTO=new BatchesDTO("name", "asdsdd", date,date, list);
	}

	
	
	@Test 
	void testaddAssociate() throws Exception {
		Mockito.when(batchesServiceImpl.addBatches(batchesDTO)).thenReturn(batchesDTO);
		mockMvc.perform(post("/rd/batches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(batchesDTO)))
		        .andExpect(status().isCreated());

	}
 
}
