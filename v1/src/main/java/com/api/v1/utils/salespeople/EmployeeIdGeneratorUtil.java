package com.api.v1.utils.salespeople;

import java.math.BigInteger;
import java.security.SecureRandom;

public class EmployeeIdGeneratorUtil {

    public static BigInteger generate() {
        SecureRandom random = new SecureRandom();
        int upperBound = 9999999;
        int randomId = random.nextInt(upperBound - 1000000 + 1) + 1000000;
        String strFormat = String.format("%07d", randomId);
        return new BigInteger(strFormat);
    }

}