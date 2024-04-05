package dev.mrshawn.deathmessages.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Util {

    // The simpler and better version of common-lang's RandomStringUtils#randomNumeric
    public static String randomNumeric(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero.");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // Bound range 0~9
            int digit = ThreadLocalRandom.current().nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
