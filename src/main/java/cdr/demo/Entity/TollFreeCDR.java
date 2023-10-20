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
public class TollFreeCDR {

	private String startTime;
    private String endTime;
    private int callDuration;
    private String callerNumber;
    private String dialedNumber;
    private String callStatus;
    private String destinationDepartment;
    private String callType;
    private double cost;
    private String sourceRegion;
    private String uniqueCallID;
    private String recordingStatus;
    private String agentID;
    private String outcome;

}
