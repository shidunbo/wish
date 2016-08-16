package com.mlx.orderpre.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlx.orderpre.common.AjaxResult;
import com.mlx.orderpre.exception.BusiException;
import com.mlx.orderpre.utils.ApplicationProperties;

@Controller
public class BaseAPI {

	private static Logger log = LoggerFactory.getLogger(BaseAPI.class);

	@ExceptionHandler
	@ResponseBody
	public Map<String, Object> exception(HttpServletRequest request, Exception ex) {

		log.error("拦截到异常信息 ", ex);

		String code = "9999";
		String msg = ApplicationProperties.getMessage(code);

		if (ex instanceof BusiException) {
			BusiException busiException = (BusiException) ex;
			code = busiException.getCode();
			msg = busiException.getMessage();
			if (null == msg || "".equals(msg.trim())) {
				msg = ApplicationProperties.getMessage(code);
			}
		}

		log.error("拦截到异常信息code[{}], msg[{}]", code, msg);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error_response", new AjaxResult(code, msg));

		return map;
	}
}
