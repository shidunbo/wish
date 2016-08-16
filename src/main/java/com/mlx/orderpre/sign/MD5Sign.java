package com.mlx.orderpre.sign;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class MD5Sign {

	private static final String ALGORITHM = "MD5";
	
	public static byte[] encode(byte[] data) throws Exception {
		MessageDigest md = MessageDigest.getInstance(MD5Sign.ALGORITHM);
		return md.digest(data);
	}
	
	public static void main(String[] args) throws Exception {
		String str = "美丽行美丽行美丽行美丽行美丽行美丽行美丽行dddddddddddddddddddddfdsfdfswerfdgfdhgtrevxf";
		byte[] data = encode(str.getBytes("UTF-8"));
		String sign = new String(Base64.encodeBase64String(data));
		
	}
}
