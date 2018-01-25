package com.imooc.zxing;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRcode {

	public static void main(String[] args) {
		int width = 300;
		int height =300;
		String format = "png";
		String content = "www.imooc.com";
		//定义二维码参数
		HashMap hash = new HashMap();
		hash.put(EncodeHintType.CHARACTER_SET, "utf-8");//加入字符集
		hash.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);//纠错等级
		hash.put(EncodeHintType.MARGIN, 2);//默认边距
		//生成二维码
		try {
			BitMatrix bitMatrix =new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, width, height,hash);
			Path file = new File("D:/code/img.png").toPath();//建立一个文件格式
			MatrixToImageWriter.writeToPath(bitMatrix, format, file);//二维码生成的路径
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
