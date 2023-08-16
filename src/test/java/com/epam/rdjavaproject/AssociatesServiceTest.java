package com.epam.rdjavaproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.advice.ExceptionResponse;
import com.epam.customexception.AssociateException;
import com.epam.dao.AssociateRepo;
import com.epam.dao.BatchesRepo;
import com.epam.dtos.AssociatesDTO;
import com.epam.dtos.BatchesDTO;
import com.epam.entity.Associates;
import com.epam.entity.Batches;
import com.epam.service.AssociateServiceImpl;

@ExtendWith(MockitoExtension.class)
class AssociatesServiceTest {

	@Mock
	AssociateRepo associateRepo;
	
	@Mock
	BatchesRepo batchesRepo;
	
	@Mock
	ModelMapper modelMapper;
	
	
	@InjectMocks
	AssociateServiceImpl associateServiceImpl;
	
	AssociatesDTO associatesDTO;

    Batches batches;
    
	Associates associates;

	BatchesDTO batchesDTO;
    
    @BeforeEach
     void initialize() throws ParseException {
    	
    	 @SuppressWarnings("unused")
		ExceptionResponse exceptionResponse=new ExceptionResponse("","","","");
 		 associatesDTO = new AssociatesDTO("ashu","ashitoshgodse007@gmail.com", "male","viit", "active");
    	 List<Associates> list=new ArrayList<>();
    	 Date date=new SimpleDateFormat("YYYY-MM-DD").parse("20001-12-10");
    	 batchesDTO=new BatchesDTO("java","RD",date, date, list);
    	 batches=new Batches(0,"java","RD",date, date, list);
    	 associates=new Associates(0, "ashu","ashitoshgodse007@gmail.com", "male","viit", "active",batches);
    	 @SuppressWarnings("unused")
		AssociatesDTO newassociatesDTO=new AssociatesDTO(1,1,"ashu","ashitoshgodse007@gmail.com", "male","viit", "active");

    }
    
    @Test
    void testEntityAssociate() {
    	Associates associates=new Associates();
    	associates.setName("java");
    	associates.setEmail("ashitoshgodse007@gmail");
    	associates.setCollege("viit");
    	associates.setGender("male");
    	associates.setStatus("active");
    	associates.setAssociatesId(1);
    	associates.setBatch(batches);
    	assertEquals("java",associates.getName());
    	assertEquals("ashitoshgodse007@gmail",associates.getEmail());
    	assertEquals("viit",associates.getCollege());
    	assertEquals("male",associates.getGender());
    	assertEquals("active",associates.getStatus());
    	assertEquals(1,associates.getAssociatesId());
    	assertEquals(batches,associates.getBatch());

    	
    	
    }
    @Test
    void testEntityStudentDTO() {
    	AssociatesDTO associates=new AssociatesDTO();    	
    	associates.setName("java");
    	associates.setEmail("ashitoshgodse007@gmail");
    	associates.setCollege("viit");
    	associates.setGender("male"); 
    	associates.setStatus("active");
    	associates.setAssociatesId(1);
    	associates.setBatchId(1);
    	assertEquals("java",associates.getName());
    	assertEquals("ashitoshgodse007@gmail",associates.getEmail());
    	assertEquals("viit",associates.getCollege());
    	assertEquals("male",associates.getGender());
    	assertEquals("active",associates.getStatus());
    	assertEquals(1,associates.getAssociatesId());
    	assertEquals(1,associates.getBatchId());
    	
    	
    }
// 
//
//    @Test
//    void testEntityBatch() {
//   	    List<Student> list=new ArrayList<>();
//   	  Student studentdemo=new Student(0, "hi", 0, batch);
//   	    list.add(studentdemo);
//    	Batch batch=new Batch();
//    	batch.setBatchId(0);
//    	batch.setBatchName("java");
//    	batch.setDomain("java");
//    	batch.setListOfStudents(list);
//
//    	assertEquals(0, batch.getBatchId());
//    	assertEquals("java", batch.getBatchName());
//    	assertEquals("java", batch.getDomain());
//    	assertEquals(batch.getListOfStudents(),list);
//    	
//    }
//    
    @Test
    void testgetAssociateByGender() {
   	 List<AssociatesDTO>associatesDTOs=new ArrayList<>();
   	 associatesDTOs.add(associatesDTO);
   	 List<Associates>associatesList=new ArrayList<>();
     Mockito.when(associateRepo.findAllByGender(anyString())).thenReturn(associatesList);
     associatesList.add(associates);
     Mockito.when(modelMapper.map(associates,AssociatesDTO.class)).thenReturn(associatesDTO);
     List<AssociatesDTO>expected=associateServiceImpl.getAssociatesByGender("male");
     assertEquals(associatesDTOs, expected);
    }
//    
//    @Test
//    void testgetById() throws BatchException {
//    Optional<Batch> batchOp=Optional.of(batch);
//     Mockito.when(batchRepository.findById(1)).thenReturn(batchOp);
//     Mockito.when(modelMapper.map(batch,BatchDTO.class)).thenReturn(batchDTO);
//     BatchDTO expected=batchServiceImpl.getBatch(1);
//     assertEquals(batchDTO, expected);
//    }
//    
//    @Test
//    void testgetByInvalidId() throws BatchException {
//     assertThrows(BatchException.class, ()->batchServiceImpl.getBatch(1));
//    }
//    
    @Test
    void testdeleteAssociate() throws AssociateException {
    	Mockito.doNothing().when(associateRepo).deleteById(1);
    	associateServiceImpl.deleteAssociateById(1);
    }
    
