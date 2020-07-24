package com.giosinosini.springboot3.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.giosinosini.springboot3.services.exceptions.FileException;

@Service
public class ImageService {
	
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {  // take an image to convert to jpg
	
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());  // get extension => MultipartFile uploadedFile
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Only images in PNG or JPG format are allowed");
		}
		
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());  // convert to BufferedImage (jpg)
			if ("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Error reading file");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extension) {  // so that AmazonS3 receives the image with InputStream
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Error reading file");
		}
	}
}
