package cdr.demo.Entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoiceCDR {

	
	private String callStartTime;
    private String callEndTime;
    private String callerNumber;
    private String recipientNumber;
    private int callDurationMinutes;
    private String callType;
    private String callQualityMetrics;
    private String callResult;
    private String callID;
    private String callDirection;
    
}
