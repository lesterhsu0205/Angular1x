package com.lester.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder{
	
	@Override
	public String encode(CharSequence rawPassword) {
		return DigestUtils.sha512Hex(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// 會把資料庫也轉一次 所以再加密一次比較
		return encode(rawPassword).equals(encodedPassword);
	}
}
