package com.echartsBuilder.entity;

public class EBModule {
	private String moduleId;
	private String moduleType;
	private String moduleParent;
	private String moduleContent;
	private int id;
	private String other1;
	private String other2;
	private String other3;
	public EBModule() {
		super();
	}
	public EBModule(String moduleId, String moduleType, String moduleParent,
			String moduleContent, int id, String other1, String other2,
			String other3) {
		super();
		this.moduleId = moduleId;
		this.moduleType = moduleType;
		this.moduleParent = moduleParent;
		this.moduleContent = moduleContent;
		this.id = id;
		this.other1 = other1;
		this.other2 = other2;
		this.other3 = other3;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getModuleParent() {
		return moduleParent;
	}
	public void setModuleParent(String moduleParent) {
		this.moduleParent = moduleParent;
	}
	public String getModuleContent() {
		return moduleContent;
	}
	public void setModuleContent(String moduleContent) {
		this.moduleContent = moduleContent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOther1() {
		return other1;
	}
	public void setOther1(String other1) {
		this.other1 = other1;
	}
	public String getOther2() {
		return other2;
	}
	public void setOther2(String other2) {
		this.other2 = other2;
	}
	public String getOther3() {
		return other3;
	}
	public void setOther3(String other3) {
		this.other3 = other3;
	}



}
