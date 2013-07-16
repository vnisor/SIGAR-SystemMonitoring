package com.appiancorp.plugins.lab.systemmonitoring;

import org.apache.log4j.Logger;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.Swap;

public class MonitorSwap extends AbstractResourceMonitor implements
		MonitorResource {

	private static final Logger LOG = Logger.getLogger(MonitorSwap.class);
	String[] fields = new String[] {"Total","Used","Free","Page In","Page Out"};
	
	
	@Override
	public void getResourceUsage() {
		LOG.debug("Collecting Swap Info");
		try{
			Swap swap = sigar.getSwap();
			item.put("Total", Sigar.formatSize(swap.getTotal()));
			item.put("Used", Sigar.formatSize(swap.getUsed()));
			item.put("Free", Sigar.formatSize(swap.getFree()));
			item.put("Page In", Sigar.formatSize(swap.getPageIn()));
			item.put("Page Out", Sigar.formatSize(swap.getPageOut()));
		}catch(Exception e){
			LOG.error(e);
		}

	}

}
