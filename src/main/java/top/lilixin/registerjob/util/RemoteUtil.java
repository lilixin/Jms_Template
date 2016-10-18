package top.lilixin.registerjob.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.lilixin.registerjob.dto.ServerInfo;
import top.lilixin.registerjob.exception.ServiceException;


/**
 * 服务调用类
 * 
 * @author d
 * 2012-12-4
 *
 */
public class RemoteUtil {

    private static final Logger logger = LoggerFactory.getLogger(RemoteUtil.class);
    
    private static RemoteUtil remote = new RemoteUtil();
    
    public static RemoteUtil getInstance() {
        return remote;
    }
    
    public Map<String, Object> callService(String url, Map<String, String> param,int timeout) throws ServiceException {
        
        HttpClient hc = new HttpClient();
        ServerInfo si = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        param.put("NEED_DO_CHECK", "false");
        logger.debug("url:" + url + ",param:" + param);
        try {
            si = hc.sendPost(url, param,timeout);
        } catch (IOException e) {
            logger.error("服务通信异常:" + ",url:" + url + ",param:" + param, e);
            throw new ServiceException(0, "服务通信异常", e.getMessage());
        }
        
         resultMap = JsonUtils.desToHashMap(si.getContent());
        
        if ("0".equals(String.valueOf(resultMap.get("status")))) {
            logger.error("服务请求异常" + ",url:" + url + ",param:" + param + ",服务返回信息:" + resultMap.get("message"));
            throw new ServiceException(0, url + "服务请求异常," + "服务返回信息:" + resultMap.get("message"), si.getContent());
        }
        
        return resultMap;
    }
}
