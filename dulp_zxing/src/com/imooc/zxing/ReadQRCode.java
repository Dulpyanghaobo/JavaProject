package com.imooc.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ReadQRCode {

	public static void main(String[] args) {
	try {
		MultiFormatReader formatReader = new MultiFormatReader();
		File file = new File("D:/code/img.png");
		BufferedImage image = ImageIO.read(file);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
		//定义二维码参数
		HashMap hash = new HashMap();
		hash.put(EncodeHintType.CHARACTER_SET, "utf-8");//加入字符集
		Result result = formatReader.decode(binaryBitmap,hash);//读取二维码的值
		System.out.println("解析结果:"+result.toString());
		System.out.println("二维码格式内容:"+result.getBarcodeFormat());
		System.out.println("二维码文本内容:"+result.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

}
