package com.echartsBuilder.common.form;

public class FormTypeHTML5 implements FormType {
	@Override
	public String createText(String id) {
		String html = "<td><input id = '" + id + "'/></td>";
		return html;
	}

	@Override
	public String createRadio(String id, String type) {
		String[] items = type.split("-");
		String html = "";
		for(int i = 0 ;i < items.length ; i ++){
			html += "<input type= 'radio' id = '" + id + "' name = '" + id + "'value = '"+ items[i] +"'/>"+ items[i];
		}
		return "<td>"+ html + "</td>";
	}

	@Override
	public String createSelect(String id, String type) {
		String[] items = type.split("-");
		String html = "";
		for(int i = 0 ;i < items.length ; i ++){
			html += "<option value = '"+ items[i] +"'>"+ items[i] +"</option>";
		}
		html = "<td><select id = '"+id+"'>"+ html +"</select></td>";
		return html;
	}

	@Override
	public String createColor(String id) {
		String html = "<td><input id = '" + id + "' type = 'color'/></td>";
		return html;
	}

	@Override
	public String createNumber(String id) {
		String html = "<td><input id = '" + id + "' type = 'color'/></td>";
		return html;
	}

	@Override
	public String createRange(String id, String type) {
		String[] items = type.split("-");
		String html = "<td><input id = '" + id + "' type = 'range' min='"+items[0]
				+"' max='" + items[1] + "' step='" + items[2] 
						+ "'  onchange='document.getElementById(\""+id+"result\").value=this.value + \"px\"'/><output id=\"" + id + "result\" value='0' >0px</output></td>";
		return html;
	}
	public String createForm(String value){
		String[] shows = value.split(",");
		if(shows.length < 3){
			return "";
		}
		String formHtml = "<tr><td>"+ shows[1] + "</td>";
		String[] types = shows[2].split("=");
		if(types[0].trim().equals("text")){
			formHtml += this.createText(shows[0]);
		}
        if(types[0].trim().equals("radio")){
        	formHtml += this.createRadio(shows[0],types[1]);
		}
        if(types[0].trim().equals("select")){
        	formHtml += this.createSelect(shows[0],types[1]);
		}
        if(types[0].trim().equals("color")){
        	formHtml += this.createColor(shows[0]);
		}
        if(types[0].trim().equals("number")){
        	formHtml += this.createNumber(shows[0]);
		}
        if(types[0].trim().equals("range")){
        	formHtml += this.createRange(shows[0],types[1]);
		}
		formHtml += "</tr>";
		return formHtml;
	}
}
