package frameworkandroid.tan.com.bottomframework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * MD5����
	 * @param password
	 * @return
	 */
	public static String md5Password(String password){
		// ��Ҫ���ܵ��ַ���
//		String src = "123456";
		StringBuffer sb = new StringBuffer();
		try {
			// ���ܶ���ָ�����ܷ�ʽ
			MessageDigest md5 = MessageDigest.getInstance("md5");
			// ׼��Ҫ���ܵ�����
			byte[] b = password.getBytes();
			// ����
			byte[] digest = md5.digest(b);
			// ʮ�����Ƶ��ַ�
			char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
					'6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
//			StringBuffer sb = new StringBuffer();
			// �����ʮ�����Ƶ��ַ���(ͨ��)
			for (byte bb : digest) {
				sb.append(chars[(bb >> 4) & 15]);
				sb.append(chars[bb & 15]);
			}
			// ��ӡ���ܺ���ַ���
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
				int number = b & 0xff;// ����
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
