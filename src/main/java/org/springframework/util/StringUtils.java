package org.springframework.util;

import javax.swing.SpringLayout.Constraints;

/**
 * �ۺ���һЩString���͵Ĺ��߷���
 * @author Wendy
 * @see org.apache.commons.lang.StringUtils
 */
public class StringUtils {
	
	/** Ĭ���ļ��ָ�� */
	private static final String FOLDER_SEPARATOR="/";
	
	/** windowsϵͳ���ļ��ָ��� */
	private static final String WINDOWS_FOLDER_SEPARATOR="\\";
	
	/** �ϼ�Ŀ¼ */
	private static final String TOP_PATH = "..";
	
	/** ��ǰĿ¼ */
	private static final String CURRENT_PATH = ".";

	/** ��չ���ָ��*/
	private static final String EXTENSION_SEPARATOR = ".";
	
	/**
	 * ��鱻����CharSequence����ʵ���Ƿ񼴲�Ϊnull������Ҳ��Ϊ0
	 * 
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * 
	 * @param str Ҫ������CharSequenceʵ��
	 * @return
	 */
	public static boolean hasLength(CharSequence str){
		return (str != null && str.length()>0);
	}
	
	/**
	 * ��鱻����String����ʵ���Ƿ񼴲�Ϊnull������Ҳ��Ϊ0
	 * @param str Ҫ������Stringʵ��
	 * @return
	 */
	public static boolean hasLength(String str){
		return hasLength((CharSequence)str);
	}
	
	/**
	 * ��鱻����CharSequence����ʵ���Ƿ񼴲�Ϊnull������Ҳ��Ϊ0,�Ҳ����пհ׷����ո�TAB�ͻس�����
	 * 
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * 
	 * @param str Ҫ������CharSequenceʵ��
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
	 * ��鱻����String����ʵ���Ƿ񼴲�Ϊnull������Ҳ��Ϊ0,�Ҳ����пհ׷����ո�TAB�ͻس�����
	 * @param str Ҫ������Stringʵ��
	 * @return
	 * @see #hasText(CharSequence)
	 */
	public static boolean hasText(String str){
		return hasText((CharSequence)str);
	}
	
	/**
	 * ��鱻����CharSequence����ʵ���Ƿ񼴲�Ϊnull������Ҳ��Ϊ0,�Һ��пհ׷����ո�TAB�ͻس�����
	 * @param str Ҫ������CharSequenceʵ��
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
	 * ��鱻����String����ʵ���Ƿ񼴲�Ϊnull������Ҳ��Ϊ0,�Һ��пհ׷����ո�TAB�ͻس�����
	 * @param str Ҫ������Stringʵ��
	 * @return
	 */
	public static boolean containsWhitespace(String str){
		return containsWhitespace((CharSequence)str);
	}
	
	/**
	 * ȥ������Stringʵ��ͷβ�Ŀհ��ַ�
	 * @param str ��Ҫ�������ַ���
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
	 * ȥ������Stringʵ���е����пհ��ַ�
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
	 * ȥ��������Stringʵ���Ŀ�ͷ�Ŀհ��ַ�
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
	 * ȥ������Stringʵ��β���Ŀհ��ַ�
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
	 * ȥ��Stringʵ��ͷ���ض����ַ�
	 * @param str
	 * @param leadingCharacter ��Ҫȥ�����ض��ַ�
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
	 * ȥ��Stringʵ��β���ض����ַ�
	 * @param str
	 * @param trailingCharacter ��Ҫȥ�����ض��ַ�
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
	 * ����Stringʵ���Ƿ��ǰ����ض���ǰ׺�����Դ�Сд
	 * @param str
	 * @param prefix Ҫ����ǰ׺
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
