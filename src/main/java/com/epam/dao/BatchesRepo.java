package com.epam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entity.Batches;

public interface BatchesRepo extends JpaRepository<Batches, Integer> {

}
