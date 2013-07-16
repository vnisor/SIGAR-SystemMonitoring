package com.appiancorp.plugins.lab.systemmonitoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hyperic.sigar.Sigar;

/**
 * Abstract base class providing the required injection and data for implementation specific Monitoring Classes to consume.
 * @author sathya.srinivasan
 * @date 16/07/2013
 */
public abstract class AbstractResourceMonitor {

	Sigar sigar = new Sigar();
	protected HashMap<String, Object> item = new HashMap<String, Object>();;
	protected List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
	
	
	
	/**
	 * Accessor for data element.
	 * @return
	 * @TODO - Can we pass data into this so the info can be appended to an existing collector object?
	 */
	public List<HashMap<String, Object>> getData() {
		data.add(item);
		return data;
	}

	
	
	
}
