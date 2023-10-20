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
public class MmsCDR {
	private String messageId;
    private String sender;
    private String recipient;
    private Date timestamp;
    private double messageSize;
    private String messageType;
    private String deliveryStatus;
    private String contentType;
    private String contentLocation;
    private String serviceCenterIdentifier;
    private double chargingAmount;
    private String networkInformation;
    private String messageStatus;
    private String subject;

}
