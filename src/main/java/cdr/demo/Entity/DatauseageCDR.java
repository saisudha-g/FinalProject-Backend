package cdr.demo.Entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor

public class DatauseageCDR {
	 	private Date sessionStartTime;
	    private Date sessionEndTime;
	    private String sessionStatusValue;
	    private double dataUsageVolume;
	    private String serviceType;
	    private String sessionID;
	    private int latency;
	    private int jitter;
	    private double packetLoss;
	    private String contentType;
	    private String protocol;
	    private String roamingStatusValue;
	    private String sessionDirectionValue;
	    private String serviceProvider;
}
