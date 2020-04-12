package com.applojamc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.applojamc.services.exceptions.FileException;

@Service
public class S3Service {
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${s3.bucket}")
	private String bucketName; 
	
	public URI uploadFile(MultipartFile multipartFile) {

		try {

			String fileName = multipartFile.getOriginalFilename();

			/*
			 * is é uma referência para um objeto básico de leitura do java.io esse obj
			 * encapsula o processamento de leitura a partir de uma origem
			 */
			InputStream is;
			is = multipartFile.getInputStream();

			String contentType = multipartFile.getContentType();

			return uploadFile(is, fileName, contentType);

		} catch (IOException e) {
			throw new FileException("Erro do IO: " + e.getMessage());
		}
	}
	
	public URI uploadFile(InputStream is, String fileName, String contentType) {

		try {

			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(contentType);

			LOG.info("Iniciando upload");

			amazonS3.putObject(bucketName, fileName, is, objectMetadata);

			LOG.info("Upload finalizado");

			return amazonS3.getUrl(bucketName, fileName).toURI();

		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}
}
