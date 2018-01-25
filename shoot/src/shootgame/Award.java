package shootgame;
/*
 * 提供奖励杀死敌人获取奖励
 * 
 * */
public interface Award {
int DOUBLE_FIRE = 0;//双倍火力
int LIFE = 1;//增命
/*
 * 获取奖励类型0或者1
 * */
int getType();
}
