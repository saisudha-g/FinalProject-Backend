package cdr.demo.service;

import java.io.DataOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cdr.demo.Entity.DatauseageCDR;
import cdr.demo.Entity.LocationCDR;
import cdr.demo.Entity.MmsCDR;
import cdr.demo.Entity.Registration;
import cdr.demo.Entity.VoiceCDR;
import cdr.demo.Entity.SmsCDR;
import cdr.demo.Entity.TollFreeCDR;
import cdr.demo.Entity.VoipCDR;
import cdr.demo.Entity.RoamingCDR;
import cdr.demo.Entity.VideoCDR;
import cdr.demo.Entity.VoIPConferenceCDR;
import cdr.demo.repo.Repository;

@Service
public class cdrService {

	@Autowired
	Repository repo;
	public void create(Registration reg) {
		// TODO Auto-generated method stub
		repo.save(reg);
	}
	public List<Registration> findbyphno(String phoneNumber) {
		// TODO Auto-generated method stub
		return repo.findByphoneNumber(phoneNumber) ;
	}
	
	//************************voice CDR******************************************
	public List<VoiceCDR> voiceCDR (int quantity)

	{
			

		    List<VoiceCDR>cdrs = new ArrayList<>();

	        Random rand = new Random();

 

	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        Date currentDate = new Date();

 

	        for (int i = 0; i < quantity; i++)

	        {

	            Date startDate = generateRandomDate(2022, currentDate, rand);

	            String callerPhoneNumber = "+91" + (6 + rand.nextInt(4)) + (rand.nextInt(900000000) + 100000000);

	            String receiverPhoneNumber = "+91" + (6 + rand.nextInt(4)) + (rand.nextInt(900000000) + 100000000);

 

	            String[] callResults = {"Connected", "Busy", "Missed"};

	            String callResult = callResults[rand.nextInt(callResults.length)];

 

	            String callDirection = (callResult.equals("Connected")) ? "Outgoing" : "Incoming";

 

	            int duration = 0;

	            if (callResult.equals("Connected")) {

	                duration = rand.nextInt(7200 - 5) + 5; // Random duration between 5 seconds and 120 minutes

	            }

 

	            Date callStartTime = new Date(startDate.getTime() - duration * 1000);

	            Date callEndTime = startDate;

 

	            String startTimeStr = dateFormat.format(callStartTime);

	            String endTimeStr = dateFormat.format(callEndTime);

 

	            String callID = callerPhoneNumber.substring(3) + "-" + startTimeStr.substring(0, 4)

	                    + startTimeStr.substring(5, 7) + startTimeStr.substring(8, 10)

	                    + startTimeStr.substring(11, 13) + startTimeStr.substring(14, 16) + startTimeStr.substring(17);

 

	            VoiceCDR vcdr = new VoiceCDR();

	            vcdr.setCallStartTime(startTimeStr);

	            vcdr.setCallEndTime(endTimeStr);

	            vcdr.setCallDurationMinutes(duration / 60);

	            vcdr.setCallerNumber(callerPhoneNumber);

	            vcdr.setRecipientNumber(receiverPhoneNumber);

	            vcdr.setCallType("Voice");

	            vcdr.setCallResult(callResult);

	            vcdr.setCallID(callID);

	            vcdr.setCallDirection(callDirection);

 

	            cdrs.add(vcdr);	
	            try{

	            	File cdr=new File("cdrVoice.txt");

	            	FileOutputStream fis = new FileOutputStream(cdr);

	            	DataOutputStream dos = new DataOutputStream(fis);

	            	dos.writeUTF(vcdr.toString());

	            }

	            		

	            catch(Exception e) {

	            	

	            	e.getStackTrace();

	            }



	           }

	        return cdrs;

	}

	public Date generateRandomDate(int startYear, Date endDate, Random rand)

