package com.example.guchiBoard.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 投稿 Entity
 */
@Data
public class MedicalCheck {
	
	/**
     * ID
     */
    private Long userId;
	
	/**
     * 健康診断年度
     */
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
