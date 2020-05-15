package com.z21.services;

import com.z21.be.models.common.SchoolConfig;
import com.z21.dao.SchoolConfigurationDao;

public class SchoolConfigurationService {
	
	private SchoolConfigurationDao configurationDao;

	public SchoolConfigurationDao getConfigurationDao() {
		return configurationDao;
	}

	public void setConfigurationDao(SchoolConfigurationDao configurationDao) {
		this.configurationDao = configurationDao;
	}

	
	
	public SchoolConfig getSchoolInfo(String schoolCode) {
		return configurationDao.getSchoolInfo(schoolCode) ;
		
	}
	 
	
	public SchoolConfig createSchoolInfo(SchoolConfig school) {
		return configurationDao.createSchoolInfo(school) ;
		
	}
	
	
	public SchoolConfig updateSchoolInfo(SchoolConfig school) {
		return configurationDao.updateSchoolInfo(school);
	}


}
