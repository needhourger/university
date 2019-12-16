package sj.db.util;

import java.util.Random;

public class GenerateCode {
	//用于邀请码的随机生成
	public static int code() {
		Random random = new Random();
		int code = random.nextInt(999999);
		return code;
	}

	public static String getBook_id() {
		int  maxNum = 36;
		int i;
		int count = 0;
		char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			    'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };	  
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while(count < 8){
		i = Math.abs(r.nextInt(maxNum));   
		if (i >= 0 && i < str.length) {
			pwd.append(str[i]);
			count ++;
			}
		}
		return pwd.toString();
	}
}
