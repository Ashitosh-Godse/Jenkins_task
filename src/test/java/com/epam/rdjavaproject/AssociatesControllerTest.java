package com.epam.rdjavaproject;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLIntegrityConstraintViolationException;
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

import com.epam.controller.AssociatesController;
import com.epam.customexception.AssociateException;
import com.epam.dtos.AssociatesDTO;
import com.epam.dtos.BatchesDTO;
import com.epam.entity.Associates;
import com.epam.entity.Batches;
import com.epam.service.AssociateServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AssociatesController.class)
class AssociatesControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AssociatesController associatesController;

	@MockBean
	AssociateServiceImpl associateServiceImpl;

	AssociatesDTO associatesDTO;

    Batches batches; 
    
	Associates associates;

	BatchesDTO batchesDTO;
	
	List<Associates>list;

	@BeforeEach
	void initialize() {
		associatesDTO = new AssociatesDTO("ashu","ashitoshgodse007@gmail.com", "male","viit", "active");
		list= new ArrayList<>();
		list.add(associates);
        Date date =new Date();
		batchesDTO=new BatchesDTO("name", "asdsdd", date,date, list);
	}

	@Test
	void testgetAssociateByGender() throws Exception {
		List<AssociatesDTO> associatesDTOList = new ArrayList<>();
		Mockito.when(associateServiceImpl.getAssociatesByGender("male")).thenReturn(associatesDTOList);
		mockMvc.perform(get("/rd/associates/male")).andExpect(status().isOk());

	}

	
	@Test
	void testget() throws Exception {
		Mockito.when(associateServiceImpl.getById(1)).thenReturn(associatesDTO);
		int id=1;
		mockMvc.perform(get("/rd/associates/associate/{id}",id)).andExpect(status().isOk());

	}
	
	@Test
	void getBatchByIdExceptionTest() throws Exception {
	int id=1;
	when(associateServiceImpl.getById(1)).thenThrow(AssociateException.class);
	mockMvc.perform(get("/rd/associates/associate/{id}",id))
	.andExpect(status().isNotFound());
	}
	

	
	@Test
	void testgetEx() throws Exception {

		when(associateServiceImpl.addAssociate(associatesDTO)).thenThrow(AssociateException.class);
		mockMvc.perform(post("/rd/associates/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(associatesDTO)))
		.andExpect(status().isNotFound());

	}

  
	@Test
	void testException() throws Exception {
		AssociatesDTO associatesDTO=new AssociatesDTO();
		Mockito.when(associateServiceImpl.addAssociate(associatesDTO)).thenReturn(associatesDTO);
		mockMvc.perform(post("/rd/associates")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(associatesDTO)))
		        .andExpect(status().isBadRequest());

	}
	
	@Test
	void testdelete() throws Exception {
		Mockito.doNothing().when(associateServiceImpl).deleteAssociateById(anyInt());
		mockMvc.perform(delete("/rd/associates/1")).andExpect(status().isNoContent());

	}
	
	@Test 
	void testaddAssociate() throws Exception {
		Mockito.when(associateServiceImpl.addAssociate(associatesDTO)).thenReturn(associatesDTO);
		mockMvc.perform(post("/rd/associates")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(associatesDTO)))
		        .andExpect(status().isCreated());

	}
	
	@Test
	void updateAssociate() throws  Exception {
		
		Mockito.when(associateServiceImpl.updateAssociate(associatesDTO)).thenReturn(associatesDTO);
		mockMvc.perform(put("/rd/associates")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(associatesDTO)))
		.andExpect(status().isOk());
	}

}
