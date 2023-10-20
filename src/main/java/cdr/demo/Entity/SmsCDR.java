package cdr.demo.Entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsCDR {
	private String timestamp;  
	private String senderNumber;    
	private String receiverNumber;   
	private String messageContent;  
	private Integer messageLength;     
	private String messageType;     
	private String messageStatus;    
	private String deliveryTimestamp;    
	private String messageDirection;    
	private String messageID;    
	private String connectionType;
	
}
