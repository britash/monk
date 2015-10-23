package com.monk.customer.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class CheckStyleUtil {
	
	/**
	 * 检测字符串格式
	 * @param str
	 * @param style
	 * @return
	 */
	public static boolean checkStyle(final String str, final Style style) {
		if (StringUtils.isBlank(str)) {
			return true;
		}
		Pattern pattern = Pattern.compile(style.getStyle());
		Matcher matcher = pattern.matcher(str);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	public static enum Style {
		/**
		 * 移动电话
		 */
		//PATTERN_MOBILE("^1(3[0-9]|5[0-35-9]|8[025-9])\\d{8}$"),
		PATTERN_MOBILE("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$"),
		/**
		 * 匹配中文
		 */
		PATTERN_ZH("^[\u4e00-\u9fa5]+$"),
		/**
		 * 匹配中文，字符，数字和下划线
		 */
		PATTERN_ZH_CHA_NUM("^[\u4e00-\u9fa5\\w]+$"),
		/**
		 * 匹配字符，数字
		 */
		PATTERN_CHA_NUM("^[A-Za-z0-9]+$"),
		/**
		 * 匹配字符(ASCII码中可显示字符)
		 */
		PATTERN_ASCII("^[!-~]+$"),
		/**
		 * 匹配字符，数字，必须以字符开头
		 */
		PATTERN_CHA_NUM_ORDER("^[A-Za-z][A-Za-z0-9]+$"),
		/**
		 * 匹配Skype账号
		 */
		PATTERN_SKYPE("^[A-Za-z][A-Za-z0-9]{5,31}$"),
		/**
		 * 匹配数字
		 */
		PATTERN_NUM("^[0-9]+$"),
		/**
		 * 匹配字符
		 */
		PATTERN_CHA("^[A-Za-z]+$"),
		/**
		 * 匹配国家/城市/地区
		 */
		PATTERN_ADDRESS("^[0-9A-Z]{1,5}$"),
		/**
		 * 匹配dota2激活码
		 */
		PATTERN_ACTIVE_CODE("^[A-Za-z0-9]{5}-[A-Za-z0-9]{5}-[A-Za-z0-9]{5}$"),
		/**
		 * 匹配字母数字下划线
		 */
		PATTERN_CHA_NUM_LINE("^[\\w-]+$"),
		/**
		 * 匹配邮箱
		 */
		PATTERN_EMAIL("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
		

		private String style;

		Style(String style) {
			this.style = style;
		}

		public String getStyle() {
			return style;
		}

		public void setStyle(String style) {
			this.style = style;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(checkStyle("asdf_123", Style.PATTERN_ZH_CHA_NUM));
	}

}

