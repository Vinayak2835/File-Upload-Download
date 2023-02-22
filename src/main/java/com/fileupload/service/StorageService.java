package com.fileupload.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.entity.ImageData;
import com.fileupload.repository.StorageRepository;
import com.fileupload.utils.ImageUtils;

@Service
public class StorageService {
	
	private StorageRepository storageRepository;
	
	@Autowired
	public StorageService(StorageRepository storageRepository) {
	    // ...
		this.storageRepository=storageRepository;
	}
	
	
	public String uploadImage(MultipartFile file) throws IOException {
		
		ImageData imageData = storageRepository.save(ImageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.imageData(ImageUtils.compressImage(file.getBytes())).build());
				
			if(imageData != null) {
				
				return "File Uploaded successfully :"+file.getOriginalFilename();
			} 
			else {
				
				return null;
			}
			
	}
			
	public byte[] downLoadImage(String fileName) {
		
		Optional<ImageData> imageData = this.storageRepository.findByName(fileName);
		
		byte[] images = ImageUtils.decompressImage(imageData.get().getImageData());
		
		return images;
	
     }
	
	
}
