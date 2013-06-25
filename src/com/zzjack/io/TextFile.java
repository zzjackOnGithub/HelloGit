package com.zzjack.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TextFile extends ArrayList<String>{
	public static String read(String fileName){
		StringBuilder sb = new StringBuilder();
		try{
			BufferedReader in = new BufferedReader(
					new FileReader(new File(fileName).getAbsoluteFile()));
			try{
				String s;
				while((s = in.readLine())!= null){
					sb.append(s);
					sb.append("\n");
				}
			}finally{
				in.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	public static void write(String fileName, String text){
		try{
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try{
				out.print(text);
			}finally{
				out.close();
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static byte[] read(File bFile) throws IOException{
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile));
		try {
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		} finally{
			bf.close();
		}
	}
	
	
	public static void main(String[] args) {
		String fileName = "F:/BufferdFileInput.java";
		String file2String = TextFile.read(fileName);
		Map<Character,Integer> charMap = new HashMap<Character, Integer>();
		Map<Byte, Integer> byteMap = new HashMap<Byte, Integer>();
		char[] file2charArray = file2String.toCharArray();
		byte[] file2Byte = null;
		try {
			file2Byte = TextFile.read(new File(fileName));
			for(int j = 0;j<file2Byte.length;j++){
				byteMap.put(file2Byte[j], 
						byteMap.get(file2Byte[j]) == null? 1:byteMap.get(file2Byte[j])+1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		for(int i = 0;i<file2charArray.length;i++){
			charMap.put(file2charArray[i], 
					charMap.get(file2charArray[i])==null?1:charMap.get(file2charArray[i])+1);
		}
		System.out.println(charMap);
		System.out.println(byteMap);
		for(Entry<Byte, Integer> entry:byteMap.entrySet()){
			System.out.println((char)entry.getKey().byteValue()+" "+entry.getValue());
		}
		
		TextFile.command("echo shit");
	}
	
	public static void command(String command){
		try {
			Process p = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s;
			while(null != (s = results.readLine())){
				System.out.println(s);
			}
		} catch (Exception e) {
			if(!command.startsWith("cmd /c")){
				command("cmd /c"+command);
			}
		}
		
	}
	
}
