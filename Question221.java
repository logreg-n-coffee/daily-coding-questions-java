
/**
 * Let's define a "sevenish" number to be one which is either a power of 7, or
 * the sum of unique powers of 7. The first few sevenish numbers are 1, 7, 8,
 * 49, and so on. Create an algorithm to find the nth sevenish number.
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question221 {
    public static void main(String[] args) {
        List<Integer> sevenishNumbers = IntStream.rangeClosed(0, 10)
                .map(Question221::nthSeventhNumber)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(sevenishNumbers);
    }

    public static int nthSeventhNumber(int n) {
        int result = 0;
        int bitPlace = 0;

        while (n != 0) {
            if ((n & 1) == 1) {
                result += Math.pow(7, bitPlace);
            }
            n >>= 1;
            bitPlace++;
        }

        return result;
    }
}