    @Test
    void testaddAssociate() throws AssociateException, SQLIntegrityConstraintViolationException {
    	Mockito.when(batchesRepo.existsById(anyInt())).thenReturn(true);
        Mockito.when(modelMapper.map(associatesDTO,Associates.class)).thenReturn(associates);
        Mockito.when(associateRepo.save(associates)).thenReturn(associates);
        Mockito.when(modelMapper.map(associates,AssociatesDTO.class)).thenReturn(associatesDTO);
        AssociatesDTO expected=associateServiceImpl.addAssociate(associatesDTO);
    	assertEquals(associatesDTO, expected);
    	
    
    }
    @Test
    void testaddAssociateException() throws AssociateException, SQLIntegrityConstraintViolationException {
    	Mockito.when(batchesRepo.existsById(anyInt())).thenReturn(false);
       
    	assertThrows(AssociateException.class,()->associateServiceImpl.addAssociate(associatesDTO));
    	
    
    }
    @Test
    void testupdateAssociate() throws AssociateException {
    	
    	Optional<Associates> associateOp=Optional.of(associates);
        Mockito.when(associateRepo.findById(anyInt())).thenReturn(associateOp);
        Mockito.when(modelMapper.map(associatesDTO,Associates.class)).thenReturn(associates);
        Mockito.when(associateRepo.save(associates)).thenReturn(associates);
        Mockito.when(modelMapper.map(associates,AssociatesDTO.class)).thenReturn(associatesDTO);
        AssociatesDTO expected=associateServiceImpl.updateAssociate(associatesDTO);
    	assertEquals(associatesDTO, expected);
    	
    
    }
 
    @Test
    void testgetById() throws AssociateException {
    Optional<Associates> associatesOp=Optional.of(associates);
     Mockito.when(associateRepo.findById(1)).thenReturn(associatesOp);
     Mockito.when(modelMapper.map(associates,AssociatesDTO.class)).thenReturn(associatesDTO);
     AssociatesDTO expected=associateServiceImpl.getById(1);
     assertEquals(associatesDTO, expected);
    }
    @Test
    void testgetByIdWithException() throws AssociateException {
    assertThrows(AssociateException.class, ()->associateServiceImpl.getById(1));
    }

    @Test
    void testUpdateForInvalidCase() throws AssociateException {
     assertThrows(AssociateException.class, ()->associateServiceImpl.updateAssociate(associatesDTO));
    }
}
