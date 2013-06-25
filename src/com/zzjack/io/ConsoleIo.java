package com.zzjack.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class ConsoleIo {

	//scanner很好用。。
	Scanner cin = new Scanner(new BufferedInputStream(System.in));
	
	//另一种采用把sysin包装成bufferedreader,必须先用InputStreamReader将其转换为Reader
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void example() throws IOException{
		int a = cin.nextInt();
		double b = cin.nextDouble();
		BigInteger bi = cin.nextBigInteger();
		String str = cin.nextLine();
//		System.out.print();// cout << …;
//		System.out.println(); // cout << … << endl; 
//		System.out.printf(); // 与C中的printf用法类似.
		
		
		br.read();
		br.readLine();
		
	}
	
}