	{

        long startMillis = new Date(startYear - 1900, 0, 1).getTime();

        long endMillis = endDate.getTime();

        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));

        return new Date(randomMillisSinceEpoch);

    }
	//************************Sms CDR******************************************
	public List<SmsCDR> SmsCDR (int quantity){
		 List<SmsCDR> sms = new ArrayList<>();
		 Random rand = new Random();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String[] messages = {
	        		
	        		"Your meeting is scheduled for tomorrow at 2:00 PM.",
    			    "Your project report is due on Friday. Please submit it by then.",
    			    "We have received your job application. Our HR team will be in touch soon.",
    			    "You're invited to our upcoming webinar on marketing strategies. Register now at example.com/webinar.",
    			   
    			    "Your order #12345 has been shipped. You can track it here: example.com/track.",
    			    "We're excited to announce our new product, the XYZ Pro. Check it out at example.com/newproduct.",
    			    "Reminder: Our annual conference is just around the corner. Don't forget to register!",
    			    "Hello! Don't miss our special sale this weekend. Discounts up to 50% off.",
    			    
    			    "Hey there! How's it going?",
    			    "What's up? Any fun plans for the weekend?",
    			    "Hi! Just wanted to say hello and wish you a great day.",
    			    "Hope you're having a fantastic day.",
    			    
    			    "A transaction of $100 was made on your account. If it's not authorized, please contact us immediately.",
    			    "Your account balance is $2,500. Keep track of your finances with our mobile app.",
    			    "Hi there. Don't forget to set up autopay for your monthly bills to avoid late fees.",
    			    "Important: Your loan payment is due on October 20. Please make the payment promptly.",
    			    "Unlock your potential",
    			    "Stay focused and driven",
    			    "Build your network",
    			    "Seize the day with confidence",
    			    "Professionalism at its best",
    			    "Achieve your goals",
    			    "Elevate your career",
    			    "Business success is yours",
    			    "Your success is our priority",
    			    "Innovation and excellence",
    			   
    			    "Congratulations on your recent achievement! Well done!",
		    	    "Happy birthday! Wishing you a day filled with joy and laughter.",
		    	    "Thanks for being a valued customer. We appreciate your business.",
		    	    "Hi! Just a friendly reminder about our meeting tomorrow at 3:00 PM.",
		    	    "Don't forget to bring your ID and passport for the flight. Safe travels!",
		    	    "You're invited to our art exhibition this Saturday. Join us at the gallery.",
		    	    "Important: Your prescription is ready for pickup at the pharmacy.",
		    	    "The event you registered for has been rescheduled to next week. See you then!",
		    	    "We're offering a special discount for loyal customers. Shop now and save.",
		    	    "Have a fantastic weekend ahead! Enjoy some quality time with loved ones.",   
    			    

    			    	// You can access each message using additionalSmsMessages[index]

	        		};

	 

	        for (int i = 0; i < quantity; i++) {
	            // Generate random timestamps within the year 2022-2023
	            Date timestamp = generateRandomDate(2022, new Date(), rand);

	 

	            // Generate random sender and receiver phone numbers in Indian format
	            String senderNumber = generateIndianPhoneNumber(rand);
	            String receiverNumber = generateIndianPhoneNumber(rand);

	 

	            // Select a random message from the array
	            String messageContent = messages[rand.nextInt(messages.length)];

	 

	            // Calculate message length
	            int messageLength = messageContent.length();

	 

	            // Generate a random message type and status
	            String messageType = "SMS";
	            String[] messageStatus = {"Delivered", "Failed"};
	            String messageStatusResult = messageStatus[rand.nextInt(messageStatus.length)];

	 

	            // Generate a random message direction
	            String[] messageDirection = {"Outgoing", "Incoming"};
	            String messageDirectionResult = messageDirection[rand.nextInt(messageDirection.length)];

	 

	            // Generate a unique Message ID
	            String messageID = generateMessageID(rand);

	 

	            // Generate a random Connection Type
	            String[] connectionTypes = {"4G LTE", "5G LTE", "3G LTE"};
	            String connectionType = connectionTypes[rand.nextInt(connectionTypes.length)];

	 

	            // Format the timestamp
	            String timestampStr = dateFormat.format(timestamp);

	 

	            // Initialize the Delivery Timestamp
	            String deliveryTimestampStr = "N/A"; // Default to "N/A"

	 

	            if (messageStatusResult.equals("Delivered")) {
	                // Generate a random delivery timestamp between 1 to 5 minutes greater than Timestamp
	                long timestampMillis = timestamp.getTime();
	                long deliveryTimestampMillis = timestampMillis + (rand.nextInt(5) + 1) * 60 * 1000;
	                Date deliveryTimestamp = new Date(deliveryTimestampMillis);
	                deliveryTimestampStr = dateFormat.format(deliveryTimestamp);
	            }

	 
	            SmsCDR obj=new SmsCDR();

	            obj.setTimestamp(timestampStr);

	            obj.setDeliveryTimestamp(deliveryTimestampStr);

	            obj.setSenderNumber(senderNumber);

	            obj.setReceiverNumber(receiverNumber);

	            obj.setMessageContent(messageContent);

	            obj.setMessageLength(messageLength);

	            obj.setMessageType(messageType);

	            obj.setMessageStatus(messageStatusResult);

	            obj.setMessageDirection(messageDirectionResult);

	            obj.setMessageID(messageID);

	            obj.setConnectionType(connectionType);

	            

	            sms.add(obj);
	            
	            try{

	            	File cdr=new File("cdrSms.txt");

	            	FileOutputStream fis = new FileOutputStream(cdr);

	            	DataOutputStream dos = new DataOutputStream(fis);

	            	dos.writeUTF(obj.toString());

	            }

	            		

	            catch(Exception e) {

	            	

	            	e.getStackTrace();

	            }


	          
	        }
	        return sms;
	    }

	 

	    public static Date generateRandomDate1(int startYear, Date endDate, Random rand) {
	        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
	        long endMillis = endDate.getTime();
	        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
	        return new Date(randomMillisSinceEpoch);
	    }

	 

	    public static String generateIndianPhoneNumber(Random rand) {
	        String[] prefixes = {"6", "7", "8", "9"};
	        String phoneNumber = "+91" + prefixes[rand.nextInt(prefixes.length)];
	        for (int i = 0; i < 8; i++) {
	            phoneNumber += rand.nextInt(10);
	        }
	        return phoneNumber;
	    }

	 

	    public static String generateMessageID(Random rand) {
	        String firstPart = String.format("%010d", rand.nextInt(1000000000));
	        String secondPart = String.format("%08d", rand.nextInt(100000000));
	        String thirdPart = String.format("%06d", rand.nextInt(1000000));
	        return firstPart + "-" + secondPart + "-" + thirdPart;
	    }
	
	  //************************Voip CDR******************************************	
	
	    
	    public List<VoipCDR> VoipCDR (int quantity){
			 List<VoipCDR> voip = new ArrayList<>();

			  Random random = new Random();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        //int recordCount = 10; // Number of CDR records to generate

		 

		        for (int i = 0; i < quantity; i++) {
		            // Generate random call start time (within a date range)
		            long randomStartTimeMillis = System.currentTimeMillis() - random.nextInt(365) * 24 * 60 * 60 * 1000;
		            Date callStartTime = new Date(randomStartTimeMillis);

		 

		            // Calculate call end time based on call duration (between 5 seconds and 2 hours)
		            int callDurationMinutes = random.nextInt(2 * 60 - 1) + 1;
		            long randomEndTimeMillis = randomStartTimeMillis + callDurationMinutes * 60 * 1000;
		            Date callEndTime = new Date(randomEndTimeMillis);

		 

		            // Generate random source and destination IP addresses
		            String sourceIP = "192.168." + random.nextInt(256) + "." + random.nextInt(256);
		            String destinationIP = "203.0." + random.nextInt(256) + "." + random.nextInt(256);

		 

		            // Generate random call direction
		            String callDirection = random.nextBoolean() ? "Incoming" : "Outgoing";

		 

		            // Generate random call type
		            String callType = random.nextBoolean() ? "Voice" : "Video";

		 

		            // Generate random codec used
		            String codecUsed;
		            if (callType.equals("Voice")) {
		                codecUsed = "G." + String.format("%03d", random.nextInt(1000));
		            } else {
		                codecUsed = "H." + String.format("%03d", random.nextInt(1000));
		            }

		            // Generate random quality metrics
		            int jitter = random.nextInt(6) + 5;
		            double packetLoss = (random.nextInt(5) + 3) / 10.0;
		            String qualityMetrics = "Jitter: " + jitter + "ms, Packet Loss: " + packetLoss + "%";

		 

		            // Generate random call termination reason
		            String[] terminationReasons = {"Network issue", "Call completion", "User hang-up"};
		            String callTerminationReason = terminationReasons[random.nextInt(3)];

		 

		            // Generate random call ID
		            String callID = "Call" + random.nextInt(100000);

		 

		            // Generate random call setup time (between 10 and 50 seconds)
		            int callSetupTime = random.nextInt(41) + 10;

		 

		            // Generate random call status
		            String callStatus = random.nextBoolean() ? "Connected" : "Disconnected";

		            VoipCDR obj1=new VoipCDR();
		            
		            obj1.setCallStartTime(callStartTime);
		            obj1.setCallEndTime(callEndTime);
		            obj1.setCallDurationMinutes(callDurationMinutes);
		            obj1.setSourceIP(sourceIP);
		            obj1.setDestinationIP(destinationIP);
		            obj1.setCallDirection(callDirection);
		            obj1.setCallType(callType);
		            obj1.setCodecUsed(codecUsed);
		            obj1.setQualityMetrics(qualityMetrics);
		            obj1.setCallTerminationReason(callTerminationReason);
		            obj1.setCallID(callID);
		            obj1.setCallSetupTime(callSetupTime);
		            obj1.setCallStatus(callStatus);
		            
		            
		            voip.add(obj1);
		            
		            try{

		            	File cdr=new File("cdrVoip.txt");

		            	FileOutputStream fis = new FileOutputStream(cdr);

		            	DataOutputStream dos = new DataOutputStream(fis);

		            	dos.writeUTF(obj1.toString());

		            }

		            		

		            catch(Exception e) {

		            	

		            	e.getStackTrace();

		            }


		          
		            
		        }
	return voip;
	

}
	  //*************************RoamingCDR**************************************
	    
	    public List<RoamingCDR> RoamingCDR (int quantity){
	    	List<RoamingCDR> roaming = new ArrayList<>();

	        Random random = new Random();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        String[] callRoutingInformation = {"Routed through Local Exchange", "Routed through Roaming Gateway"};
	        String[] domesticRoamingPartners = {
	            "Airtel (Karnataka)", "Jio (Maharashtra)", "Vodafone (Delhi)", "Idea (Andhra Pradesh)",
	            "BSNL (Kerala)", "Tata Docomo (Gujarat)", "Reliance (Bihar)"
	        };
	        String[] internationalRoamingPartners = {
	            "AT&T (USA)", "Vodafone UK", "Telstra (Australia)", "Orange (France)", "T-Mobile (Germany)"
	        };

	        for (int i = 0; i < quantity; i++) {
	            long randomStartTimeMillis = System.currentTimeMillis() - random.nextInt(365) * 24 * 60 * 60 * 1000;
	            Date roamingStartTime = new Date(randomStartTimeMillis);

	            int roamingDurationMinutes = random.nextInt(24 * 60 - 1) + 1;
	            long randomEndTimeMillis = randomStartTimeMillis + roamingDurationMinutes * 60 * 1000;
	            Date roamingEndTime = new Date(randomEndTimeMillis);

	            int mcc;
	            String roamingLocation;

	            if (random.nextBoolean()) {
	                mcc = 404; // MCC for India
	                int mnc = random.nextInt(100); // Random MNC
	                roamingLocation = "MCC: " + mcc + " (India), MNC: " + String.format("%02d", mnc) + " (Operator)";
	            } else {
	                mcc = 250 + random.nextInt(100); // Random MCC for international
	                int mnc = random.nextInt(100); // Random MNC
	                roamingLocation = "MCC: " + mcc + " (International), MNC: " + String.format("%02d", mnc) + " (Operator)";
	            }

	            long durationMillis = randomEndTimeMillis - randomStartTimeMillis;
	            int sessionDurationHours = (int) (durationMillis / (60 * 60 * 1000));

	            int callCount = random.nextInt(4); // Up to 3 calls in a session
	            int smsSent = 0;
	            int smsReceived = 0;
	            String voiceCalls = "";

	            String[] serviceTypes = {"Voice", "Data", "SMS"};
	            String serviceType = serviceTypes[random.nextInt(3)];

	            if ("Voice".equals(serviceType)) {
	                smsSent = 0;
	                smsReceived = 0;
	            } else if ("SMS".equals(serviceType)) {
	                callCount = 0;
	            }

	            for (int j = 0; j < callCount; j++) {
	                String callType = random.nextBoolean() ? "Outgoing" : "Incoming";
	                int callDuration = random.nextInt(25) + 5; // Call duration between 5 and 30 minutes
	                String phoneNumber;
	                String routingPartner;

	                if (roamingLocation.contains("India")) {
	                    phoneNumber = "+91 " + (random.nextInt(900) + 100) + " " + String.format("%4d", random.nextInt(10000));
	                    routingPartner = domesticRoamingPartners[random.nextInt(domesticRoamingPartners.length)];
	                } else {
	                    phoneNumber = "+1 " + (random.nextInt(900) + 100) + " " + String.format("%4d", random.nextInt(10000));
	                    routingPartner = internationalRoamingPartners[random.nextInt(internationalRoamingPartners.length)];
	                }

	                voiceCalls += "   - Call " + (j + 1) + ": Duration " + callDuration + " minutes, Destination " + phoneNumber + " (" + callType + "), Routing Partner: " + routingPartner + "\n";
	            }

	            int dataUsageMB = (callCount + smsSent) * 2; // 2MB per call or message
	            int gprsDataUsageMB = random.nextInt(100) + 1;
	            String chargeCurrency = roamingLocation.contains("India") ? "INR" : "USD";
	            double chargeAmount = random.nextDouble() * 100.0; // Up to 2 decimal places


		            
		            RoamingCDR r=new RoamingCDR();
		            r.setRoamingStartTime(roamingStartTime);
		            r.setRoamingEndTime(roamingEndTime);
		            r.setRoamingLocation(roamingLocation);
		            r.setSessionDurationHours(sessionDurationHours);
		            r.setDataUsageMB(dataUsageMB);
		            r.setVoiceCalls(voiceCalls);
		            r.setSmsSent(smsSent);
		            r.setSmsReceived(smsReceived);
		            r.setGprsDataUsageMB(gprsDataUsageMB);
		            r.setServiceType(serviceType);
		            r.setCallRoutingInformation(callRoutingInformation[roamingLocation.contains("India") ? 0 : 1]);
		            r.setRoamingPartner(roamingLocation.contains("India") ? "Domestic Roaming Partner" : "International Roaming Partner");
		            
		            roaming.add(r);
		            
		            try{

		            	File cdr=new File("cdrroaming.txt");

		            	FileOutputStream fis = new FileOutputStream(cdr);

		            	DataOutputStream dos = new DataOutputStream(fis);

		            	dos.writeUTF(r.toString());

		            }

		            		

		            catch(Exception e) {

		            	

		            	e.getStackTrace();

		            }


		          
		        }
				return roaming;
			 }
	    
	    //***************************DatauseageCDR********************************
	    
	    public List<DatauseageCDR> DatauseageCDR (int quantity){
	    	List<DatauseageCDR> data = new ArrayList<>();
	    	 Random random = new Random();
	         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	         // Number of data usage CDR entries to generate
	         //int numberOfEntries = 10;

	         // Sample real service provider names
	         String[] serviceProviders = {
	             "Verizon Wireless",
	             "AT&T",
	             "T-Mobile",
	             "Sprint",
	             "Vodafone",
	             "Telefonica",
	             "Orange",
	             "Airtel",
	             "EE",
	             "Three",
	             "O2",
	         };

	         for (int i = 0; i < quantity; i++) {
	             // Generate Session Start Time
	             Date sessionStartTime = new Date(1672569015000L + random.nextInt(7200000)); // Random time within 2 hours
	             // Generate Session End Time
	             Date sessionEndTime;
	             String sessionStatusValue;
	             if (random.nextBoolean()) {
	                 // Successful session
	                 sessionEndTime = new Date(sessionStartTime.getTime() + 900000 + random.nextInt(4500000)); // Within 3-4 seconds
	                 sessionStatusValue = "Success";
	             } else {
	                 // Failed session
	                 sessionEndTime = new Date(sessionStartTime.getTime() + 4000 + random.nextInt(1000)); // Within 4-5 seconds
	                 sessionStatusValue = "Failed";
	             }

	             // Generate Data Usage Volume (between 5 MB and 500 MB)
	             double dataUsageVolume = 5.0 + random.nextDouble() * (500.0 - 5.0);

	             // Generate Service Type
	             String[] serviceTypes = {"3G", "4G", "5G", "Wi-Fi"};
	             String serviceType = serviceTypes[random.nextInt(serviceTypes.length)];

	             // Generate Session ID
	             String sessionID = "Session" + (10000 + random.nextInt(90000));

	             // Generate QoS Metrics
	             int latency = 20 + random.nextInt(41); // Random latency between 20ms and 60ms
	             int jitter = 5 + random.nextInt(21); // Random jitter between 5ms and 25ms
	             double packetLoss = 0.2 + random.nextDouble() * (0.6 - 0.2);

	             // Generate Content Type and Protocol based on service type
	             String contentType, protocol;
	             if (serviceType.equals("3G") || serviceType.equals("4G") || serviceType.equals("5G")) {
	                 contentType = "Video Streaming";
	                 protocol = (random.nextBoolean()) ? "RTMP" : "HTTP";
	             } else if (serviceType.equals("Wi-Fi")) {
	                 contentType = "Web Browsing";
	                 protocol = (random.nextBoolean()) ? "HTTP" : "HTTPS";
	             } else {
	                 contentType = "Email";
	                 protocol = "SMTP";
	             }

	             // Generate Roaming Status (Roaming or Local)
	             String[] roamingStatus = {"Roaming", "Local"};
	             String roamingStatusValue = roamingStatus[random.nextInt(roamingStatus.length)];

	             // Generate Session Direction (Outgoing or Incoming)
	             String[] sessionDirection = {"Outgoing", "Incoming"};
	             String sessionDirectionValue = sessionDirection[random.nextInt(sessionDirection.length)];

	             // Select a random service provider name from the array
	             String serviceProvider = serviceProviders[random.nextInt(serviceProviders.length)];

	             // Print the generated Data Usage CDR entry
	            DatauseageCDR d=new DatauseageCDR();
	            
	            d.setSessionStartTime(sessionStartTime);
	            d.setSessionEndTime(sessionEndTime);
	            d.setDataUsageVolume(dataUsageVolume);
	            d.setServiceType(serviceType);
	            d.setSessionID(sessionID);
	            d.setJitter(jitter);
	            d.setLatency(latency);
	            d.setPacketLoss(packetLoss);
	            d.setContentType(contentType);
	            d.setProtocol(protocol);
	            d.setServiceProvider(serviceProvider);
	            d.setRoamingStatusValue(roamingStatusValue);
	            d.setSessionDirectionValue(sessionDirectionValue);
	            d.setSessionStatusValue(sessionStatusValue);
	            
	            data.add(d);
	            
	            try{

	            	File cdr=new File("cdrdatauseage.txt");

	            	FileOutputStream fis = new FileOutputStream(cdr);

	            	DataOutputStream dos = new DataOutputStream(fis);

	            	dos.writeUTF(d.toString());

	            }

	            		

	            catch(Exception e) {

	            	

	            	e.getStackTrace();

	            }


	          
	            
	         }
	         return data;
	    }
	  //*******************************LocationCDR********************************
	    public List<LocationCDR> LocationCDR (int quantity){
	    	List<LocationCDR> location = new ArrayList<>();
	    	
	    	Random random = new Random();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	 

	        // List of example locations (cities or regions)
	        String[] locations = {
	            "San Francisco, CA, USA",
	            "Los Angeles, CA, USA",
	            "London, UK",
	            "New York, NY, USA",
	            "Ottawa, ON, Canada",
	            "Mumbai, India",
	            "Delhi, India",
	            "Bangalore, India",
	            "Sydney, Australia",
	            "Tokyo, Japan",
	            "Paris, France",
	            "Berlin, Germany"
	        };

	 

	        // List of example service types and provider information
	        String[] serviceTypes = {
	            "GPS",
	            "Cell Tower Triangulation",
	            "Wi-Fi Positioning",
	            "IP Geolocation",
	            "Bluetooth Beacons",
	            "GLONASS",
	            "Galileo",
	            "GNSS",
	            "AGPS"
	        };

	 

	        String[] providerInfo = {
	            "GPS Satellite",
	            "Cellular Network",
	            "Wi-Fi Networks",
	            "Internet Service Provider",
	            "Bluetooth Beacons",
	            "GNSS Signal",
	            "Local Cell Tower",
	            "Satellite Broadband",
	            "WiFi Hotspot"
	        };

	 

	        for (int i = 0; i < quantity; i++) {
	            int locationIndex = random.nextInt(locations.length);
	            double latitude = random.nextDouble() * 180 - 90; // Generate random latitude between -90 and 90
	            double longitude = random.nextDouble() * 360 - 180; // Generate random longitude between -180 and 180
	            String locationString = locations[locationIndex];

	 

	            // Generate a random timestamp
	            long randomTimestamp = System.currentTimeMillis() - (random.nextInt(24 * 60 * 60 * 1000)); // Within the past 24 hours
	            Date timestamp = new Date(randomTimestamp);

	 

	            // Randomly select service type and provider information
	            String selectedServiceType = serviceTypes[random.nextInt(serviceTypes.length)];
	            String accuracy = (random.nextInt(10) + 1) + " meters"; // Accuracy between 1 and 10 meters
	            String selectedProviderInfo = providerInfo[random.nextInt(providerInfo.length)];
	            String batteryLevel = random.nextInt(101) + "%"; // Random battery level between 0% and 100%
	            String deviceIMEI = String.valueOf(100000000000000L + (long)(random.nextDouble() * 900000000000000L));
	            
	            LocationCDR l=new LocationCDR();
	            
	            l.setLocation(locationString);
	            l.setLatitude(latitude);
	            l.setLongitude(longitude);
	            l.setTimestamp(timestamp);
	            l.setServiceType(selectedServiceType);
	            l.setAccuracy(accuracy);
	            l.setProviderInfo(selectedProviderInfo);
	            l.setBatteryLevel(batteryLevel);
	            l.setDeviceIMEI(deviceIMEI);
	            
	            location.add(l);
	            
	            try{

	            	File cdr=new File("cdrlocation.txt");

	            	FileOutputStream fis = new FileOutputStream(cdr);

	            	DataOutputStream dos = new DataOutputStream(fis);

	            	dos.writeUTF(l.toString());

	            }

	            		

	            catch(Exception e) {

	            	

	            	e.getStackTrace();

	            }


	          
	 
	        }
	            return location;
	        }
	    
