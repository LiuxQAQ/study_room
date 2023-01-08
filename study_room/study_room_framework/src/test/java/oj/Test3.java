package oj;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t--!=0){
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[][] point = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    point[i][j]=0;
                }
            }
            int k = scanner.nextInt();
            while (k--!=0){
                int stone_x = scanner.nextInt();
                int stone_y = scanner.nextInt();
                point[stone_x-1][stone_y-1] = 1;
            }
//            for (int[] ints : point) {
//                for (int anInt : ints) {
//                    System.out.printf(String.valueOf(anInt));
//                }
//                System.out.println();
//            }

            int emp = 0;
            int sign;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (point[i][j]==0){
                        if (j<n-1 && point[i][j+1]==0){
                            for (int l = j+2; l <= n-1; l++) {
                                if (point[i][l] == 1){

                                    j=l;
                                    break;
                                }
                                if (l==n-1) {
                                    j = n-1;
                                }

                            }
                            emp++;
                        }
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    if (point[i][j]==0){
                        if (i<m-1 && point[i+1][j]==0){
                            for (int l = i+2; l <= m-1; l++) {
                                if (point[l][j] == 1){
                                    i=l;
                                    break;
                                }
                                if (l==m-1) i=m-1;
                            }
                            emp++;
                        }
                    }
                }
            }

            System.out.println(emp);
        }
    }
}
