package shootgame;

import java.util.Random;

//敌机是飞行物也是分数
public class AirPlane extends FlyingObject implements Enemy{
private int speed = 2;
	@Override
	public int getScore() {
		return 5;
	}
public AirPlane() {
	this.image = shootGame.airplane;
	width = image.getWidth();
	height = image.getHeight();
	y = -height;
	Random rand = new Random();
	x = rand.nextInt(shootGame.WIDTH-width);
	//x=100;
	//y=100;
}
@Override
public void step() {
	y+=speed;
}
@Override
public boolean outOfBounds() {//越界处理
	
	return y>shootGame.HEIGHT;
}
}
