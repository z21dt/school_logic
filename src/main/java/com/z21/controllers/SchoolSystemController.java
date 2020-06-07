package com.z21.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z21.be.models.common.SchoolConfig;
import com.z21.services.SchoolConfigurationService;

@Controller
@RequestMapping("admin/schools")
public class SchoolSystemController {
	
	@Autowired
	SchoolConfigurationService schoolConfiService;
	
	@RequestMapping(value = "help", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getHelp() {
		return "test";
	}
	


	@RequestMapping(value = "getSchoolConfig", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SchoolConfig getSchoolInformation(@RequestParam(value = "schoolCode", required = true) String code,		
									HttpServletRequest servletRequest,
									HttpServletResponse servletResponse) {		
		
		
		System.out.println("Get Code "+code);
		 

		String scode = code.toLowerCase();
		SchoolConfig conf =  schoolConfiService.getSchoolInfo(scode);
		
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		return conf;
	}
	 
	
	@RequestMapping(value = "createSchoolConfig", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody SchoolConfig getSchoolInformation(@RequestBody SchoolConfig  school,		
									HttpServletRequest servletRequest,
									HttpServletResponse servletResponse) {		
		
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		System.out.println("Create School "+school); 
		return schoolConfiService.createSchoolInfo(school);
	}
	
	
	@RequestMapping(value = "updateSchoolConfig", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody SchoolConfig updateSchoolConfig(@RequestBody SchoolConfig  school,		
									HttpServletRequest servletRequest,
									HttpServletResponse servletResponse) {		
		
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");

		System.out.println("Update School "+school); 
		return schoolConfiService.updateSchoolInfo(school);
	}
	
	
	@RequestMapping(value = "_getSchoolConfig", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SchoolConfig getSchoolInfo(@RequestParam(value = "schoolId", required = true) String code,		
									HttpServletRequest servletRequest,
									HttpServletResponse servletResponse) {		
		
		
		SchoolConfig school = new SchoolConfig();

		System.out.println("Looking for School : "+code);
		if(code != null) {
			String schoolCode = code.toUpperCase();
			
			
			if(schoolCode.equals("SLRA")) {
			
				school.setSchoolCode(schoolCode);
				school.setContactInfo("Contact Info St Lorenzo Here..");	
				school.setContactNumber("0977.701.9347");
				school.setEmailAddress("slra.tagum@gmail.com"); 
				school.setFooterBgColor("#262626");
				school.setFooterTextColor("#ffffff");			
				school.setHeaderBgColor("#007bff");
				school.setHeaderTextColor("#ffffff");
				school.setSchoolName("St. Lorenzo Ruiz Academy of Tagum, Inc.");
				school.setSchoolDescription("Say something about SLRA here...");
				school.setAddress("Marquez rd cor Apokon Road, Apokon Tagum City, 8100");
				school.setLogo("assets/images/slra_logo.png");
			}else if(schoolCode.equals("LCT")) {
			
				school.setSchoolCode(schoolCode);
				school.setContactInfo("Contact Info of Longford Here..");	
				school.setContactNumber("0977.701.2222");
				school.setEmailAddress("lfa.tagum@gmail.com"); 
				school.setFooterBgColor("#515c57");
				school.setFooterTextColor("white");			
				school.setHeaderBgColor("#b0bf73");
				school.setHeaderTextColor("white");
				school.setSchoolName("Longford College of Tagum.");
				school.setSchoolDescription("Say something about Longford here...");
				school.setAddress("Tagum City, 8100");
				school.setLogo("assets/images/LCT_logo.jpeg");
			}
			else {
				
				school.setSchoolCode("UNKNOWN");
				school.setContactInfo("Contact Info Here..");
				school.setContactNumber("0977.701.1234");
				school.setEmailAddress("testSchool@gmail.com"); 
				school.setFooterBgColor("#999999");
				school.setFooterTextColor("#00000");			
				school.setHeaderBgColor("white");
				school.setHeaderTextColor("black");
				school.setSchoolName("Test school");
				school.setSchoolDescription("Say something about Test School here...");
				school.setAddress("Address here...");
				
			}
		}{
			
		}
		
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		
		return school;
		
	}
}

