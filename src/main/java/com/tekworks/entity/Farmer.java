package com.tekworks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Farmer {
	
	@Id
	@SequenceGenerator(name = "FARMER_SEQ",sequenceName = "FARMER_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "FARMER_SEQ")
	private Integer id;
	
	@Size(min=4,max = 50)
	@Column(length = 150)
	private String name;
	
	@Size(min=4,max = 50)
	@Column(length = 50)
	private String address;
	
	@Positive
	private Double income;

}
