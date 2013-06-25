package com.zzjack.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

public class BufferdFileInput {
	public static String read(String filename) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s ;
		StringBuilder sb = new StringBuilder();
		LinkedList<String> ll = new LinkedList<String>();
		while(null != (s = in.readLine())){
			sb.append(s.toUpperCase()).append("\n");
			ll.add(s);
		}
		in.close();
		System.out.println(sb.toString());
		while(ll.size()>0){
			System.out.println(ll.pollLast());
		}
		return sb.toString();
	}
	public static void main(String[] args) throws IOException {
		read("F:/BufferdFileInput.java");
	}
}
