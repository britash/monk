package com.monk.customer.common.util;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Hello world!
 *
 */
public class EscapeUtils 
{
   public static String escapeSql(String str){
	   return StringEscapeUtils.escapeSql(str);
   }
}
