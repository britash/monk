package com.monk.customer.common.fastjson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.monk.customer.common.util.MathUtils;

public class BigDecimalSerializer implements ObjectSerializer {

	private static java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.0000"); 
	
	static{
		 df.setRoundingMode(MathUtils.DecimalFormat_RoundingMode);
	}
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        SerializeWriter out = serializer.getWriter();

        if (object == null) {
            if (out.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
                out.write('0');
            } else {
                out.writeNull();
            }
            return;
        }

        BigDecimal val = (BigDecimal) object;
       
        out.write( df.format(val));
    }

}
