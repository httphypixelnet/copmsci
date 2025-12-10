package compsci;

import java.util.Arrays;

import static compsci.Utils.prompt;

public class Primes {
    public static void main(String[] args) {
            long start = System.currentTimeMillis();

            int n = Integer.parseInt(prompt("Enter your preferred upper limit for primes:", new java.util.Scanner(System.in)));
            int[] primes = sieve(n);

            long end = System.currentTimeMillis();

            System.out.println("Found " + primes.length + " primes up to " + n);
            System.out.println("Time taken: " + (end - start) + "ms");

            System.out.print("Primes: ");
            for(int i=0; i<primes.length; i++) System.out.print(primes[i] + " ");

    }
    public static int[] sieve(int n) {
        if (n < 2) return new int[0];

        int estimatedCount = (n < 25) ? n : (int) (n / Math.log(n) * 1.15);

        int[] compositeBits = new int[((n / 2) >> 5) + 1];

        int sqrtN = (int) Math.sqrt(n);

        for (int i = 1; (2 * i + 1) <= sqrtN; i++) {

            if ((compositeBits[i >> 5] & (1 << (i & 31))) == 0) {

                int start = 2 * i * (i + 1);

                int step = 2 * i + 1;

                for (int j = start; j <= n / 2; j += step) {
                    compositeBits[j >> 5] |= (1 << (j & 31));
                }
            }
        }

        int[] primes = new int[estimatedCount];
        int count = 0;

        primes[count++] = 2;

        for (int i = 1; i <= n / 2; i++) {
            if ((compositeBits[i >> 5] & (1 << (i & 31))) == 0) {
                primes[count++] = 2 * i + 1;
            }
        }

        return Arrays.copyOf(primes, count);
    }

}