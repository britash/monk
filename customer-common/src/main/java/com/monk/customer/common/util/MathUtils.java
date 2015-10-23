package com.monk.customer.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author Shang Pu
 * @version Date: Sep 23, 2015 11:53:30 AM
 */
public class MathUtils {
	/**
	 * A BigDecimal representing the number 0
	 */
	public static final BigDecimal DECIMAL_ZERO = new BigDecimal(BigInteger.ZERO);
	public static final RoundingMode DecimalFormat_RoundingMode = RoundingMode.HALF_UP;

	/**
	 * default value is RoundingMode.HALF_UP
	 */
	public static final MathContext mathContext = new MathContext(15, DecimalFormat_RoundingMode);

	public static BigDecimal roundFinalResult(BigDecimal original) {
		BigDecimal scaled = original.setScale(2, DecimalFormat_RoundingMode);
		return scaled;
	}
}
