package com.util;

import java.io.File;
import java.io.IOException;

public class FileUtilEx {
	
	public static File createFile(String fileFoder,String fileName) throws IOException{
		return createFile(fileFoder, fileName,true);
	}
	
	public static File createFile(String fileFoder,String fileName,boolean override) throws IOException{
		File foder = new File(fileFoder);
		File file = new File(fileFoder+fileName);
		if(!foder.exists()){
			foder.mkdirs();
		}
		if(!file.exists()||override)
			file.createNewFile();
		return file;
	}
	
	public static boolean isFileExists(String fileName){
		File f = new File(fileName);
		return f.exists();
	}
	
	public static String getPlatformPath(){
		String path = null;
		try {
			path = new File("").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getContextPathOnWeb(){
		String path= null;
		path = FileUtilEx.class.getResource("/").getPath();
		if(path == null)
			throw new RuntimeException("path is null");
		path = path.substring(1,path.indexOf("/defaultroot"));
		return path;
	}
	
	public static void main(String[] args) {
		System.out.println(FileUtilEx.getContextPathOnWeb());
	}
}
