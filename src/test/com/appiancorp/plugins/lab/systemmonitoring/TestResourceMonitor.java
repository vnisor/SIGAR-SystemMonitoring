package test.com.appiancorp.plugins.lab.systemmonitoring;

import com.appiancorp.plugins.lab.systemmonitoring.MonitorCPU;
import com.appiancorp.plugins.lab.systemmonitoring.MonitorMemory;
import com.appiancorp.plugins.lab.systemmonitoring.MonitorResource;
import com.appiancorp.plugins.lab.systemmonitoring.MonitorSwap;
import com.appiancorp.plugins.lab.systemmonitoring.PrintDataCollector;


public class TestResourceMonitor {
	
	public static void main(String[] args){
		
		MonitorResource r = new MonitorSwap();
		r.getResourceUsage();
		PrintDataCollector.print(r.getData());
		
	}

}
