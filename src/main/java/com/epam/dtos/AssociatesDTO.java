package com.epam.dtos;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociatesDTO {

	public AssociatesDTO(String name,
		     String email,
			 String gender,
			 String college,
			 String status) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.college = college;
		this.status = status;
	}

	int associatesId;
	
	@NotNull(message="batch id cannot be null")
	private int batchId;
	
	@NotBlank(message="name value cannot be null or empty")
	private String name;
	
	@Email(message="Invalid email format!!")
	private String email;
	
	@NotBlank(message="gender value cannot be null or empty")
	private String gender;
	
	@NotBlank(message="college value cannot be null or empty")
	private String college;
	
	@NotBlank(message="status value cannot be null or empty")
	private String status;
	
}