//******************************MMSCDR**************************************
	    
	    public List<MmsCDR> MmsCDR (int quantity){
	    	List<MmsCDR> mms = new ArrayList<>();
	    	

	    	        Random random = new Random();

	    	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    	 

	    	        for (int i = 0; i < quantity; i++) {

	    	            String messageId = Long.toString(random.nextLong() & Long.MAX_VALUE);

	    	            String sender = "+91" + (7_000_000_000L + random.nextInt(1_000_000_000));

	    	            String recipient = "+91" + (7_000_000_000L + random.nextInt(1_000_000_000));

	    	            Date timestamp = (new Date(System.currentTimeMillis() - (random.nextInt(24 * 60 * 60 * 1000)))); // Within the past 24 hours

	    	            double messageSize = random.nextDouble() * 5.0 + 0.5; // Message size between 0.5 MB and 5.5 MB
	    	            messageSize = Math.round(messageSize * 100.0) / 100.0;

	    	            String messageType = random.nextBoolean() ? "Outgoing" : "Incoming";

	    	            String deliveryStatus = random.nextBoolean() ? "Delivered" : "Failed";

	    	            String contentType = getRandomContentType();

	    	            String contentLocation = random.nextBoolean() ? "http://example.com/" + getRandomMediaFile(contentType) : "-";

	    	            String serviceCenterIdentifier = "SC" + (10_000 + random.nextInt(90_000));

	    	            double chargingAmount = random.nextDouble() * 1.5 + 0.1; // Charging amount between $0.10 and $1.60

	    	            String networkInformation = "Cell Tower " + (100 + random.nextInt(900));

	    	            String messageStatus = getMessageStatus(messageType, deliveryStatus);

	    	            String subject = getRandomSubject();

	    	            MmsCDR m=new MmsCDR();

	    		    	 m.setMessageId(messageId);

	    		    	 m.setSender(sender);

	    		    	 m.setRecipient(recipient);

	    		    	 m.setTimestamp(timestamp);

	    		    	 m.setMessageSize(messageSize);

	    		    	 m.setMessageType(messageType);

	    		    	 m.setDeliveryStatus(deliveryStatus);

	    		    	 m.setContentType(contentType);

	    		    	 m.setContentLocation(contentLocation);

	    		    	 m.setServiceCenterIdentifier(serviceCenterIdentifier);

	    		    	 m.setChargingAmount(chargingAmount);

	    		    	 m.setNetworkInformation(networkInformation);

	    		    	 m.setMessageStatus(messageStatus);

	    	           mms.add(m);
	    	           try{

	   	            	File cdr=new File("cdrmms.txt");

	   	            	FileOutputStream fis = new FileOutputStream(cdr);

	   	            	DataOutputStream dos = new DataOutputStream(fis);

	   	            	dos.writeUTF(m.toString());

	   	            }

	   	            		

	   	            catch(Exception e) {

	   	            	

	   	            	e.getStackTrace();

	   	            }

	    	        }
	    	        return mms;

	    	    }

	    	 

	    	    private static String getRandomContentType() {

	    	        String[] contentTypes = {"Image", "Video", "Audio", "Text"};

	    	        return contentTypes[new Random().nextInt(contentTypes.length)];

	    	    }

	    	 

	    	    private static String getRandomMediaFile(String contentType) {

	    	        switch (contentType) {

	    	            case "Image":

	    	                return "image" + (1 + new Random().nextInt(5)) + ".jpg";

	    	            case "Video":

	    	                return "video" + (1 + new Random().nextInt(5)) + ".mp4";

	    	            case "Audio":

	    	                return "audio" + (1 + new Random().nextInt(5)) + ".mp3";

	    	            default:

	    	                return "text.txt";

	    	        }

	    	    }

	    	 

	    	    private static String getMessageStatus(String messageType, String deliveryStatus) {

	    	        if (messageType.equals("Outgoing")&& deliveryStatus.equals("Delivered")) {

	    	            return "Delivered";

	    	        } else if(messageType.equals("Outgoing")&& deliveryStatus.equals("failed")){

	    	            return "failed";

	    	        }

	    	        else if (messageType.equals("Incoming") && deliveryStatus.equals("Delivered")) {

	    	            return "Received";

	    	        } else if(messageType.equals("Outgoing")) {

	    	            return "Draft";

	    	        }

	    	        else{

	    	            return "failed";

	    	        }

	    	    }

	    	 

	    	    private static String getRandomSubject() {

	    	        String[] subjects = {"Family Vacation", "Birthday Party", "Business Proposal", "Holiday Greetings", "Meeting Agenda", "Travel Diary"};

	    	        return subjects[new Random().nextInt(subjects.length)];

	    	    }
