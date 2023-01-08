package oj;

import java.util.Arrays;
import java.util.Scanner;

public class Test6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()){
            int n = scanner.nextInt();
            int[] ints = new int[n];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = scanner.nextInt();

            }

                int[] maxlen = new int[1000];
                Arrays.fill(maxlen,1);
                for (int i = 1;i<=ints.length-1;i++){
                    for (int j = 0;j<i;j++){
                        if (ints[i]>ints[j]){
                            maxlen[i] = Math.max(maxlen[i],maxlen[j]+1);
                        }
                    }
                }
                int a = 1;
                for (int i : maxlen) {
                    if (i>a) a = i;
                }
                System.out.println(a);
        }
        scanner.close();
    }


}
