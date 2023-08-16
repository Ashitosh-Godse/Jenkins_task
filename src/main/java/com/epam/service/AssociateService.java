package com.epam.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.epam.customexception.AssociateException;
import com.epam.dtos.AssociatesDTO;

public interface AssociateService {
AssociatesDTO addAssociate(AssociatesDTO associatesDTO) throws AssociateException, SQLIntegrityConstraintViolationException;
AssociatesDTO updateAssociate(AssociatesDTO associatesDTO) throws AssociateException;
void deleteAssociateById(int associateId);
List<AssociatesDTO> getAssociatesByGender(String gender);
AssociatesDTO getById(int id) throws AssociateException ;


}
