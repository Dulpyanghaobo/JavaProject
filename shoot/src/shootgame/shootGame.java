package shootgame;
/*
 * 图片加载
 * 
 * */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class shootGame extends JPanel{
public static final int WIDTH = 400;//面板宽
public static final int HEIGHT = 650;//面板高
public static BufferedImage background;
public static BufferedImage start;
public static BufferedImage airplane;
public static BufferedImage bee;
public static BufferedImage bullet;
public static BufferedImage hero0;
public static BufferedImage hero1;
public static BufferedImage pause;
public static BufferedImage gameover;

private FlyingObject[] flyings = {};//敌机数组
private Bullet[] bullets = {};//子弹数组
private Hero hero = new Hero();//英雄机
int flyEnteredIndex = 0;//飞行物入场次数
private Timer timer; //定时器
private int intervel =1000/100;//时间间隔
int shootIndex = 0;//射击次数
private int score =0;//得分
private int state;
public static final int START =0;
public static final int RUNNING =1;
public static final int PAUSE =2;
public static final int GAME_OVER =3;

public shootGame() {
	//初始化一只蜜蜂和一架飞机
	flyings = new FlyingObject[2];
	flyings[0] =new AirPlane();
	flyings[1] =new Bee();
	//初始化一颗子弹
	bullets = new Bullet[1];
	bullets[0] =new Bullet(200,350);
}
@Override
public void paint(Graphics g) {
	g.drawImage(background, 0, 0, null);//画背景图
	paintHero(g);//画英雄机
	paintBullets(g);//画子弹
	paintFlyingObjects(g);//画飞行物
	paintScore(g);//画分数
	paintState(g);
}
//启动执行代码
public void action() {
	timer = new Timer();
	timer.schedule(new TimerTask() {
		@Override
		public void run() {
			enterAction();//飞行物入场
			stepAction();//走一步
			repaint();//重绘调用paint方法
			shootAction();//运行子弹发射
			bangAction();
			outOfBoundsAction();//删除越界飞行物和子弹
			checkGameOverAction();//检查游戏状态
			}
	},intervel,intervel);
	//鼠标监听事件
	MouseAdapter ma = new MouseAdapter() {
		@Override
		public void mouseMoved(MouseEvent e) {//鼠标移动
			if(state == RUNNING) {//运行时移动飞行机
			int x = e.getX();
			int y = e.getY();
			hero.moveTo(x,y);
		}
			}
		@Override
		public void mouseEntered(MouseEvent e) {//鼠标进行
			if(state == PAUSE) {
				state = RUNNING;
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {//鼠标退出
			if(state != GAME_OVER) {
				state =PAUSE;//游戏未结束将其设置为暂停
			}
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {//鼠标点击
			switch(state) {
			case START:
				state = RUNNING;
				break;
			case GAME_OVER://游戏结束清理现场
				flyings =new FlyingObject[0];
				bullets = new Bullet[0];
				hero = new Hero();
				score = 0;
				state = START;
				break;
			}
		}
		
	};
	this.addMouseListener(ma);//处理鼠标点击操作
	this.addMouseMotionListener(ma);//处理鼠标滑动的操作
}
//画飞行物
private void paintFlyingObjects(Graphics g) {
	for(int i=0;i<flyings.length;i++) {
		FlyingObject fo = flyings[i];
		g.drawImage(fo.getImage(), fo.getX(), fo.getY(), null);
	}
}
//画子弹
private void paintBullets(Graphics g) {
	for(int i =0; i<bullets.length;i++) {
		Bullet b = bullets[i];
		g.drawImage(b.getImage(), b.getX(), b.getY(), null);
	}
}
//画英雄机
private void paintHero(Graphics g) {
	g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
}
/*画分数*/
public void paintScore(Graphics g) {
	int x = 10;
	int y = 25;
	Font font = new Font(Font.SANS_SERIF,Font.BOLD, 14);
	g.setColor(new Color(0x3A3B3B));//设置颜色
	g.setFont(font);//设置字体
	g.drawString("SCORE"+score, x, y);//画分数
	y+=20;
	g.drawString("LIFE:"+hero.getLife(), x, y);//画命
}
/*画出游戏状态*/
public void paintState(Graphics g) {
	switch(state) {
	case START:
		g.drawImage(start, 0, 0, null);
		break;
	case PAUSE:
		g.drawImage(pause, 0, 0, null);
		break;
	case GAME_OVER:
		g.drawImage(gameover, 0, 0,null);
		break;
	}
}
/*
 * 随机生成飞行物
 * */
public static FlyingObject nextOne() {
	Random rand = new Random();
	int type = rand.nextInt(20);//只有当随机数为0的时候才能生成蜜蜂
	if(type==0) {
		return new Bee();
	}else {
	return new AirPlane();
}
	}
/*飞行物入场*/
public void enterAction() {
	flyEnteredIndex++;
	if(flyEnteredIndex % 40 == 0) {//400毫秒--10*40
		FlyingObject obj = nextOne();
		flyings = Arrays.copyOf(flyings, flyings.length+1);//扩容
		flyings[flyings.length-1] =obj;//放在最后一位
	}
}
//实现所有的飞行物移动
public void stepAction() {
	//飞行物走一步
	for(int i =0;i<flyings.length;i++) {
		FlyingObject f = flyings[i];
		f.step();
	}
	//子弹走一步
	for(int i =0;i<bullets.length;i++) {
		Bullet b = bullets[i];
		b.step();
	}
	hero.step();
}
//每调用30次发射一次子弹子弹存入bullet[]里面
public void shootAction() {
	shootIndex++;
	if(shootIndex%30==0) {//每30毫秒发射一次子弹
		Bullet[]bs=hero.shoot();
		bullets = Arrays.copyOf(bullets, bullets.length+bs.length);//添加到子弹库里面
		System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);//追加数组
	}
}
/*
 * 子弹和飞行物碰撞检测
 * */
public void bangAction() {
	for(int i =0;i<bullets.length;i++) {//遍历所有的子弹
		Bullet b =bullets[i];
		bang(b);
	}
}
/*
 * 删除越界的飞行物和子弹
 * */
public void outOfBoundsAction() {
	int index = 0;
	//储存活着的飞行物
	FlyingObject [] flyingLives = new FlyingObject[flyings.length];
	for(int i=0;i<flyings.length;i++) {
		FlyingObject f =flyings[i];
		if(!f.outOfBounds()) {
			flyingLives[index++]=f;//不出界的留下
			
		}
	}
	flyings = Arrays.copyOf(flyingLives, index);//将不越界的飞行物留下
	index = 0;//重置为0
	Bullet[] bulletLives = new Bullet[bullets.length];
	for(int i =0;i<bullets.length;i++) {
		Bullet b =bullets[i];
		if(!b.outOfBounds()) {
			bulletLives[index++]=b;
		}
	}
	bullets = Arrays.copyOf(bulletLives, index);//将不越界的子弹留下
}
/*
 * 子弹和飞行物之间的碰撞检查
 * */
public void bang(Bullet bullet) {
	int index =-1;//击中飞行物索引
	for(int i=0;i<flyings.length;i++) {
		FlyingObject fly =flyings[i];
		if(fly.shootBy(bullet)) {//判断是否被击中
			index = i;
			break;
		}
	}
	if(index !=-1) {//有击中的飞行物
		FlyingObject one = flyings[index];//记录击中的飞行物
		FlyingObject temp = flyings[index];//被击中的飞行物和最后一个飞行物交换
		flyings[index] =flyings[flyings.length-1];
		flyings[flyings.length-1] = temp;
		//删除最后一个飞行物(即被击中)
		flyings = Arrays.copyOf(flyings, flyings.length-1);
		//检查one类型如果是敌人,就算分
		if(one instanceof Enemy) {//检查类型如果是敌人加分
			Enemy e = (Enemy) one;//强制类型转换
			score += e.getScore();//加分
		}
		if(one instanceof Award) {//若为奖励则设置奖励
			Award a = (Award) one;
			int type = a.getType();
			switch(type) {
			case Award.DOUBLE_FIRE:
				hero.addDoubleFire();//加双倍火力
			case Award.LIFE:
				hero.addLife();//加命
				break;
			}
		}
	}
}
/*
 * 判断游戏是否结束
 * */
public boolean isGameOver() {
	for(int i=0;i<flyings.length;i++) {
		int index =-1;
		FlyingObject obj =flyings[i];
		if(hero.hit(obj)) {//检查是否相撞
			hero.subtractLife();
			hero.setDoubleLife(0);
		index = i;
		}
		if(index!=-1) {
			FlyingObject t =flyings[index];
			flyings[index] = flyings[flyings.length-1];
			flyings[flyings.length-1] =t;
			flyings = Arrays.copyOf(flyings, flyings.length-1);
		}
	}
	return hero.getLife() <=0;
}
//检查游戏是否结束
public void checkGameOverAction() {
	if(isGameOver()) {
		state=GAME_OVER;
	}
}
//静态块加载
static {
	try {
		background=ImageIO.read(shootGame.class.getResource("background.png"));
		start=ImageIO.read(shootGame.class.getResource("start.png"));
		airplane=ImageIO.read(shootGame.class.getResource("airplane.png"));
		bee=ImageIO.read(shootGame.class.getResource("bee.png"));
		bullet=ImageIO.read(shootGame.class.getResource("bullet.png"));
		hero0=ImageIO.read(shootGame.class.getResource("hero0.png"));
		hero1=ImageIO.read(shootGame.class.getResource("hero1.png"));
		pause=ImageIO.read(shootGame.class.getResource("pause.png"));
		gameover=ImageIO.read(shootGame.class.getResource("gameover.png"));
	} catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException(" 加载图片资源失败",e);
	}
}
public static void main(String[] args) {
	JFrame frame = new JFrame();
	shootGame game = new shootGame();//面板对象
	frame.add(game);
	frame.setSize(WIDTH, HEIGHT);//大小
	frame.setAlwaysOnTop(true);//总是在最上
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
	frame.setLocationRelativeTo(null);//设置窗体初始位置
	frame.setVisible(true);//快速创建程序
	game.action();//开始运行
}

}
