package com.zzjack.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MapIo {
	private static final int numOfInt = 4000000;
	private static final int numOfUbuffInts = 200000;
	private abstract static class Tester{
		private String name ;
		public Tester(String name){this.name = name;}
		public void runTest(){
			System.out.println(name+" :");
			try {
				long start = System.nanoTime();
				test();
				double duration = System.nanoTime()-start;
				System.out.format("%.2f\n",duration/1.0e9);

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		public abstract void test() throws IOException;
	}

	private static Tester[] testers = {
		new Tester("Stream Write"){
			public void test() throws IOException{
				DataOutputStream dos = new DataOutputStream(
						new BufferedOutputStream(new FileOutputStream(new File("temp.tmp"))));
				int i = 0;
				while(i<numOfInt){
					dos.writeInt(i);
					if(i<2)System.out.println(i);
					i++;
				}
				dos.close();
			}
		},
		new Tester("Mapped write"){
			public void test() throws IOException{
				FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
				int i = 0;
				while(i<numOfInt){
					ib.put(i);
					i++;
				}
				fc.close();
			}
		},
		new Tester("Stream Read"){

			@Override
			public void test() throws IOException {
				DataInputStream dis = new DataInputStream(
						new BufferedInputStream(new FileInputStream(new File("temp.tmp"))));
				int i = 0;
				while(i++<numOfInt)dis.readInt();
				dis.close();
			}

		},
		new Tester("Mapped read"){

			@Override
			public void test() throws IOException {

				FileChannel fc = new FileInputStream(new File("temp.tmp")).getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size()).asIntBuffer();
				while(ib.hasRemaining()){
					ib.get();
				}
				fc.close();
			}
		},
		new Tester("Strem read wite"){

			@Override
			public void test() throws IOException {
				RandomAccessFile raf = new RandomAccessFile("temp.tmp", "rw");
				raf.writeInt(1);
				System.out.println(raf.readInt());
				raf.seek(raf.length()-4);
				System.out.println("after seek lenth-4:"+"  "+raf.readInt());
				
				int i = 0;
				while(i++<numOfUbuffInts){
					raf.seek(raf.length()-4);
					raf.writeInt(raf.readInt());
				}
				raf.close(); 
			}

		},
		new Tester("Mapped read and write"){

			@Override
			public void test() throws IOException {
				FileChannel fc = new RandomAccessFile("temp.tmp","rw").getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
				ib.put(0);
				for(int i = 1;i<numOfUbuffInts;i++){
					ib.put(ib.get(i-1));
				}
				fc.close();
			}	
			
		},
	};

	public static void main(String[] args) {
		for(Tester t:testers){
			t.runTest();
		}
	}
}