//************************VideoCDR********************************************
	    	    public List<VideoCDR> VideoCDR(int quantity) {
	    	        List<VideoCDR> videoCDRs = new ArrayList<>();
	    	        Random rand = new Random();

	    	        for (int j = 0; j < quantity; j++) {
	    	            String callId = "VC" + String.format("%05d", new Random().nextInt(100000));
	    	            int callDuration = new Random().nextInt(115) + 5;
	    	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	            Date startTime = new Date(System.currentTimeMillis() - (long) callDuration * 60 * 1000);
	    	            Date endTime = new Date(System.currentTimeMillis());

	    	            String hostCallerNumber = "+91" + (6 + rand.nextInt(4)) + (rand.nextInt(900000000) + 100000000);
	    	            String hostCallStatus = getRandomCallStatus();

	    	            int numParticipants = new Random().nextInt(5) + 1;
	    	            String[] participantNumbers = new String[numParticipants];
	    	            String[] participantStatus = new String[numParticipants];

	    	            for (int i = 0; i < numParticipants; i++) {
	    	                participantNumbers[i] = "+91" + (6 + rand.nextInt(4)) + (rand.nextInt(900000000) + 100000000);
	    	                participantStatus[i] = getRandomCallStatus();
	    	            }

	    	            String callStatus = getRandomCallStatus();

	    	            String resolution = generateRandomResolution();
	    	            String frameRate = generateRandomFrameRate();
	    	            double bitrate = getRandomBitrate();

	    	            // Create a VideoCDR object and set its properties
	    	            VideoCDR v = new VideoCDR();
	    	            v.setCallId(callId);
	    	            v.setStartTime(startTime);
	    	            v.setEndTime(endTime);
	    	            v.setCallDuration(callDuration);
	    	            v.setHostCallerNumber(hostCallerNumber);
	    	            v.setHostCallStatus(hostCallStatus);

	    	            // Create a participant list and set it to the VideoCDR object
	    	            StringBuilder participantList = new StringBuilder();
	    	            for (int i = 0; i < numParticipants; i++) {
	    	                participantList.append("  - Participant ").append(i + 1).append(": ").append(participantNumbers[i])
	    	                        .append(" (").append(participantStatus[i]).append(")").append("\n");
	    	            }
	    	            v.setParticipants(participantList.toString());

	    	            v.setCallType("Video Conference");
	    	            v.setVideoQualityMetrics("Resolution: " + resolution + "\n" +
	    	                    "Frame Rate: " + frameRate + "\n" +
	    	                    "Packet Loss Rate: " + getRandomPacketLossRate() + "\n" +
	    	                    "Bitrate: " + String.format("%.2f", bitrate) + " Mbps");

	    	            videoCDRs.add(v);
	    	            try{

	    	            	File cdr=new File("cdrvideo.txt");

	    	            	FileOutputStream fis = new FileOutputStream(cdr);

	    	            	DataOutputStream dos = new DataOutputStream(fis);

	    	            	dos.writeUTF(v.toString());

	    	            }

	    	            		

	    	            catch(Exception e) {

	    	            	

	    	            	e.getStackTrace();

	    	            }
	    	        }

	    	        return videoCDRs;
	    	    }

	    	    private static String generatePhoneNumber() {
	    	        String[] countryCodes = {"+91", "+61"};
	    	        String countryCode = countryCodes[new Random().nextInt(countryCodes.length)];
	    	        String phoneNumber = "";
	    	        for (int i = 0; i < 10; i++) {
	    	            phoneNumber += String.valueOf(new Random().nextInt(10));
	    	        }
	    	        return countryCode + phoneNumber;
	    	    }

	    	    private static String generateParticipantList(String[] participants, String[] statuses) {
	    	        StringBuilder participantList = new StringBuilder();
	    	        for (int i = 0; i < participants.length; i++) {
	    	            participantList.append("  - Participant ").append(i + 1).append(": ").append(participants[i])
	    	                    .append(" (").append(statuses[i]).append(")").append("\n");
	    	        }
	    	        return participantList.toString();
	    	    }

	    	    private static String getRandomCallStatus() {
	    	        String[] statuses = {"Connected", "Busy","Disconnected"};
	    	        return statuses[new Random().nextInt(statuses.length)];
	    	    }

	    	    private static String getRandomCallDirection() {
	    	        String[] directions = {"Outgoing", "Incoming"};
	    	        return directions[new Random().nextInt(directions.length)];
	    	    }

	    	    private static String getRandomPacketLossRate() {
	    	        double rate = new Random().nextDouble() * 5.0; // Random packet loss rate between 0 and 5
	    	        return String.format("%.1f%%", rate);
	    	    }

	    	    private static double getRandomBitrate() {
	    	        return new Random().nextDouble() * 9.0 + 1.0; // Random bitrate between 1 and 10 Mbps
	    	    }

	    	    private static String generateRandomResolution() {
	    	        String[] resolutions = {"1280x720", "1920x1080", "640x480"};
	    	        return resolutions[new Random().nextInt(resolutions.length)];
	    	    }

	    	    private static String generateRandomFrameRate() {
	    	        return (new Random().nextInt(16) + 15) + " fps"; // Random frame rate between 15 and 30 fps
	    	    }
	    	    
	    	   //***************voipconferenceCDR************************************
	    	    
	    	    public static List<VoIPConferenceCDR> VoIPConferenceCDR(int quantity) {
	    	        List<VoIPConferenceCDR> cdrs = new ArrayList<>();
	    	        Random rand = new Random();

	    	        for (int j = 0; j < quantity; j++) {
	    	            String meetingId = "M" + String.format("%05d", new Random().nextInt(100000));
	    	            int callDuration = new Random().nextInt(115) + 5;
	    	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	            Date startTime = new Date(System.currentTimeMillis() - (long) callDuration * 60 * 1000);
	    	            Date endTime = new Date(System.currentTimeMillis());

	    	            String hostCallerIP = "192.168." + rand.nextInt(256) + "." + rand.nextInt(256);
	    	            String hostCallStatus = getRandomCallStatus();

	    	            int numParticipants = new Random().nextInt(5) + 1;
	    	            List<String> participantIPs = new ArrayList<>();
	    	            List<String> participantStatuses = new ArrayList();

	    	            for (int i = 0; i < numParticipants; i++) {
	    	                String participantIP = "192.168." + rand.nextInt(256) + "." + rand.nextInt(256);
	    	                String participantStatus = getRandomCallStatus();
	    	                participantIPs.add(participantIP);
	    	                participantStatuses.add(participantStatus);
	    	            }

	    	            String callStatus = getRandomCallStatus1();
	    	            String callDirection = getRandomCallDirection1();
	    	            String callTerminationReason = getRandomCallTerminationReason();

	    	            // Create a VoIPConferenceCDR object and set its properties
	    	            VoIPConferenceCDR cdr = new VoIPConferenceCDR();
	    	            cdr.setMeetingId(meetingId);
	    	            cdr.setStartTime(startTime);
	    	            cdr.setEndTime(endTime);
	    	            cdr.setCallDuration(callDuration);
	    	            cdr.setHostCallerIP(hostCallerIP);
	    	            cdr.setHostCallStatus(hostCallStatus);
	    	            cdr.setParticipantIPs(participantIPs);
	    	            cdr.setParticipantStatuses(participantStatuses);
	    	            cdr.setCallStatus(callStatus);
	    	            cdr.setCallDirection(callDirection);
	    	            cdr.setCallTerminationReason(callTerminationReason);

	    	            cdrs.add(cdr);
	    	            try{

	    	            	File cdr1=new File("cdrvoipcon.txt");

	    	            	FileOutputStream fis = new FileOutputStream(cdr1);

	    	            	DataOutputStream dos = new DataOutputStream(fis);

	    	            	dos.writeUTF(cdr.toString());

	    	            }

	    	            		

	    	            catch(Exception e) {

	    	            	

	    	            	e.getStackTrace();

	    	            }
	    	        }

	    	        return cdrs;
	    	    }

	    	    private static String getRandomCallStatus1() {
	    	        String[] statuses = {"Connected", "Busy"};
	    	        return statuses[new Random().nextInt(statuses.length)];
	    	    }

	    	    private static String getRandomCallDirection1() {
	    	        String[] directions = {"Outgoing", "Incoming"};
	    	        return directions[new Random().nextInt(directions.length)];
	    	    }

	    	    private static String getRandomCallTerminationReason() {
	    	        String[] reasons = {"Normal call end", "Call drop", "Unknown", "Error"};
	    	        return reasons[new Random().nextInt(reasons.length)];
	    	    }

	    	    public static void main(String[] args) {
	    	        List<VoIPConferenceCDR> cdrs = VoIPConferenceCDR(5);
	    	        for (VoIPConferenceCDR cdr : cdrs) {
	    	            System.out.println(cdr);
	    	            System.out.println();
	    	        }
	    	    }
