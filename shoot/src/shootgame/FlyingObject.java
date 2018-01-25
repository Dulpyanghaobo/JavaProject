package shootgame;

import java.awt.image.BufferedImage;

public abstract class FlyingObject {
protected int x;
protected int y;
protected int width;
protected int height;
protected BufferedImage image;
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
}
public int getHeight() {
	return height;
}
public void setHeight(int height) {
	this.height = height;
}
public BufferedImage getImage() {
	return image;
}
public void setImage(BufferedImage image) {
	this.image = image;
}
/*
 * 飞行物移动
 * */
public abstract void step();
/*
 * 检查当前飞行器是否被子弹击中
 * */
public boolean shootBy(Bullet bullet) {
	int x = bullet.x;//子弹的横坐标
	int y = bullet.y;//子弹的纵坐标
	return this.x<x && x<this.x+width && this.y<y && y<this.y+height;
}
/*
 * 检查是否出界
 * */
public abstract boolean outOfBounds();
}
