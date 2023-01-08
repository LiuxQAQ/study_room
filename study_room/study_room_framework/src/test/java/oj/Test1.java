package oj;

import java.util.Scanner;
public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            int k = scanner.nextInt();
            for (int j = m ;j>1 ;j--){
                k = (k+1)*2;
            }
            System.out.println(k);
        }
    }
}
