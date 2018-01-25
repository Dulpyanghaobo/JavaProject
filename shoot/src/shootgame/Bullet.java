package shootgame;

public class Bullet extends FlyingObject{
private int speed =3;//ÒÆ¶¯ËÙ¶È
public Bullet(int x,int y) {
	this.x=x;
	this.y=y;
	this.image=shootGame.bullet;
}
@Override
public void step() {
	y-=speed;
	
}
@Override
public boolean outOfBounds() {
	return y<-height;
}
}
