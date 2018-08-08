package com.lacosdaalegria.transparencia.repository;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class S3Repository {
	
	private final String bucketName = "bucketName";
	private BasicAWSCredentials creds = new BasicAWSCredentials("creds", "creds");
	private AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).withRegion("us-west-2").build();
	private static int ID = 1;

	public String carregaImagem(String tag, MultipartFile imagem){
		
		String nome_imagem = "transparencia_" + tag + "_" + ++ID + "_" + System.currentTimeMillis();
		
		try {
			
			s3client.putObject(new PutObjectRequest(
			         bucketName, nome_imagem, imagem.getInputStream(), 
			         new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nome_imagem;
	}

}
