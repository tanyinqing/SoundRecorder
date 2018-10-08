package frameworkandroid.tan.com.bottomframework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * MD5加密
	 * @param password
	 * @return
	 */
	public static String md5Password(String password){
		// 需要加密的字符串
//		String src = "123456";
		StringBuffer sb = new StringBuffer();
		try {
			// 加密对象，指定加密方式
			MessageDigest md5 = MessageDigest.getInstance("md5");
			// 准备要加密的数据
			byte[] b = password.getBytes();
			// 加密
			byte[] digest = md5.digest(b);
			// 十六进制的字符
			char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
					'6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
//			StringBuffer sb = new StringBuffer();
			// 处理成十六进制的字符串(通常)
			for (byte bb : digest) {
				sb.append(chars[(bb >> 4) & 15]);
				sb.append(chars[bb & 15]);
			}
			// 打印加密后的字符串
//			System.out.println(sb);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
		/*try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer sb = new StringBuffer();
			for (byte b : result) {
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					sb.append("0");
				}
				sb.append(str);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
			//can't reach
		}*/
	}
}
