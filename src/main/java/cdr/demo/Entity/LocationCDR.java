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
public class LocationCDR {
	private String location;
    private double latitude;
    private double longitude;
    private Date timestamp;
    private String serviceType;
    private String accuracy;
    private String providerInfo;
    private String batteryLevel;
    private String deviceIMEI;
}
