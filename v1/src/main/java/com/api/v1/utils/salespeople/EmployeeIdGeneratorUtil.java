package com.api.v1.utils.salespeople;

import java.math.BigInteger;
import java.security.SecureRandom;

public class EmployeeIdGeneratorUtil {

    public static BigInteger generate() {
        SecureRandom random = new SecureRandom();
        BigInteger min = BigInteger.valueOf(1000000);
        BigInteger max = BigInteger.valueOf(9999999);
        return min.add(new BigInteger(7, random)).mod(max.subtract(min));
    }

}
