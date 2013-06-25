package com.zzjack.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

public class BufferToText {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws Exception {
		FileChannel fc = 
				new FileOutputStream("file.txt").getChannel();
		fc.write(ByteBuffer.wrap("Buffer to Text ����".getBytes()));
		fc.close();
		fc = new FileInputStream("file.txt").getChannel();
		ByteBuffer bf = ByteBuffer.allocate(BSIZE);
		fc.read(bf);
		bf.flip();
		fc.close();
		//do not work;
		System.out.println(bf.asCharBuffer());
		bf.rewind();//����
		String encoding   = System.getProperty("file.encoding");
		System.out.println("Decoding using "+encoding+" :"+Charset.forName(encoding).decode(bf));
		
		fc = new FileOutputStream("file.txt").getChannel();
		fc.write(ByteBuffer.wrap("English and ����".getBytes("UTF-16BE")));
		fc.close();
		
		fc = new FileInputStream("file.txt").getChannel();
		bf.clear();
		fc.read(bf);
		bf.flip();
		System.out.println(bf.asCharBuffer());
		System.out.println("Decoding using "+encoding+" :"+Charset.forName(encoding).decode(bf));
		//��Ϊ����utf-16be����ģ�����ʧЧ
		fc.close();
		// About charset
		
		SortedMap<String , Charset> charsets = 
				Charset.availableCharsets();
		Iterator<String> it = charsets.keySet().iterator();
		while(it.hasNext()){
			String csName = it.next();
			System.out.print(csName+" :");
			Iterator<String> aliases = 
					charsets.get(csName).aliases().iterator();
			while(aliases.hasNext()){
				System.out.print(aliases.next()+" ");
			}
			System.out.println();
			
		}
	}

}
