package com.echartsBuilder.common.form;

public interface FormType {
	public String createText(String id);
	public String createColor(String id);
	public String createNumber(String id);
	public String createRadio(String id,String type);
	public String createSelect(String id,String type);
	public String createRange(String id,String type);
	public String createForm(String value);
}
