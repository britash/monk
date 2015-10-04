package com.monk.customer.common.fastjson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

public class SimpleDateFormatSerializer implements ObjectSerializer {
	
    private final String pattern = "yyyyMMdd HH:mm:ss";

    public SimpleDateFormatSerializer(){
    }

    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws IOException {
    	if (object == null) {
    		serializer.getWriter().writeNull();
    		return;
    	}
    	
        Date date = (Date) object;
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        String text = format.format(date);
        serializer.write(text);
    }
}
