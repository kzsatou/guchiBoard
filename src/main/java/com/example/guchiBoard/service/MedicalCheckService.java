package com.example.guchiBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
