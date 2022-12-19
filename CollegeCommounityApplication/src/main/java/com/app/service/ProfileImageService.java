package com.app.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service 
public class ProfileImageService {

	
	public void uploadImage(MultipartFile file) throws IllegalStateException, IOException {
		// All file image will store 
		file.transferTo(new File("D:\\root_directory\\assets\\img\\uploads\\"+
	file.getOriginalFilename()));
	}
}


