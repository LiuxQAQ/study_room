package oj;

import java.util.Scanner;

public class Test8 {
    public static void main(String[] args) {

        int[][]dis = new int[64][64];
        for (int i = 0;i<64;i++){
            for (int j = 0;j<64;j++){
                dis[i][j] = 10;
            }
        }
        floyd(dis);
        char[] s = new char[5];
        char[] t = new char[5];
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            s = scanner.next().toCharArray();
            t = scanner.next().toCharArray();
            int x = (s[0]-'a')*8 + (s[1]-'1');
            int y = (t[0]-'a')*8+(t[1]-'1');
            System.out.println("To get from "+s[0]+s[1]+" to "+t[0]+t[1]+" takes "+dis[x][y]+" knight moves.");
        }
    }

    public static void floyd(int[][] dis){
        for (int i = 0 ;i<64;dis[i][i]=0,i++){
            for(int j = 0;j<64;j++){
                int x = Math.abs(i/8-j/8);
                int y = Math.abs(i%8-j%8);
                if ((x==1 && y==2) || (x==2 && y==1)){
                    dis[i][j] = dis[j][i] = 1;
                }
            }

            for (int m=0;m<64;m++){
                for (int n = 0;n<64;n++){
                    for (int j = 0;j<64;j++){
                        dis[n][j] = Math.min(dis[n][j],dis[n][m]+dis[m][j]);
                    }

                }
            }
        }
    }
}
