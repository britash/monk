package com.monk.customer.common;



import com.alibaba.fastjson.serializer.SerializeConfig;
import com.monk.customer.common.fastjson.BigDecimalSerializer;
import com.monk.customer.common.fastjson.SimpleDateFormatSerializer;

public class FastJsonSerializeConfig {

	private static SerializeConfig mapping = new SerializeConfig();
	
	static {
		mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer());  
		mapping.put(java.util.Date.class, new SimpleDateFormatSerializer());  
		mapping.put(java.math.BigDecimal.class, new BigDecimalSerializer());  
	}
	
	public static SerializeConfig getFastJsonSerializeConfigInstance(){
		return mapping;
	}
}
