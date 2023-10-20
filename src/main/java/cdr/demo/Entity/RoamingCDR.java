package cdr.demo.Entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoamingCDR {
	 	private Date roamingStartTime;
	    private Date roamingEndTime;
	    private String roamingLocation;
	    private int sessionDurationHours;
	    private int sessionDurationMinutes;
	    private int dataUsageMB;
	    private String voiceCalls;
	    private int smsSent;
	    private int smsReceived;
	    private int gprsDataUsageMB;
	    private String serviceType;
	    private double chargeAmount;
	    private String callRoutingInformation;
	    private String roamingPartner;
	  

}
