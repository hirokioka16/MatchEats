package com.example.demo.util;

public class FileUtils {


	public static String getExt(String fileName){

	    if (fileName == null)
	        return null;
	    int point = fileName.lastIndexOf(".");
	    if(point == -1){
	    	return "";
	    }

	    return fileName.substring(point+1);
	}
}