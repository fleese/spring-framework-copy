package org.springframework.util;

import java.util.Arrays;

/**
 * �����һЩ�����ʹ�÷���
 * @author Wendy
 *
 */
public class ObjectUtils {
	
	/**
	 * ����Ƿ����ܲ��쳣
	 * @param ex
	 * @return
	 */
	public static boolean isCheckedException(Throwable ex){
		return !(ex instanceof RuntimeException || ex instanceof Error);
	}
	
	/**
	 * ���������쳣�Ƿ������throws�Ӿ����������쳣
	 * @param ex
	 * @param declaredExceptions
	 * @return
	 */
	public static boolean isCompatibleWithThrowsClause(Throwable ex,Class[] declaredExceptions){
		if(!isCheckedException(ex)){
			return true;
		}
		if(declaredExceptions != null){
			int i = 0;
			while(i < declaredExceptions.length){
				if(declaredExceptions[i].isAssignableFrom(ex.getClass())){
					return true;
				}
			}
		}
		return true;
	}
	
	/**
	 * ��ȫ�ıȽ����������Ƿ���ȣ�������κ�һ��Ϊnull,����false��������������飬
	 * ��Arrays.equalsȥ�Ƚ�����Ԫ���Ƿ���ȣ������ǱȽ������Ƿ����
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean nullSafeEquals(Object o1, Object o2){
		if(o1 == o2){
			return true;
		}
		if(o1 == null || o2 == null){
			return false;
		}
		if(o1.equals(o2)){
			return true;
		}
		if(o1.getClass().isArray() && o2.getClass().isArray()){
			if(o1 instanceof Object[] && o2 instanceof Object[]){
				return Arrays.equals((Object[])o1, (Object[])o2);
			}
			if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
				return Arrays.equals((boolean[]) o1, (boolean[]) o2);
			}
			if (o1 instanceof byte[] && o2 instanceof byte[]) {
				return Arrays.equals((byte[]) o1, (byte[]) o2);
			}
			if (o1 instanceof char[] && o2 instanceof char[]) {
				return Arrays.equals((char[]) o1, (char[]) o2);
			}
			if (o1 instanceof double[] && o2 instanceof double[]) {
				return Arrays.equals((double[]) o1, (double[]) o2);
			}
			if (o1 instanceof float[] && o2 instanceof float[]) {
				return Arrays.equals((float[]) o1, (float[]) o2);
			}
			if (o1 instanceof int[] && o2 instanceof int[]) {
				return Arrays.equals((int[]) o1, (int[]) o2);
			}
			if (o1 instanceof long[] && o2 instanceof long[]) {
				return Arrays.equals((long[]) o1, (long[]) o2);
			}
			if (o1 instanceof short[] && o2 instanceof short[]) {
				return Arrays.equals((short[]) o1, (short[]) o2);
			}
		}
		return false;
	}
}
