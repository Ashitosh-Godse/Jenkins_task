package com.epam.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Batches {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int batchId;
	
	private String name;
	
	private String practice;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date endDate;
	
	@OneToMany(mappedBy = "batch",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Associates> associateList;

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Associates> getAssociateList() {
		return associateList;
	}

	public void setAssociateList(List<Associates> associateList) {
		associateList.forEach(associate->associate.setBatch(this));
		this.associateList = associateList;
	}
	
	
	
	
	
}
