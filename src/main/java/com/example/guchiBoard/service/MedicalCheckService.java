package com.example.guchiBoard.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
