package com.mlx.orderpre.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mlx.orderpre.dict.ConstEC;
import com.mlx.orderpre.exception.BusiException;
import com.mlx.orderpre.sign.Sign;
import com.mlx.orderpre.utils.ApplicationProperties;
import com.mlx.orderpre.utils.DateTimeUtil;

/**
 * 
 * @author Shidb
 *
 */
public class ParamCheckInterceptor extends HandlerInterceptorAdapter {

	private static Logger log = LoggerFactory.getLogger(ParamCheckInterceptor.class);

	private static final String OPTIONAL = "^\\[\\w+\\]$";

	private static final String NOTVERIFY = "^\\{\\w+\\}$";

	private static final String MIDBRACKETS = "\\[|\\]";

	private static final String PRE = "req.";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		MDC.put("type", "detail");
		this.checkParam(request);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("请求流水号rpId[{}].......end", request.getAttribute(ConstEC.RPID));
		super.postHandle(request, response, handler, modelAndView);
	}

	private void checkParam(HttpServletRequest request) throws Exception {
		long rpId = DateTimeUtil.getTime();
		log.debug("请求流水号rpId[{}], 收到请求参数[{}].......start", rpId, request.getParameterMap());

		Map<String, String[]> map = request.getParameterMap();

		String[] array = null;
		for (String key : map.keySet()) {
			array = map.get(key);
			System.out.println("key= " + key + " , value= " + array[0]);
		}

		request.setAttribute(ConstEC.RPID, rpId);
		String method = ServletRequestUtils.getStringParameter(request, ConstEC.METHOD, "");
		log.debug("{} 开始请求参数合法性校验", method);
		String itfParam = null;
		if ("".equals(method) || method.isEmpty()) {
			log.debug("method[{}] 请求参数不存在", method);
			throw new BusiException("0010", ApplicationProperties.getMessage("0010"));
		}
		// 从配置文件中读取请求接口的相应参数
		itfParam = ApplicationProperties.getMessage(PRE + method);

		if ("".equals(itfParam.trim()) || itfParam.isEmpty()) {
			log.debug("{}[{}] 请求接口不存在!", method, itfParam);
			throw new BusiException("0011", ApplicationProperties.getMessage("0011"));
		}

		// 是否属于非必传参数,配置文件中使用[]来区分非必传参数
		boolean isOption = false;

		// 校验参数值是否合法

		String clientIp = this.getIpAddr(request);
		// 分割请求参数
		String[] keys = itfParam.split(",");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		int index = 0;
		for (String key : keys) {
			// 判断参数是否不校验
			isOption = key.matches(NOTVERIFY);
			if (isOption) {
				continue;
			}
			isOption = key.matches(OPTIONAL);
			// 判断是否必传
			if (isOption) {
				// 切出[] 中的参数名
				key = key.replaceAll(MIDBRACKETS, "");
				parameterMap.put(key, request.getParameter(key));
				keys[index] = key;
				// 判断必传是否为空
			} else if (!StringUtils.isEmpty(request.getParameter(key))) {
				parameterMap.put(key, request.getParameter(key));
			} else {
				// 为空抛出异常
				log.error("日志id[{}]请求参数key[{}]=value[{}]不合法", request.getAttribute(ConstEC.RPID), key,
						request.getParameter(key));
				throw new BusiException("0004", ApplicationProperties.getMessage("0004") + ", key[" + key + "]");
			}

			log.debug("参数key[{}]=value[{}]", key, request.getParameter(key));
			index++;
		}
		String sign = request.getParameter("sign");
		String plain = Sign.getKingdeePlain(parameterMap, keys);
		log.debug("签名串plain[{}]", plain);
		if (!Sign.verify(plain, sign)) {
			log.info("plain[{}], sign[{}] 验签失败!", plain, sign);
			throw new BusiException("0017", ApplicationProperties.getMessage("0017"));
		}

		if (null != clientIp && clientIp.length() < 16) {
			request.setAttribute("clientIp", clientIp);
		}

		log.info("clientIp[{}]", clientIp);

	}

	private String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
