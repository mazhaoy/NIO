package com.mazy.demo.nio;

import java.nio.ByteBuffer;

import org.junit.Test;

public class NioDemo01 {
	
	@Test
	public void test01(){
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		String str = "xxx";
		buf.put(str.getBytes());
		buf.flip();
		byte[] dst = new byte[buf.limit()];
		buf.get(dst,0,buf.limit());
		System.out.println(new String(dst));
	}
	

}
