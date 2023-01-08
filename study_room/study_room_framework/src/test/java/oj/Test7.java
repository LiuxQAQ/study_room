package oj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t--!=0){

            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int max = scanner.nextInt();
            List<Plat> plats = new ArrayList<>();

            while (n--!=0){
                int x1 = scanner.nextInt();
                int x2 = scanner.nextInt();
                int h = scanner.nextInt();
                plats.add(new Plat(x1,x2,h));
            }

            int min;
            for (Plat plat : plats) {
                if (plat.x1<=x && plat.x2>=x && y-plat.h>=max){

                }
            }
        }
    }
}
class Plat{
    int x1;
    int x2;
    int h;

    public Plat(int x1, int x2, int h) {
        this.x1 = x1;
        this.x2 = x2;
        this.h = h;
    }

}
