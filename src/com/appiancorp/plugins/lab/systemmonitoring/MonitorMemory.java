package com.appiancorp.plugins.lab.systemmonitoring;

import org.apache.log4j.Logger;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

/**
 * Collect Memory utilisation Details
 * @author sathya.srinivasan
 * @date 16/07/2013
 */
public class MonitorMemory extends AbstractResourceMonitor implements MonitorResource {
	

	private static final Logger LOG = Logger.getLogger(MonitorMemory.class);

	String[] fields = new String[] {"Total Memory","Total Free","Total Used","Total RAM","Actual Used","Percentage Used","Actual Free","Percentage Free"};
	
	
	@Override
	public void getResourceUsage() {
		LOG.debug("Collecting Memory Info");
		try{
			Mem mem = sigar.getMem();
			item.put("Total Memory", Sigar.formatSize(mem.getTotal()));
			item.put("Total Free", Sigar.formatSize(mem.getFree()));
			item.put("Total Used", Sigar.formatSize(mem.getUsed()));
			
			item.put("Total RAM", Sigar.formatSize(mem.getRam()));
			item.put("Actual Used", Sigar.formatSize(mem.getActualUsed()));
			item.put("Percentage Used", CpuPerc.format(mem.getUsedPercent()/100));
			item.put("Actual Free", Sigar.formatSize(mem.getActualFree()));
			item.put("Percentage Free", CpuPerc.format(mem.getFreePercent()/100));
			
			
		}catch (Exception e){
			LOG.error(e);
		}
		
	}




}
