package com.z21.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z21.be.models.SchoolInfo;

@Controller
@RequestMapping("admin/schools")
public class SchoolSystemController {
	
	
	@RequestMapping(value = "help", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getHelp() {
		return "test";
	}
	
	@RequestMapping(value = "getSchoolConfig", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SchoolInfo getSchoolInfo(@RequestParam(value = "schoolId", required = true) String code,		
									HttpServletRequest servletRequest,
									HttpServletResponse servletResponse) {		
		
		
		SchoolInfo school = new SchoolInfo();

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

