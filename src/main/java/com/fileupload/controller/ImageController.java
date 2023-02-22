package com.fileupload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.service.StorageService;

@RestController
@RequestMapping("/images")
public class ImageController {
	
	@Autowired
	private StorageService storageService;
	
	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException{
		
		String image = storageService.uploadImage(file);
		
		return ResponseEntity.status(HttpStatus.OK).body(image);
	}
	
	@GetMapping("/{fileName}")
	public ResponseEntity<?> downLoadImage(@PathVariable("fileName") String fileName){
		
		byte[] loadImage = this.storageService.downLoadImage(fileName);
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png"))
				.body(loadImage);
	}

}
