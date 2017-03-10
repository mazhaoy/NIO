package com.mazy.demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class NioDemo2 {
	
	/**
	 * 利用通道完成文件的复制（非直接缓冲区）
	 * @throws Exception
	 */
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
	
	/**
	 * 使用直接缓冲区完成文件的复制(内存映射文件)
	 * @throws Exception
	 */
	@Test
	public void test02() throws Exception{
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		FileChannel ouChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE,StandardOpenOption.READ);
		//内存映射文件
		MappedByteBuffer inChannelMap = inChannel.map(MapMode.READ_ONLY, 0,inChannel.size() );
		MappedByteBuffer ouChannelMap = ouChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		//直接对缓冲区进行数据的读写操作
		byte[] dst = new byte[inChannelMap.limit()];	
		inChannelMap.get(dst);
		ouChannelMap.put(dst);
		
		ouChannel.close();
		inChannel.close();
		
	}
	

}
