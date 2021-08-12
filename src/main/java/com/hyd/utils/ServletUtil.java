package com.hyd.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet 工具类
 */
public class ServletUtil {

	/**
	 * 将字符串渲染到客户端
	 */
	public static void renderString(HttpServletResponse response, String data) {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
