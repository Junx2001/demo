package com.example.demo.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public class RegionService {
	private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
    
	public List<Region> getRegions() {
		return regionRepository.findAll();
	}
}
