package utils;
import java.util.UUID;
/*
 * 生成随机字符串工具类UUID
 * @author Dulp
 * */
public class UUIDUtils {
public static String getUUID() {
	//产生随机字符串返回
	return UUID.randomUUID().toString().replace("-", "");
}
}
