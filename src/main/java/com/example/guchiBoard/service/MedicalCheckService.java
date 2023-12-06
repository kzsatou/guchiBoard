package com.example.guchiBoard.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.example.guchiBoard.dao.MedicalCheckMapper;
import com.example.guchiBoard.dto.MedicalCheckForm;

/**
 * 投稿 Service
 */
@Service
public class MedicalCheckService {

    /**
     * 投稿 Mapper
     */
    @Autowired
    private MedicalCheckMapper medicalMapper;
    
    /**
     * 健康診断登録
     * @param medical リクエストデータ
     */
    public void addMedical(MedicalCheckForm medicalForm) {
    	medicalMapper.addMedical(medicalForm);
    }
    
    /**
     * アップロード実行処理
     * @param multipartFile
     */
    public String saveImageLocal(MultipartFile mFile) {
      
    	//ファイル名取得
        String fileName = mFile.getOriginalFilename();
        
        //格納先のフルパス ※事前に格納先フォルダ「Uploads」をCドライブ直下に作成しておく
        Path filePath = Paths.get("C:/uploads/" + fileName);
        
        try {
        	Files.copy(mFile.getInputStream(), filePath);
        }catch (IOException e) {
            e.printStackTrace();
    }
        String path = filePath.toString();
        
        return path;
  }
    
    /**
     * パス処理
     * @param multipartFile
     */
	/*
	 * public String searchImage(int userId, int checkYear) { return
	 * medicalMapper.searchImage(userId, checkYear); }
	 */
    public String searchImage(int userId) {	
    	return medicalMapper.searchImage(userId);
    }
    
    /**
     * 公開設定
     * @param year,userID
     */
    public void displayMedical(int userId, int checkYear) {
    	medicalMapper.displayMedical(userId, checkYear);
    }
    
    /**
     * 健康診断の表示
     * @param post リクエストデータ
     */
	public String outputImage(int userId) throws IOException {
		System.out.println("methodの確認"); 
		System.out.println(userId); 
		
		String imgFilePath = searchImage(userId);
		
		String base64Data = "";
		File fileImg = null;
		//該当のデータがあった場合のみファイルを取得しに行く
		
		try {
			fileImg = new File(imgFilePath);
		}catch(NullPointerException e) {
			return base64Data;
		}

		try {
			byte[] byteImg = Files.readAllBytes(fileImg.toPath());
			base64Data = Base64.getEncoder().encodeToString(byteImg);
			//medicalForm = new MedicalCheckForm();
			//model.addAttribute("medicalForm",medicalForm);
			//model.addAttribute("base64Data","data:application/pdf;base64,"+base64Data);
			//model.addAttribute("pdf","data:application/pdf;base64,"+base64Data);
		}catch(IOException e) {
			//例外処理
			base64Data = "";
			return base64Data;
		}
		return base64Data;
	}
	
}
