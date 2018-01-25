package com.dulp.qrcode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class MYQRCodeImage implements QRCodeImage{
BufferedImage bufferedImgae;

	public MYQRCodeImage() {
	
	}
	public MYQRCodeImage(BufferedImage bufferedImage) {
		this.bufferedImgae = bufferedImage;
	}
	@Override
	public int getHeight() {
		
		return bufferedImgae.getHeight();
	}

	@Override
	public int getPixel(int arg0, int arg1) {

		return bufferedImgae.getRGB(arg0, arg1);
	}

	@Override
	public int getWidth() {

		return bufferedImgae.getWidth();
	}

}
