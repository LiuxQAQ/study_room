package oj;

import org.bouncycastle.jcajce.provider.symmetric.AES;

import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            boolean k = false;
            int a = scanner.nextInt();
            for (int i=2;i<a;i++){
                for (int j=i;j<a;j++){
                    if (i*j == a){
                        k = ff(i, j);
                    }
                }
                if (k) break;
            }
            if (k) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static boolean ff(int i,int j){
        Boolean a = true;
        for (int i1 = 2; i1 < i; i1++) {
            if (i%i1==0) a=false;
        }
        for (int i1 = 2; i1 < j; i1++) {
            if (j%i1==0) a = false;
        }

        return a;
    }
}
