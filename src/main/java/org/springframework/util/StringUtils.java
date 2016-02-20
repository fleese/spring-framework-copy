package org.springframework.util;

import javax.swing.SpringLayout.Constraints;

/**
 * 聚合了一些String类型的工具方法
 * @author Wendy
 * @see org.apache.commons.lang.StringUtils
 */
public class StringUtils {
	
	/** 默认文件分割符 */
	private static final String FOLDER_SEPARATOR="/";
	
	/** windows系统的文件分隔符 */
	private static final String WINDOWS_FOLDER_SEPARATOR="\\";
	
	/** 上级目录 */
	private static final String TOP_PATH = "..";
	
	/** 当前目录 */
	private static final String CURRENT_PATH = ".";

	/** 拓展名分割符*/
	private static final String EXTENSION_SEPARATOR = ".";
	
	/**
	 * 检查被给的CharSequence类型实例是否即不为null，长度也不为0
	 * 
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * 
	 * @param str 要被检查的CharSequence实例
	 * @return
	 */
	public static boolean hasLength(CharSequence str){
		return (str != null && str.length()>0);
	}
	
	/**
	 * 检查被给的String类型实例是否即不为null，长度也不为0
	 * @param str 要被检查的String实例
	 * @return
	 */
	public static boolean hasLength(String str){
		return hasLength((CharSequence)str);
	}
	
	/**
	 * 检查被给的CharSequence类型实例是否即不为null，长度也不为0,且不含有空白符（空格，TAB和回车）。
	 * 
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * 
	 * @param str 要被检查的CharSequence实例
	 * @return
	 */
	public static boolean hasText(CharSequence str){
		if(!hasLength(str)){
			return false;
		}
		int strLen = str.length();
		for(int i=0;i<strLen;i++){
			if(!Character.isWhitespace(str.charAt(i))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检查被给的String类型实例是否即不为null，长度也不为0,且不含有空白符（空格，TAB和回车）。
	 * @param str 要被检查的String实例
	 * @return
	 * @see #hasText(CharSequence)
	 */
	public static boolean hasText(String str){
		return hasText((CharSequence)str);
	}
	
	/**
	 * 检查被给的CharSequence类型实例是否即不为null，长度也不为0,且含有空白符（空格，TAB和回车）。
	 * @param str 要被检查的CharSequence实例
	 * @return
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean containsWhitespace(CharSequence str){
		if(!hasLength(str)){
			return false;
		}
		int strLen = str.length();
		for(int i=0;i<strLen;i++){
			if(Character.isWhitespace(str.charAt(i))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检查被给的String类型实例是否即不为null，长度也不为0,且含有空白符（空格，TAB和回车）。
	 * @param str 要被检查的String实例
	 * @return
	 */
	public static boolean containsWhitespace(String str){
		return containsWhitespace((CharSequence)str);
	}
	
	/**
	 * 去掉给定String实例头尾的空白字符
	 * @param str 需要操作的字符串
	 * @return
	 */
	public static String trimWhitespace(String str){
		if(!hasLength(str)){
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while(sb.length()>0 && Character.isWhitespace(sb.charAt(0))){
			sb.deleteCharAt(0);
		}
		while(sb.length()>0&& Character.isWhitespace(sb.charAt(sb.length()-1))){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	/**
	 * 去除所给String实例中的所有空白字符
	 * @param str
	 * @return
	 */
	public static String trimAllWhitespace(String str){
		if(!hasLength(str)){
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while(sb.length()>index){
			if(Character.isWhitespace(sb.charAt(index))){
				sb.deleteCharAt(index);
			}else{
				index++;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 去除被给的String实例的开头的空白字符
	 * @param str 
	 * @return
	 */
	public static String trimLeadingWhitespace(String str){
		if(!hasLength(str)){
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while(sb.length()>0 && Character.isWhitespace(sb.charAt(0))){
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	
	/**
	 * 去除被给String实例尾部的空白字符
	 * @param str
	 * @return
	 */
	public static String trimTrailingWhitespace(String str){
		if(!hasLength(str)){
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while(sb.length()>0 && Character.isWhitespace(sb.length()-1)){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	/**
	 * 去除String实例头部特定的字符
	 * @param str
	 * @param leadingCharacter 需要去除的特定字符
	 * @return
	 */
	public static String trimLeadingCharacter(String str,char leadingCharacter){
		if(!hasLength(str)){
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while(sb.length()>0 && sb.charAt(0) == leadingCharacter){
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	
	/**
	 * 去除String实例尾部特定的字符
	 * @param str
	 * @param trailingCharacter 需要去除的特定字符
	 * @return
	 */
	public static String trimTrailingCharacter(String str, char trailingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && sb.charAt(sb.length() - 1) == trailingCharacter) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	/**
	 * 测试String实例是否是包含特定的前缀，忽略大小写
	 * @param str
	 * @param prefix 要检查的前缀
	 * @return
	 */
	public static boolean startsWithIgnoreCase(String str,String prefix){
		if(str==null||prefix==null){
			return false;
		}
		if(str.startsWith(prefix)){
			return true;
		}
		if(str.length()<prefix.length()){
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}
}
