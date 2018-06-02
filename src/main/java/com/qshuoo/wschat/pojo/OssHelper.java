package com.qshuoo.wschat.pojo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 
 * @author qshuoo
 *
 */
@Data
public class OssHelper {
	private String fileName;
	private String contentType;
	private byte[] bytes;
	
	public OssHelper(MultipartFile file) throws IOException {
		int nextInt = ThreadLocalRandom.current().nextInt(0, 100000);
		long currentTime = System.currentTimeMillis();
		fileName = String.valueOf(currentTime) + "-" + new DecimalFormat("00000").format(nextInt);
        contentType = file.getContentType();
        bytes = file.getBytes();
    }
		
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	

}
