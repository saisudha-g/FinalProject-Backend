package cdr.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import cdr.demo.Entity.DatauseageCDR;
import cdr.demo.Entity.LocationCDR;
import cdr.demo.Entity.MmsCDR;
import cdr.demo.Entity.Registration;
import cdr.demo.Entity.RoamingCDR;
import cdr.demo.Entity.SmsCDR;
import cdr.demo.Entity.TollFreeCDR;
import cdr.demo.Entity.VideoCDR;
import cdr.demo.Entity.VoIPConferenceCDR;
import cdr.demo.Entity.VoiceCDR;
import cdr.demo.Entity.VoipCDR;
import cdr.demo.service.cdrService;

@RestController
public class Frontcontroller {

	@Autowired
	cdrService service;
	
	@CrossOrigin(origins="http://localhost:4200/")
	@PostMapping("/register")
	public void create (@RequestBody  Registration reg) {
		System.out.print("reg"+reg);
		service.create(reg);
		
	}
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/register/{phoneNumber}")
	public List<Registration> findbyphno(@PathVariable("phoneNumber") String phoneNumber){
		return service.findbyphno(phoneNumber);
		
	}
	@CrossOrigin(origins="http://localhost:4200/")
	
	@GetMapping("/voice/{quantity}")
	public List<VoiceCDR> voice_creation(@PathVariable("quantity") int quantity){
		return service.voiceCDR(quantity);
	}
	@CrossOrigin(origins="http://localhost:4200/")
	
	@GetMapping("/smscdr/{quantity}")
	public List<SmsCDR> Sms_creation(@PathVariable("quantity") int quantity){
		return service.SmsCDR(quantity);
	}
	@CrossOrigin(origins="http://localhost:4200/")
	
	@GetMapping("/voipcdr/{quantity}")
	public List<VoipCDR> Voip_creation(@PathVariable("quantity") int quantity){
		return service.VoipCDR(quantity);
	}
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/roamingcdr/{quantity}")
	public List<RoamingCDR> roaming_creation(@PathVariable("quantity") int quantity){
		return service.RoamingCDR(quantity);
	}
	
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/datacdr/{quantity}")
	public List<DatauseageCDR> data_creation(@PathVariable("quantity") int quantity){
		return service.DatauseageCDR(quantity);
	}
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/locationcdr/{quantity}")
	public List<LocationCDR> location_creation(@PathVariable("quantity") int quantity){
		return service.LocationCDR(quantity);
	}
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/mmscdr/{quantity}")
	public List<MmsCDR> mms_creation(@PathVariable("quantity") int quantity){
		return service.MmsCDR(quantity);
	}
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/videocdr/{quantity}")
	public List<VideoCDR> video_creation(@PathVariable("quantity") int quantity){
		return service.VideoCDR(quantity);
	}
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/voipconcdr/{quantity}")
	public List<VoIPConferenceCDR> videoc_creation(@PathVariable("quantity") int quantity){
		return service.VoIPConferenceCDR(quantity);
	}
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/tollcdr/{quantity}")
	public List<TollFreeCDR> tf_creation(@PathVariable("quantity") int quantity){
		return service.tollfreeCDR(quantity);
	}
	
}
