package com.example.guchiBoard.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MedicalCheckForm implements Serializable{

	/**
     * ID
     */
    private Long userId;
    
	/**
     * 健康診断年度
     */
    @NotEmpty(message = "健康診断を受診した年度を入力して下さい")
    private int checkYear;
    
    /**
     * PDFの場所
     */
    private String pdfPath;
    
	/**
     * 写真の場所
     */
    private String picturePath;
    
	/**
     * URLの場所
     */
    private String urlPath;
    
    /**
     * 更新日時
     */
    private Date updateDate;
    
    /**
     * 登録日時
     */
    private Date createDate;
    
}
