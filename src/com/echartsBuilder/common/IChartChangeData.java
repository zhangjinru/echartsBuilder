package com.echartsBuilder.common;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IChartChangeData {
	public List<Map> getData(InputStream ins) throws Exception;
	public List<Map> getDataMerge(InputStream ins) throws Exception;
}
