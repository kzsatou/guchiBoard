package com.example.guchiBoard.service;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.regions.Region;
import com.amazonaws.services.polly.model.OutputFormat;

public interface PollyService {
	
	/**
	 * メイン処理
	 */
	public void Polly(String text) throws Exception;
	
	/**
	 * 音声出力
	 * @param String text 読み上げテキスト, OutputFormat format 出力のフォーマット(mp3)
	 */
	public InputStream synthesize(String text, OutputFormat format) throws IOException;

}
