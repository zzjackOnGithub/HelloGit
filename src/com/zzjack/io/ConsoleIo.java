package com.zzjack.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class ConsoleIo {

	//scanner�ܺ��á���
	Scanner cin = new Scanner(new BufferedInputStream(System.in));
	
	//��һ�ֲ��ð�sysin��װ��bufferedreader,��������InputStreamReader����ת��ΪReader
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void example() throws IOException{
		int a = cin.nextInt();
		double b = cin.nextDouble();
		BigInteger bi = cin.nextBigInteger();
		String str = cin.nextLine();
//		System.out.print();// cout << ��;
//		System.out.println(); // cout << �� << endl; 
//		System.out.printf(); // ��C�е�printf�÷�����.
		
		
		br.read();
		br.readLine();
		
	}
	
}
