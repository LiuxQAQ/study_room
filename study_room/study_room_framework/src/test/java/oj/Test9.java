package oj;

import java.util.Scanner;

public class Test9 {
    public static void main(String[] args) {
        int x, y, z;
        int a1, a2, a3, a4, a5, a6;
        int[] c = {0, 5, 3, 1};
        Scanner scanner = new Scanner(System.in);
        while (true) {
            z = 0;
            a1 = scanner.nextInt();
            a2 = scanner.nextInt();
            a3 = scanner.nextInt();
            a4 = scanner.nextInt();
            a5 = scanner.nextInt();
            a6 = scanner.nextInt();

            if (a1 == 0 && a2 == 0 && a3 == 0 && a4 == 0 && a5 == 0 && a6 == 0)
                break;
            z += a6 + a5 + a4 + (a3 + 3) / 4;
            x = 5 * a4 + c[a3 % 4];
            if (a2 > x) {
                z += (a2 - x + 8) / 9;
            }
            y = 36 * z - 36 * a6 - 25 * a5 - 16 * a4 - 9 * a3 - 4 * a2;
            if (a1 > y)
                z += (a1 - y + 35) / 36;
            System.out.println(z);
        }
    }
}
