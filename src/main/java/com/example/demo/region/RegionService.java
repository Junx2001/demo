package com.example.demo.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RegionService {
	private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
    
	public List<Region> getRegions() {
		return regionRepository.findAll(Sort.by("nom").ascending());
	}
}
