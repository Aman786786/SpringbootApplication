package com.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.ProfileImage;
import com.app.model.User;
import com.app.repository.FileUploadRepository;
import com.app.service.ProfileImageService;

import jakarta.persistence.criteria.Path;

@RestController
@RequestMapping("/upload/file")
public class ProfileImageController {

	@Autowired
	ProfileImageService profileImageService;

	@Autowired
	FileUploadRepository fileUploadRepository;

	private static final String DIR_TO_UPLOAD = "D:\\root_directory\\assets\\img\\uploads\\";

	@PostMapping("/image")
	public void uploadImage(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		profileImageService.uploadImage(file);
	}

	@PostMapping(value = "/database")
	public String uploadToDatabase(@RequestParam String name, @RequestParam MultipartFile file) throws IOException {

		// Set the form data into entity
		ProfileImage pmOfIndia = new ProfileImage();
		pmOfIndia.setName(name);
		pmOfIndia.setPhoto(file.getBytes());

		// Save the records into the database
		fileUploadRepository.save(pmOfIndia);

		return "Records saved into database.";
	}

	@PostMapping("/directory")
	public String uploadToDirectory(@RequestParam MultipartFile file) throws IOException {

		byte[] bytes = file.getBytes();
		java.nio.file.Path path = Paths.get(DIR_TO_UPLOAD + file.getOriginalFilename());
		Files.write(path, bytes);

		return "File uploaded";
	}
	
	

	@PutMapping("/update/image/{id}")
	public ProfileImage updateProfileImage(@PathVariable int id, @RequestBody ProfileImage image) {

		ProfileImage exImage = fileUploadRepository.getById(id);

		exImage.setName(image.getName());

		return fileUploadRepository.save(exImage);

	}

}
