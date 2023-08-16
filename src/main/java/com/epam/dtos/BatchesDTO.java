package com.epam.dtos;

import java.util.Date;
import java.util.List;

import com.epam.entity.Associates;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BatchesDTO {
	
	private int batchId;
	
	@NotBlank(message="name value cannot be null or empty")
	private String name;
	
	@NotBlank(message="practice value cannot be null or empty")
	private String practice;
	
	@NotBlank(message="startDate value cannot be null or empty")
	private Date startDate;
	
	@NotBlank(message="endDate value cannot be null or empty")
	private Date endDate;
	
	private List<Associates> associateList;

	public BatchesDTO(String name,
			 String practice,
			 Date startDate,
			 Date endDate, List<Associates> associateList) {
		super();
		this.name = name;
		this.practice = practice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.associateList = associateList;
	}
}
