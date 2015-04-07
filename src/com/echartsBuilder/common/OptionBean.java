package com.echartsBuilder.common;

import java.util.Iterator;

import com.echartsBuilder.common.form.FormType;
import com.echartsBuilder.common.form.FormTypeHTML5;

import net.sf.json.JSONObject;

public class OptionBean {
	private StringBuilder showHtml;//最后生成的showHtml
	private JSONObject contentJson;//json的value"id,中文名,type"
	public JSONObject getContentJson() {
		return contentJson;
	}
	public void setContentJson(JSONObject contentJson) {
		this.contentJson = contentJson;
	}
	public StringBuilder getShowHtml() {
		return showHtml;
	}
	public void setShowHtml(StringBuilder showHtml) {
		this.showHtml = showHtml;
	}
	public OptionBean(){
		super();
		showHtml = new StringBuilder();
	}
	public void showContent(String label){
		FormType type = new FormTypeHTML5();
		JSONObject jobj = this.getContentJson().getJSONObject(label);
		if (jobj != null) {
			Iterator it = jobj.keys();
			this.setShowHtml(new StringBuilder("<table>"));
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = jobj.getString(key);
				this.setShowHtml(this.getShowHtml().append(type.createForm(value)));
			}
			this.setShowHtml(this.getShowHtml().append("</table>"));
		}
	}
}


