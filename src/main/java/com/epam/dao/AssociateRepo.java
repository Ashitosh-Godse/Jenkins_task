package com.epam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entity.Associates;

public interface AssociateRepo extends JpaRepository<Associates, Integer> {
	List<Associates> findAllByGender(String gender);
}
