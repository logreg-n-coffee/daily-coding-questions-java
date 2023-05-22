
/**
 * IPv4 addresses must follow the format A.B.C.D, where A, B, C, and D are numbers
 * between 0 and 255. Zero-prefixed numbers, such as 01 and 065, are not
 * allowed, except for 0 itself.
 * 
 * For example, given "2542540123", you should return ['254.25.40.123',
 * '254.254.0.123'].
 */

import java.util.ArrayList;
import java.util.List;

public class Question213 {
    public static void main(String[] args) {
        System.out.println(getValidIpAddresses("2542540123"));
    }

    public static List<String> getValidIpAddresses(String str) {
        List<String> ipAddresses = new ArrayList<>();

        // Early return if input is shorter than a valid IP or longer than a maximum
        // valid IP address.
        if (str.length() < 4 || str.length() > 12) {
            return ipAddresses;
        }

        for (int i = 1; i < 4 && i < str.length() - 2; i++) {
            if (!isValidPart(str.substring(0, i)))
                continue;
            for (int j = i + 1; j < i + 4 && j < str.length() - 1; j++) {
                if (!isValidPart(str.substring(i, j)))
                    continue;
                for (int k = j + 1; k < j + 4 && k < str.length(); k++) {
                    String s3 = str.substring(j, k);
                    String s4 = str.substring(k);
                    if (isValidPart(s3) && isValidPart(s4)) {
                        ipAddresses.add(str.substring(0, i) + "." + str.substring(i, j) + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return ipAddresses;
    }

    public static boolean isValidPart(String str) {
        if (str.length() > 3) {
            return false;
        }
        if (str.startsWith("0") && str.length() != 1) {
            return false;
        }
        int val = Integer.parseInt(str);
        return val >= 0 && val <= 255;
    }
}
