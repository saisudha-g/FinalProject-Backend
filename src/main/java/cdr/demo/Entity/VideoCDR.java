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
public class VideoCDR {
	private String callId;
    private Date startTime;
    private Date endTime;
    private int callDuration;
    private String hostCallerNumber;
    private String hostCallStatus;
    private String participants;
    private String callStatus;
    private String callType;
    private String videoQualityMetrics;
    private String callDirection;
}
