package cdr.demo.Entity;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoIPConferenceCDR {

	private String meetingId;
    private Date startTime;
    private Date endTime;
    private int callDuration;
    private String hostCallerIP;
    private String hostCallStatus;
    private List<String> participantIPs;
    private List<String> participantStatuses;
    private String callStatus;
    private String callDirection;
    private String callTerminationReason;
}
