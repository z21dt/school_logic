package com.z21.fe.models.resp;

import java.util.List;

import com.z21.be.models.school.ReferenceDocument;

public class RefDocumentResp {
	
	List<ReferenceDocument> attachments;

	public List<ReferenceDocument> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<ReferenceDocument> attachments) {
		this.attachments = attachments;
	}
	
	
}
