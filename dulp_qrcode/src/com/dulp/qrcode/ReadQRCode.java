package com.dulp.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

public class ReadQRCode {

	public static void main(String[] args) throws Exception {
		File file = new File("D:/code/qrcode.png");
		BufferedImage bufferedImgae = ImageIO.read(file);
		QRCodeDecoder codeDecoder = new QRCodeDecoder();
		String result = new String(codeDecoder.decode(new MYQRCodeImage(bufferedImgae)),"gb2312");
		System.out.println(result);	
			
		
	}

}
