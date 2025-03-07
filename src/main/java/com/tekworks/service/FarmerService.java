package com.tekworks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekworks.entity.Farmer;
import com.tekworks.repository.FarmerRepository;

@Service
public class FarmerService {

	@Autowired
	private FarmerRepository farmerRepository;
	
	public List<Farmer> getAllFarmers(){
		return farmerRepository.findAll();
	}
	
	public List<Farmer> searchFarmer(String name){
		return farmerRepository.findByNameLike(name);
	}
	
	public Farmer getFarmer(Integer id){
		return farmerRepository.findById(id).get();
	}
	
	public Farmer saveFarmer(Farmer farmer){
		return farmerRepository.save(farmer);
	}
	
	public Farmer updateFarmer(Farmer farmer){
		return farmerRepository.save(farmer);
	}
	
	public void deleteFarmer(Integer id){
		 farmerRepository.deleteById(id);
	}
}
