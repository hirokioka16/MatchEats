package com.example.demo.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisplayImage {

	@ResponseBody
	@GetMapping(value = "/getImage/{id}")
	public HttpEntity<byte[]> img(@PathVariable String id) throws IOException {

	
	//画像があるディレクトリを取得
    String dir ="/usr/share/tomcat/webapps/foodpic/" + id;
    
    InputStream image = new BufferedInputStream(new FileInputStream(dir));
    
    // byteへ変換
 	byte[] b = IOUtils.toByteArray(image);
 		
 		// レスポンスデータとして返却
 		HttpHeaders headers = new HttpHeaders();
 		headers.setContentType(MediaType.IMAGE_JPEG);		
 		headers.setContentLength(b.length);
 		return new HttpEntity<byte[]>(b, headers);
    
}
}

