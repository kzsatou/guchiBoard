package com.example.guchiBoard.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class DownloadServiceImpl implements DownloadService {
	
	/**
	 * InputStreamでダウンロードしたいファイルを読み込み、OutputStreamでresponseに書き込む
	 * @param response　レスポンスデータ(ファイルの情報)
	 */
	@Override
	public void fileDownload(HttpServletResponse response, String imgFilePath) {
		try (InputStream inputStream = new FileInputStream(imgFilePath);
				OutputStream outputStream = response.getOutputStream();
		/* ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); */
				) {

			byte[] fileByteArray = inputStream.readAllBytes();
			
			Resource resource = new PathResource(imgFilePath);

			response.setContentType("application/octet-stream");
			//response.setHeader("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"");
			response.setHeader("Content-Disposition", "attachment; filename*=utf-8''"
	                + java.net.URLEncoder.encode(resource.getFilename(), "UTF-8"));
			response.setContentLength(fileByteArray.length);

			/*少しずつ読み込み*/
			int size;
            while ((size = inputStream.read(fileByteArray, 0, fileByteArray.length)) > 0) {
            	outputStream.write(fileByteArray, 0, size);
            }
			outputStream.write(fileByteArray);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}