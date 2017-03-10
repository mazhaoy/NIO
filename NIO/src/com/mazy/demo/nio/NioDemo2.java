package com.mazy.demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class NioDemo2 {
	
	@Test
	public void test01() throws Exception{
		FileInputStream fis = new FileInputStream("1.jpg");
		FileOutputStream fos = new FileOutputStream("2.jpg");
		//获取通道
		FileChannel inputChannel = fis.getChannel();
		FileChannel outputChannel = fos.getChannel();
		//获取缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		//将通道中的数据存入缓冲区中
		while(inputChannel.read(buf) != -1){
			//切换读取数据的模式
			buf.flip();
			//将缓冲区中的数据写入通道中
			outputChannel.write(buf);
			//清空缓冲区
			buf.clear();
		}
		outputChannel.close();
		inputChannel.close();
		fos.close();
		fis.close();
		
	}
	

}
