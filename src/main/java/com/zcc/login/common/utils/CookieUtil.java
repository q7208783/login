package com.zcc.login.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZhangChicheng on 2017/11/15.
 */
public class CookieUtil {
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i = 0; i < cookies.length; ++i) {
				if(cookieName.equals(cookies[i].getName())) {
					Cookie cookie = cookies[i];
					if(cookie != null) {
						return cookie.getValue();
					}

					return null;
				}
			}
		}

		return null;
	}

	public static void addCookie(HttpServletResponse response, String key, String value){
		Cookie cookie = new Cookie(key, value);
		response.addCookie(cookie);
	}
}