//*******************************TollFreeCDR**************************************
	    	    public List<TollFreeCDR> tollfreeCDR(int quantity) {
	    	        List<TollFreeCDR> tf= new ArrayList<>();
	    	        
	    	        Random random = new Random();

	    	       

	    	        // Define an array of possible outcomes
	    	        String[] outcomes = {
	    	            "Sale",
	    	            "Support Request",
	    	            "Billing Inquiry",
	    	            "Complaint Resolution",
	    	            "Product Inquiry",
	    	            "Technical Issue",
	    	            "Appointment Scheduling",
	    	            "Feedback",
	    	            "Follow-up Call",
	    	            "Information Request"
	    	        };

	    	        for (int i = 1; i <=quantity; i++) {
	    	            // Generate random values for each field
	    	            String callStatus = (random.nextBoolean()) ? "Answered" : "Not Connected"; // Randomly set as answered or not connected
	    	            
	    	            int callDuration;
	    	            if (callStatus.equals("Not Connected")) {
	    	                callDuration = random.nextInt(300); // Random duration less than 3 minutes for not connected calls
	    	            } else {
	    	                callDuration = random.nextInt(41) + 5; // Random duration between 5 and 45 minutes for connected calls
	    	            }
	    	            
	    	            String startTime = "2023-10-15 " + String.format("%02d:%02d:00", random.nextInt(24), random.nextInt(60));
	    	            String endTime = calculateEndTime(startTime, callDuration);
	    	            
	    	            String callerNumber ="+91" + (6 + random.nextInt(4)) + (random.nextInt(900000000) + 100000000); // Indian phone number format
	    	            String dialedNumber = "1-800-" + (100 + random.nextInt(900)) + "-" + (1000 + random.nextInt(9000));
	    	            String destinationDepartment = (random.nextBoolean()) ? "Sales" : "Customer Support"; // Random department
	    	            String callType =  "Voice Call" ; // Randomly set as voice call or text message
	    	            
	    	            String uniqueCallID = "ID" + (100000000 + random.nextInt(900000000));
	    	            String recordingStatus = (random.nextBoolean()) ? "Yes" : "No"; // Randomly set as Yes or No
	    	           
	    	            // Randomly select an outcome from the array
	    	            String outcome = outcomes[random.nextInt(outcomes.length)];

	    	            // Print the generated values
	    	            System.out.println("CDR " + i + ":");
	    	            System.out.println("1. Start Time of the Call: " + startTime);
	    	            System.out.println("2. End Time of the Call: " + endTime);
	    	            System.out.println("3. Call Duration: " + callDuration + " minutes");
	    	            System.out.println("4. Caller's Number: " + callerNumber);
	    	            System.out.println("5. Dialed Toll-Free Number: " + dialedNumber);
	    	            System.out.println("6. Call Status: " + callStatus);
	    	            System.out.println("7. Destination Department: " + destinationDepartment);
	    	            System.out.println("8. Call Type: " + callType);
	    	            System.out.println("11. Unique Call ID: " + uniqueCallID);
	    	            System.out.println("12. Recording Status: " + recordingStatus);
	    	            System.out.println("14. Outcome: " + outcome);
	    	            System.out.println();
	    	            
	    	            TollFreeCDR t=new TollFreeCDR();
	    	            t.setStartTime(startTime);
	    	            t.setEndTime(endTime);
	    	            t.setCallDuration(callDuration);
	    	            t.setCallerNumber(callerNumber);
	    	            t.setDialedNumber(dialedNumber);
	    	            t.setCallStatus(callStatus);
	    	            t.setDestinationDepartment(destinationDepartment);
	    	            t.setCallType(callType);
	    	            t.setUniqueCallID(uniqueCallID);
	    	            t.setRecordingStatus(recordingStatus);
	    	            t.setOutcome(outcome);
	    	            tf.add(t);
	    	            try{

	    	            	File cdr=new File("cdrtollfree.txt");

	    	            	FileOutputStream fis = new FileOutputStream(cdr);

	    	            	DataOutputStream dos = new DataOutputStream(fis);

	    	            	dos.writeUTF(t.toString());

	    	            }

	    	            		

	    	            catch(Exception e) {

	    	            	

	    	            	e.getStackTrace();

	    	            }
	    	        }
	    	        return tf;
	    	    }

	    	    // Calculate end time from start time and call duration
	    	    private static String calculateEndTime(String startTime, int callDuration) {
	    	        String[] parts = startTime.split(" ");
	    	        String datePart = parts[0];
	    	        String timePart = parts[1];
	    	        String[] timeParts = timePart.split(":");
	    	        int hours = Integer.parseInt(timeParts[0]);
	    	        int minutes = Integer.parseInt(timeParts[1]);
	    	        
	    	        // Calculate the end time
	    	        minutes += callDuration;
	    	        while (minutes >= 60) {
	    	            hours++;
	    	            minutes -= 60;
	    	        }
	    	        hours %= 24;
	    	        
	    	        return datePart + " " + String.format("%02d:%02d:00", hours, minutes);
	    	    }
	    	    }
	    	


	    
	    

