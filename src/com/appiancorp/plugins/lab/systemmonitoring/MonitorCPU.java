package com.appiancorp.plugins.lab.systemmonitoring;

import org.apache.log4j.Logger;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarLoader;

/**
 * Collect CPU Details 
 * @author sathya.srinivasan
 * @date 16/07/2013
 *
 */
public class MonitorCPU extends AbstractResourceMonitor implements MonitorResource {
	
	private static final Logger LOG = Logger.getLogger(MonitorCPU.class);
	String[] fields = new String[] {"CPU Model","CPU Vendor", "Total Cores","Speed (Mhz)","Physical CPUs","Cores per CPU","Cache Size","User Time","System Time","Idle Time","Wait Time","Nice Time","Combined","Irq Time","SoftIrq Time","Stolen Time"};

	
	/**
	 * Collects CPU related details
	 */
	public void getResourceUsage(){
		try{
			LOG.debug("Collecting CPU Info");
			CpuInfo cpuInfo = sigar.getCpuInfoList()[0];
			item.put("CPU Model", cpuInfo.getModel());
			item.put("CPU Vendor", cpuInfo.getVendor());
			item.put("Total Cores", cpuInfo.getTotalCores());
			item.put("Speed (Mhz)", cpuInfo.getMhz());
			
			if ((cpuInfo.getTotalCores() != cpuInfo.getTotalSockets()) || (cpuInfo.getCoresPerSocket() > cpuInfo.getTotalCores())) {
				item.put("Physical CPUs", cpuInfo.getTotalSockets());
				item.put("Cores per CPU", cpuInfo.getCoresPerSocket());
		    }

		    if (cpuInfo.getCacheSize() != Sigar.FIELD_NOTIMPL) {
			   item.put("Cache Size", cpuInfo.getCacheSize());
		    }
			
			LOG.debug("Collecting CPU percentage info");
			CpuPerc cpuPerc = sigar.getCpuPerc();
			item.put("User Time", CpuPerc.format(cpuPerc.getUser()));
			item.put("System Time", CpuPerc.format(cpuPerc.getSys()));
			item.put("Idle Time", CpuPerc.format(cpuPerc.getIdle()));
			item.put("Wait Time", CpuPerc.format(cpuPerc.getWait()));
			item.put("Nice Time", CpuPerc.format(cpuPerc.getNice()));
			item.put("Combined", CpuPerc.format(cpuPerc.getCombined()));
			item.put("Irq Time", CpuPerc.format(cpuPerc.getIrq()));
			if (SigarLoader.IS_LINUX){
				LOG.debug("Collecting Linux Specific Details");
				item.put("SoftIrq Time", CpuPerc.format(cpuPerc.getSoftIrq()));
				item.put("Stolen Time", CpuPerc.format(cpuPerc.getStolen()));
			}
			
		}catch(Exception e){
			LOG.error(e);
		}

	}	

}
