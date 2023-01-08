package oj;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        int[] f = new int[1000000];
        f[0] = 7;
        f[1] = 11;
        int sum = 0;
        Scanner scanner = new Scanner(System.in);
        int n ;
        while ((n = scanner.nextInt())!=-1) {

            if (n==0 || n==1) {
                System.out.println("no");
                continue;
            }
            for (int i=2;i<=n;i++){
                f[i] = f[i-2]+f[i-1];
                sum = f[i];
            }
            if (sum%3==0){
                System.out.println("yes");
            }else System.out.println("no");
        }
    }
}
