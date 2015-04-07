 package com.echartsBuilder.common;

import java.util.Iterator;

import com.echartsBuilder.common.form.FormType;
import com.echartsBuilder.common.form.FormTypeHTML5;

import net.sf.json.JSONObject;

public class Legend extends OptionBean {
	public void showContent() {
		FormType type = new FormTypeHTML5();
		JSONObject jobj = this.getContentJson().getJSONObject("legend");
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
