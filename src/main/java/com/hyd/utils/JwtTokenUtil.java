package com.hyd.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.InputStream;
import java.security.*;
import java.util.Date;

public class JwtTokenUtil {
	/**
	 * 密钥生成命令如下，口令为：123456
	 * keytool -genkey -alias jwt -keyalg  RSA -keysize 1024 -validity 365 -keystore jwt.jks
	 */
	private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
	private static PrivateKey privateKey = null;
	private static PublicKey publicKey = null;
	private static final String KEY_TYPE = "JKS";
	private static final String KEY = "123456";
	private static final String KEY_ALIAS = "jwt";

	/**
	 * 初始化
	 */
	static {
		try {
			KeyStore keyStore = KeyStore.getInstance(KEY_TYPE);
			keyStore.load(inputStream, KEY.toCharArray());//加载证书文件
			privateKey = (PrivateKey) keyStore.getKey(KEY_ALIAS, KEY.toCharArray());
			publicKey = keyStore.getCertificate(KEY_ALIAS).getPublicKey();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成token
	 */
	public static String generateToken(String subject, int expirationSeconds, String salt) {
		return Jwts.builder()
				.setSubject(subject)//设置主题
				//指定 jwtTokwn 过期时间
				.setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
//                .signWith(SignatureAlgorithm.HS512, salt) // 不使用公钥私钥
				//私钥签名
				.signWith(SignatureAlgorithm.RS256, privateKey)
				.compact();
	}

	/**
	 * 解析token
	 */
	public static String parseToken(String token, String salt) {
		//获取JWT 负载
		Claims claims = Jwts.parser()
//                    .setSigningKey(salt) // 不使用公钥私钥
				//公钥验证
				.setSigningKey(publicKey)
				.parseClaimsJws(token).getBody();
		//返回负载中 sub 字段（生成JWT token 时，放入的数据）
		return claims.getSubject();
	}
}
