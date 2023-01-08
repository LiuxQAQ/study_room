package oj;

import java.util.Arrays;
import java.util.Scanner;

public class Test10 {
    public static void main(String[] args) {
        int[] ints = new int[200];
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int n, z, x;
        while (i-- != 0) {
            Arrays.fill(ints, 0);
            n = scanner.nextInt();
            for (int j = n; j > 0; j--) {
                z = scanner.nextInt();
                x = scanner.nextInt();
                z = (z - 1) / 2;
                x = (x - 1) / 2;
                for (int m = z; m <= x; m++) {
                    ints[m]++;
                }

            }
            int max = 0;
            for (int anInt : ints) {
                if (anInt > max) max = anInt;
            }
            System.out.println(max*10);
        }
    }
}
