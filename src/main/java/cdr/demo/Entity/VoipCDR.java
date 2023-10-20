package cdr.demo.Entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoipCDR {
	
	 	private Date callStartTime;
	    private Date callEndTime;
	    private int callDurationMinutes;
	    private String sourceIP;
	    private String destinationIP;
	    private String callDirection;
	    private String callType;
	    private String codecUsed;
	    private String qualityMetrics;
	    private String callTerminationReason;
	    private String callID;
	    private int callSetupTime;
	    private String callStatus;
}
