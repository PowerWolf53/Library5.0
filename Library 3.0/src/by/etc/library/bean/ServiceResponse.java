package by.etc.library.bean;


import java.util.HashMap;

import java.util.Map;

public class ServiceResponse {

	private Map<String,Object> sessionParams = new HashMap<>();
	
	private Map<String,Object> cockieParams = new HashMap<>();
	
	private Map<String,Object> requestParams = new HashMap<>();
	
	public ServiceResponse()
	{
		
	}
	
	public void addSessionParam(String key,Object param)
	{
		sessionParams.put(key, param);
	}
	
	public void addCockieParam(String key,Object param)
	{
		cockieParams.put(key, param);
	}
	
	public void addRequestParams(String key,Object param)
	{
		requestParams.put(key,param);
	}
	
	public Object getSessionParam(String key)
	{
		return sessionParams.get(key);
	}
	
	public Object getCockieParam(String key)
	{
		return cockieParams.get(key);
	}
	
	public Object getRequestParam(String key)
	{
		return requestParams.get(key);
	}
	
	
	
	
	
	
}
