package com.example.guchiBoard.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class DownloadServiceImpl implements DownloadService {
	
	/**
	 * InputStreamでダウンロードしたいファイルを読み込み、OutputStreamでresponseに書き込む
	 * @param response　レスポンスデータ(ファイルの情報)
	 */
	@Override
	public void fileDownload(HttpServletResponse response, String imgFilePath){
		//File file = new File(imgFilePath);
		try (
				  InputStream inputStream = new FileInputStream(imgFilePath); 
				  OutputStream outputStream = response.getOutputStream();
	
				//BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				//ServletOutputStream outputStream = response.getOutputStream();
		// ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
				) {

		
			byte[] fileByteArray = inputStream.readAllBytes();
			
			Resource resource = new PathResource(imgFilePath);

			response.setContentType("application/octet-stream");
			//response.setContentType("application/pdf");
			//response.setHeader("Content-Disposition", "attachment; filename=\"" + java.net.URLEncoder.encode(resource.getFilename() + "\"", "UTF-8"));
			
			response.setHeader("Content-Disposition", "attachment; filename*=utf-8''" +
			  java.net.URLEncoder.encode(resource.getFilename(), "UTF-8"));
			 
			response.setContentLength(fileByteArray.length);

			/*少しずつ読み込み*/
			
			
			  /*int bytesRead; 
			  while ((bytesRead = inputStream.read(fileByteArray,0,fileByteArray.length)) > 0) { 
				  outputStream.write(fileByteArray, 0,bytesRead); 
				  }*/
			 
			/*
			 * int bytesRead = -1; while ((bytesRead = inputStream.read(fileByteArray)) !=
			 * 0) { outputStream.write(fileByteArray, 0, bytesRead); }
			 */
			 
			outputStream.write(fileByteArray);
			outputStream.flush();
			//inputStream.close();
			//outputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace(); 
			}
			 
	}
}