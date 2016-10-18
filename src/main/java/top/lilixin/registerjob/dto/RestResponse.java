package top.lilixin.registerjob.dto;

import java.io.Serializable;

/**
 * WEB层返回给浏览器的是JSON字符串,这个类用来包装返回结果.同时它附加了返回码,和返回信息等
 * 
 * @author lijia
 *
 * @param <T>  
 */
public class RestResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String status;

	private String message;

	private T result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
