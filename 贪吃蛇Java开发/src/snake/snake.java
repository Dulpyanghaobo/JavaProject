package snake;

import javax.swing.JFrame;

public class snake {
public static void main(String[] args) {
	/*
	 * 定义背景大小
	 * */
	JFrame jfr = new JFrame();
	jfr.setBounds(10,10,920,700);
	//定义是否能被改变
	jfr.setResizable(false);
	//点击关闭按钮的时候退出游戏
	jfr.setDefaultCloseOperation(jfr.EXIT_ON_CLOSE);

	
	snakepanel sp = new snakepanel();
	//添加画布
	jfr.add(sp);
	//显示程序
	jfr.setVisible(true);
}
}
