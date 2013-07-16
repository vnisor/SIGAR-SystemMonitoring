package com.appiancorp.plugins.lab.systemmonitoring;

import org.apache.log4j.Logger;
import org.hyperic.sigar.SigarNotImplementedException;
import org.hyperic.sigar.Uptime;
import org.hyperic.sigar.util.PrintfFormat;

public class MonitorUptime extends AbstractResourceMonitor implements
		MonitorResource {
	
	private static final Logger LOG = Logger.getLogger(MonitorUptime.class);
	String[] fields = new String[] {"Uptime","Load Average"};
	private static Object[] loadAvg = new Object[3];
	private static PrintfFormat formatter = new PrintfFormat("%.2f, %.2f, %.2f");

	@Override
	public void getResourceUsage() {
		LOG.debug("Collecting Uptime Info");
		try{
			Uptime up = sigar.getUptime();
			item.put("Uptime", formatUptime(up.getUptime()));
			
			double[] avg = sigar.getLoadAverage();
            loadAvg[0] = new Double(avg[0]);
            loadAvg[1] = new Double(avg[1]);
            loadAvg[2] = new Double(avg[2]);

            item.put("Load Average", formatter.sprintf(loadAvg) );
            
		}catch (SigarNotImplementedException e1) {
            LOG.error(e1);
        }catch(Exception e){
			LOG.error(e);
		}

	}
	
	
	
    private static String formatUptime(double uptime) {
        String retval = "";

        int days = (int)uptime / (60*60*24);
        int minutes, hours;

        if (days != 0) {
            retval += days + " " + ((days > 1) ? "days" : "day") + ", ";
        }

        minutes = (int)uptime / 60;
        hours = minutes / 60;
        hours %= 24;
        minutes %= 60;

        if (hours != 0) {
            retval += hours + ":" + minutes;
        }
        else {
            retval += minutes + " min";
        }

        return retval;
    }

  
}
