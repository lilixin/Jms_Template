package top.lilixin.registerjob.util;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Initialization on demand holder 的方式创建jackson ObjectMapper对象，保证多线程下只创建一个实例且延迟加载，充分利用ObjectMapper的特性
 * @author dengxiaofeng
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class JacksonObjectMapper {
	private static class JacksonObjectMapperHolder {
		private static final ObjectMapper jacksonObjectMapperInstatnce =new ObjectMapper();
	}

	public static ObjectMapper getInstance() {
		return JacksonObjectMapperHolder.jacksonObjectMapperInstatnce;
	}
}
