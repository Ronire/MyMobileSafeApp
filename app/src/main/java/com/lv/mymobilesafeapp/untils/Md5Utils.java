package com.lv.mymobilesafeapp.untils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.R;
/**
 * md5����
 *
 */
public class Md5Utils {
	public static String md5Password(String password) {

		try {
		
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();

			for (byte b : result) {
				// ������
				int number = b & 0xff;
				String str = Integer.toHexString(number);
				// System.out.println(str);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}
			// ��ʶ��md5���ܺ�Ľ��
			return (buffer.toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
