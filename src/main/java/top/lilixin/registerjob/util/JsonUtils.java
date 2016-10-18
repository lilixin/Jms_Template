package top.lilixin.registerjob.util;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

/**
 * json工具类
 *  
 */
public class JsonUtils {

    private static final Log log = LogFactory.getLog(JsonUtils.class);

    private static ObjectMapper objectMapper = JacksonObjectMapper.getInstance();

    private static final SimpleDateFormat DATAFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        objectMapper.getSerializationConfig().setDateFormat(DATAFORMAT);
        objectMapper.getDeserializationConfig().setDateFormat(DATAFORMAT);
        // objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, false);
        // objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, false);
    }

    /**
     * json转化成对象
     * 
     * @param json
     * @param cls
     * @return
     * @throws Exception
     */
    public static Object jsonToBean(String json, Class<?> cls) {
        ObjectMapper mapper = JacksonObjectMapper.getInstance();
        try {
            setDateFormat("yyyy-MM-dd HH:mm:ss", mapper);
            Object vo = mapper.readValue(json, cls);
            return vo;
        } catch (Exception e) {
            log.error("jsonToBean error!", e);
        }
        return null;
    }

    public static String bean2Json(Object obj) {
        StringWriter writer = null;
        JsonGenerator gen = null;
        try {
            writer = new StringWriter();
            gen = new JsonFactory().createJsonGenerator(writer);
            objectMapper.writeValue(gen, obj);
            String json = writer.toString();
            return json;
        } catch (Exception e) {
            log.error("beanToJson error!", e);
        } finally {
            try {
                gen.close();
                writer.close();
            } catch (IOException e) {
                log.error("beanToJson closing io error!", e);
            }
        }
        return null;
    }

    public static Object json2Bean(String json, Class<?> cls) {
        try {
            Object vo = objectMapper.readValue(json, cls);
            return vo;
        } catch (Exception e) {
            log.error("jsonToBean error!", e);
        }
        return null;
    }

    /**
     * json转化成带泛型的对象
     * 
     * @param json
     * @param type
     * @return
     */
    public static Object jsonToGenericsBean(String json, JavaType type) {
        ObjectMapper mapper = JacksonObjectMapper.getInstance();
        try {
            setDateFormat("yyyy-MM-dd HH:mm:ss", mapper);
            Object vo = mapper.readValue(json, type);
            return vo;
        } catch (Exception e) {
            log.error("jsonToBean error!", e);
        }
        return null;
    }

    /**
     * json转化成集合
     * 
     * @param json
     * @param collectionType
     * @param elementType
     * @return
     */
    public static Object jsonToCollection(String json, Class<? extends Collection> collectionType, Class<?> elementType) {
        JavaType type = TypeFactory.collectionType(collectionType, elementType);
        return jsonToGenericsBean(json, type);
    }

    /**
     * 对象转换成json
     * 
     * @param obj
     * @return
     */
    public static String beanToJson(Object obj) {
        ObjectMapper mapper = JacksonObjectMapper.getInstance();
        StringWriter writer = null;
        JsonGenerator gen = null;
        try {
            writer = new StringWriter();
            gen = new JsonFactory().createJsonGenerator(writer);
            setDateFormat("yyyy-MM-dd HH:mm:ss", mapper);
            mapper.writeValue(gen, obj);
            String json = writer.toString();
            return json;
        } catch (Exception e) {
            log.error("beanToJson error!", e);
        } finally {
            try {
                gen.close();
                writer.close();
            } catch (IOException e) {
                log.error("beanToJson closing io error!", e);
            }
        }
        return null;
    }

    /**
     * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数.
     */
    public static void setDateFormat(String pattern, ObjectMapper mapper) {
        DateFormat df = new SimpleDateFormat(pattern);
        mapper.getSerializationConfig().setDateFormat(df);
        mapper.getDeserializationConfig().setDateFormat(df);
    }

    /**
     * 将对象转换为HashMap
     */
    public static HashMap<String, Object> desToHashMap(String object) {

        HashMap<String, Object> data = new HashMap<String, Object>();
        ObjectMapper mapper = JacksonObjectMapper.getInstance();
        try {
            Map<?, ?> map = mapper.readValue(object, Map.class);
            Iterator<?> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = String.valueOf(iterator.next());
                Object value = map.get(key);
                data.put(key, value);
            }
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 将对象转换为HashMap update by DXF
     */
    public static Map<Integer, Integer> desToHashMapWithIntKey(String object) {
        HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
        if (StringUtils.isBlank(object)) {
            return data;
        }
        ObjectMapper mapper = JacksonObjectMapper.getInstance();
        try {
            Map<?, ?> map = mapper.readValue(object, Map.class);
            if(map.isEmpty()){
                return data;
            }
            Iterator<?> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                int value = (Integer) map.get(key);
                data.put(Integer.valueOf(key), value);
            }
        } catch (JsonParseException e) {
            return new HashMap<Integer, Integer>();
        } catch (JsonMappingException e) {
            return new HashMap<Integer, Integer>();
        } catch (IOException e) {
            return new HashMap<Integer, Integer>();
        }
        return data;
    }

}
