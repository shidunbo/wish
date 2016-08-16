package com.mlx.orderpre.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mlx.orderpre.sign.Sign;
import com.mlx.orderpre.utils.ApplicationProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp,src/main/resources")
@ContextHierarchy({ @ContextConfiguration(locations = "classpath:spring/spring-mvc.xml") })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FindTradesApiTest {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	private static final String OPTIONAL = "^\\[\\w+\\]$";

	private static final String MIDBRACKETS = "\\[|\\]";

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testIndex() throws Exception {
		boolean isOption = false;
		String[] keys = ApplicationProperties.getMessage("req.kingdee.trades.get").split(",");
		int index = 0;
		for (String key : keys) {
			isOption = key.matches(OPTIONAL);
			// 判断是否必传
			if (isOption) {
				// 切出[] 中的参数名
				key = key.replaceAll(MIDBRACKETS, "");
				keys[index] = key;
			}
			index++;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("method", "kingdee.trades.get");
		map.put("app_key", "app_key");
		map.put("session", "session");
		map.put("v", "1.0");
		map.put("timestamp", "2008-01-25 20:23:30");
		map.put("sign_method", "md5");
		map.put("use_has_next", "true");
		map.put("tid", "2016052500000432");
		String plain = Sign.getKingdeePlain(map, keys);
		String sign = Sign.MD5Sign(plain);

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/kingdee/index")
				.param("method", "kingdee.trades.get").param("app_key", "app_key").param("session", "session")
				.param("v", "1.0").param("timestamp", "2008-01-25 20:23:30").param("sign_method", "md5")
				.param("use_has_next", "true").param("sign", sign).param("tid", "2016052500000432"));

		MvcResult mrs = result.andDo(MockMvcResultHandlers.print()).andReturn();
		// 返回状态码
		int resultStr = mrs.getResponse().getStatus();

		String content = mrs.getResponse().getContentAsString();

		System.out.println("content[" + content + "]");

		// 如果返回状态相等 就通过
		Assert.assertEquals(200, resultStr);
	}

	@Test
	public void testRefunds() throws Exception {
		boolean isOption = false;
		String[] keys = ApplicationProperties.getMessage("req.kingdee.refunds.get").split(",");
		int index = 0;
		for (String key : keys) {
			isOption = key.matches(OPTIONAL);
			// 判断是否必传
			if (isOption) {
				// 切出[] 中的参数名
				key = key.replaceAll(MIDBRACKETS, "");
				keys[index] = key;
			}
			index++;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("method", "kingdee.refunds.get");
		map.put("app_key", "192.168.1.1");
		map.put("session", "192.168.1.1");
		map.put("v", "1.0");
		map.put("timestamp", "2008-01-25 20:23:30");

		map.put("format", "json");
		map.put("start_time", "2008-01-25 20:23:30");
		map.put("end_time", "2025-01-25 20:23:30");
		map.put("page_no", "1");
		map.put("page_size", "12");
		map.put("use_has_next", "true");
		String plain = Sign.getKingdeePlain(map, keys);
		System.out.println(plain);
		String sign = Sign.MD5Sign(plain);

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/kingdee/index")
				.param("method", "kingdee.refunds.get").param("app_key", "app_key").param("session", "session")
				.param("v", "1.0").param("timestamp", "2008-01-25 20:23:30").param("format", "json")
				.param("sign", sign).param("start_time", "2008-01-25 20:23:30")
				.param("end_time", "2025-01-25 20:23:30").param("page_no", "1").param("page_size", "12")
				.param("use_has_next", "true"));

		MvcResult mrs = result.andDo(MockMvcResultHandlers.print()).andReturn();
		// 返回状态码
		int resultStr = mrs.getResponse().getStatus();

		String content = mrs.getResponse().getContentAsString();

		System.out.println("content[" + content + "]");

		// 如果返回状态相等 就通过
		Assert.assertEquals(200, resultStr);
	}

	@Test
	public void testListGoods() throws Exception {
		boolean isOption = false;
		String[] keys = ApplicationProperties.getMessage("req.kingdee.items.get").split(",");
		int index = 0;
		for (String key : keys) {
			isOption = key.matches(OPTIONAL);
			// 判断是否必传
			if (isOption) {
				// 切出[] 中的参数名
				key = key.replaceAll(MIDBRACKETS, "");
				keys[index] = key;
			}
			index++;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("method", "kingdee.items.get");
		map.put("app_key", "192.168.1.1");
		map.put("session", "192.168.1.1");
		map.put("v", "1.0");
		map.put("timestamp", "2008-01-25 20:23:30");
		map.put("format", "json");
		map.put("num_iid", "177");
		String plain = Sign.getKingdeePlain(map, keys);
		System.out.println(plain);
		String sign = Sign.MD5Sign(plain);

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/kingdee/index")
				.param("method", "kingdee.items.get").param("app_key", "192.168.1.1").param("session", "192.168.1.1")
				.param("v", "1.0").param("timestamp", "2008-01-25 20:23:30").param("format", "json")
				.param("sign", sign).param("num_iid", "177"));

		MvcResult mrs = result.andDo(MockMvcResultHandlers.print()).andReturn();
		// 返回状态码
		int resultStr = mrs.getResponse().getStatus();

		String content = mrs.getResponse().getContentAsString();

		System.out.println("content[" + content + "]");

		// 如果返回状态相等 就通过
		Assert.assertEquals(200, resultStr);
	}
}
