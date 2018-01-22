package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
//定义画布
public class snakepanel extends JPanel implements KeyListener,ActionListener{
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon up = new ImageIcon("up.png");
	
	int [] snakex=new int[750];
	int [] snakey=new int[750];
	//设计食物的位置
	Random rm = new Random();
	//总共一排有34个格子，总共在25为一个格子后面的25表示第一个
	int foodx = rm.nextInt(34)*25+25 ;
	int foody = rm.nextInt(24)*25+75;
	int len = 4;
	int score = 0;
	
	String fangxiang = "R";//R右L左U上D下
	//设计开始按钮
	boolean isStarted = false;
	boolean isFailed = false;
	//监听器
	Timer timer=new Timer(50,this);
	//获取焦点
	public snakepanel() {
		this.setFocusable(true);
		//监听自己的键盘事件
		this.addKeyListener(this);
		initSetup();
		//蛇开始动
		timer.start();
	}
	//图形的意思画笔
	public void paint(Graphics g) {
		this.setBackground(Color.BLUE);
		title.paintIcon(this, g, 25, 11);
		//画背景
		//化成圆的g.fillOval(25, 75, 850, 600);
		g.fillRect(25, 75, 850, 600);
		//画蛇头
		if(fangxiang.equals("R")) {
			right.paintIcon(this, g, snakex[0],snakey[0]);
		}else if(fangxiang.equals("L")) {
			left.paintIcon(this, g, snakex[0],snakey[0]);
		}else if(fangxiang.equals("U")) {
			up.paintIcon(this, g, snakex[0],snakey[0]);
		}else if(fangxiang.equals("D")) {
			down.paintIcon(this, g, snakex[0],snakey[0]);
		}
		//画蛇的身体
		for(int i=1;i<len;i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		//显示开始暂停
		if(!isStarted) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("Please Space to start/Pause", 280, 350);
		}
		if(isFailed) {
			g.setColor(Color.RED);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("Game Over,Please Space to Restart", 280, 350);
		}
		//画出食物
		food.paintIcon(this, g, foodx, foody);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("score: "+score, 750, 45);
		g.drawString("Length: "+len, 600, 45);
	}
	public void initSetup() {
		isStarted = false;
		isFailed = false;
		len = 4;
		snakex[0] =100; 
		snakex[1] =75;
		snakex[2] =50;
		snakex[3] =25;
		snakey[0] =100;
		snakey[1] =100;
		snakey[2] =100;
		snakey[3] =100;
		fangxiang = "R";
	}
	//按键操作
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if(isFailed) {
				initSetup();
			}else {
				isStarted = !isStarted;
			}
			
			//重新画布(不断切换)
			repaint();
		}
		//画头移动的样子实现拐弯
		else if(keyCode == KeyEvent.VK_UP && fangxiang != "D") {
			fangxiang = "U";
		} else if(keyCode == KeyEvent.VK_DOWN && fangxiang != "U") {
			fangxiang = "D";
		} else if(keyCode == KeyEvent.VK_LEFT && fangxiang != "R") {
			fangxiang = "L";
		} else if(keyCode == KeyEvent.VK_RIGHT && fangxiang != "L") {
			fangxiang = "R";
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//设置时间(多少秒重画一次)
		timer.start();
		//1.移动数据
		//移动身体
		if(isStarted && !isFailed) {
			for(int i=len;i>0;i--) {
				snakex[i]=snakex[i-1];
				snakey[i]=snakey[i-1];
			}
			//移动头
			if(fangxiang.equals("R")) {
				snakex[0] = snakex[0]+25;
				if(snakex[0]>850) snakex[0] = 25;
			}else if(fangxiang.equals("L")) {
				snakex[0] = snakex[0]-25;
				if(snakex[0]<25) snakex[0] = 850;
			}else if(fangxiang.equals("U")) {
				snakey[0] = snakey[0]-25;
				if(snakey[0]<75) snakey[0] = 650;
			}else if(fangxiang.equals("D")) {
				snakey[0] = snakey[0]+25;
				if(snakey[0]>650) snakey[0] = 75;
			}
			//吃掉食物加长度而且食物重新出现
			if(snakex[0]==foodx && snakey[0]==foody) {
				len++;
				score++;
				foodx = rm.nextInt(34)*25+25;
				foody = rm.nextInt(25)*25+75;
			}
			//蛇咬到了自己
			for(int i = 1;i<len;i++) {
				if(snakex[0]==snakex[i] &&snakey[0]==snakey[i]) {
					isFailed = true;
				}
			}
		}
		//2.repaint()
		repaint();
	}
}
