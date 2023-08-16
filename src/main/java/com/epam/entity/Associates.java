package com.epam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Associates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int associatesId;
	
	private String name;
	
	private String email;
	
	private String gender;
	
	private String college;
	
	private String status;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn
	private Batches batch;
	
}
