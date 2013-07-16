package com.appiancorp.plugins.lab.systemmonitoring;

import java.util.HashMap;
import java.util.List;

/**
 * Base Interface for Resource monitoring 
 * @author sathya.srinivasan
 * @date 16/07/2013
 */
public interface MonitorResource {
	
	/*
	 * Create map to generate the resource data
	 */
	public void getResourceUsage();
	
	public List<HashMap<String, Object>> getData();
	

}
