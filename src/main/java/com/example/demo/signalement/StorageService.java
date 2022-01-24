package com.example.demo.signalement;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	

	public void store(MultipartFile file,String path) throws IllegalStateException, IOException {
		file.transferTo(new File(file.getOriginalFilename()));
		//System.out.println(file.getName());
	}

	

}
