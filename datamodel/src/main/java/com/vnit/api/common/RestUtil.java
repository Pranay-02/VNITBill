package com.vnit.api.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class RestUtil {

	private static final SecureRandom random = new SecureRandom();
	private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	static String v_Return;

	public static boolean isValidDateFormat(String format) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		try {
			dateFormat.parse(format);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static String formatDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		try {
			return dateFormat.format(dateFormat.parse(format));
		} catch (ParseException e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public static  String formateDateToString(Date date) {
		DateFormat	formatter = new SimpleDateFormat("dd-MMM-yyyy");  
		String strDate =null;
		try {
			strDate = formatter.format(date);  
		}catch(Exception e) {
			//e.printStackTrace();
		}
	    return strDate;
	}

	public static boolean isNullString(String object) {
		boolean isValid = false;
		try {
			if (object == null) {
				isValid = true;
			} else {
				if (object.trim().isEmpty()) {
					isValid = true;
				}
			}
		} catch (Exception e) {
			//	e.printStackTrace();
		}
		return isValid;
	}

	public static boolean isNull(Object object) {
		boolean isValid = false;
		try {
			if (object == null) {
				isValid = true;
			} else {
				if (object.equals("")) {
					isValid = true;
				}
			}
		} catch (Exception e) {
			//	e.printStackTrace();
		}
		return isValid;
	}

	/**
	 * **** ****
	 *
	 * @param date
	 *            in format dd-MM-yyyy
	 * @return java.util.Date
	 * @throws ParseException
	 */
	public static Date getDate(String date) throws ParseException, Exception {
		DateFormat dm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		if (date.length() < 16 || date.length() > 16) {
			throw new Exception("Date is not formated ");
		}
		return dm.parse(date);
	}

	public static String getFormattedDate(String date, String format) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(dateFormat.parse(date));
	}

	public static boolean isValidDate(String date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			dateFormat.format(dateFormat.parse(date));
			return true;
		} catch (ParseException e) {
			//e.printStackTrace();
			return false;
		}
	}

	public static boolean isSpecialCharacter(String str) {
		boolean flag = false;
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");

		Matcher matcher = pattern.matcher(str);

		if (!matcher.matches()) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	public static boolean stringContainsNumber(String s) {
		return Pattern.compile("[0-9]").matcher(s).find();
	}

	public static boolean numberContainsString(String s) {
		boolean numeric = true;
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			numeric = false;
		}
		return numeric;
	}

	public static String issueToken() {
		Random random = new SecureRandom();
		String token = new BigInteger(159, random).toString(32);
		return token;
	}

	public static String getSecureToken(String timestamp) {
		String secureToken = null;
		try {
			UUID uuid = UUID.randomUUID();
			secureToken = uuid.toString().replaceAll("-", "").toUpperCase();
		} catch (Exception e) {
			//	e.printStackTrace();
		}

		return secureToken;
	}

	public static String generate() {
		byte[] buffer = new byte[20];
		random.nextBytes(buffer);
		return encoder.encodeToString(buffer);
	}

	public static char[] generateOTP(int length) {
		String numbers = "1234567890";
		Random random = new Random();
		char[] otp = new char[length];

		for (int i = 0; i < length; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		return otp;
	}

	public static String hashMD5(final String string) throws UnsupportedEncodingException {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e2) {
			throw new RuntimeException("UTF-8 should be supported?", e2);
		}
		final StringBuilder hex = new StringBuilder(hash.length * 2);
		for (final byte b : hash) {
			if ((b & 0xFF) < 16) {
				hex.append("0");
			}
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	public static synchronized String getUniqueUserId(){
		return UUID.randomUUID().toString();
	}

	public static boolean isValidMobileNo(String mobileNo) {
		try {
			Pattern pattern = Pattern.compile("^[6789]\\d{9}$");
			Matcher matcher = pattern.matcher(mobileNo);

			if (matcher.matches()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return false;
	}
	
	public static String getDateFromString(String date) throws ParseException {
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		return sdf.format(date1);
	}

	@SuppressWarnings("resource")
	public String extractImageFromPath(String fileName) throws IOException {
		
		try {
			String baseImg = "";
			File file = new File(fileName);
			if(file.exists()) {
				FileInputStream fileInputStreamReader = new FileInputStream(file);
					byte[] bytes = new byte[(int)file.length()];
					fileInputStreamReader.read(bytes);
					baseImg = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);				
			}
			return baseImg;
		}catch(Exception e) {
			//	e.printStackTrace();
		}

		return null;
	}



}
