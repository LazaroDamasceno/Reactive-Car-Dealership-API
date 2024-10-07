package com.api.v1.utils.purchases;

import java.math.BigInteger;
import java.time.ZonedDateTime;

public class PurchaseIdGeneratorUtil {

    private static int year = ZonedDateTime.now().getYear();
    private static String strFormat = "%04d00000".formatted(year);
    private static BigInteger biFormat = new BigInteger(strFormat);

    public static BigInteger generate() {
        biFormat = biFormat.add(BigInteger.ONE);
        return biFormat;
    }

}
