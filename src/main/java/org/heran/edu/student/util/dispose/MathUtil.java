package org.heran.edu.student.util.dispose;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 数学计算工具类
 * 
 * @author John
 *
 */
public class MathUtil {

	/**
	 * 除法运算
	 * 
	 * @param dividend
	 * @param divisor
	 * @param scale
	 * @return
	 */
	public static double divide(double dividend, double divisor, int scale) {
		return new BigDecimal(dividend).divide(new BigDecimal(divisor), scale,
				BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	/**
	 * 加法运算
	 * 
	 * @param addend
	 * @param augend
	 * @param scale
	 * @return
	 */
	public static double add(double addend, double augend, int scale) {
		return new BigDecimal(addend).add(new BigDecimal(augend))
				.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	/**
	 * 减法运算
	 * 
	 * @param minuend
	 * @param subtrahend
	 * @param scale
	 * @return
	 */
	public static double subtract(double minuend, double subtrahend, int scale) {
		return new BigDecimal(minuend).subtract(new BigDecimal(subtrahend))
				.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	/**
	 * 乘法运算
	 * 
	 * @param multiplier
	 * @param multiplicand
	 * @param scale
	 * @return
	 */
	public static double multiply(double multiplier, double multiplicand, int scale) {
		return new BigDecimal(multiplier).multiply(new BigDecimal(multiplicand))
				.setScale(scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	/**
	 * 舍入运算
	 * 
	 * @param roundend
	 * @param scale
	 * @return
	 */
	public static double round(double roundend, int scale) {
		return divide(roundend, 1, scale);
	}

	/**
	 * 求余运算
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static long remainder(long dividend, long divisor) {
		return new BigInteger(String.valueOf(dividend)).remainder(
				new BigInteger(String.valueOf(divisor))).longValue();
	}

}
