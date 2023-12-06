package com.example.guchiBoard.service;

import jakarta.servlet.http.HttpServletResponse;

public interface DownloadService {

	/**
	 * ファイルダウンロード
	 * @param response　レスポンスデータ(ファイルの情報)
	 */
	public void fileDownload(HttpServletResponse response, String imgFilePath);
}
