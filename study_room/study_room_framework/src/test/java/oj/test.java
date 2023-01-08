package oj;
 
import java.util.*;

public class test {
    static int N, X, Y, MAX;
    static PlatFrom[] platfroms;

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        int t = reader.nextInt();
        while (t != 0) {
            N = reader.nextInt();
            X = reader.nextInt();
            Y = reader.nextInt();
            MAX = reader.nextInt();
            platfroms = new PlatFrom[N + 1];
            platfroms[0] = new PlatFrom(X, X, Y);
            for (int i = 1; i < N + 1; i++) {
                int L = reader.nextInt();
                int R = reader.nextInt();
                int H = reader.nextInt();
                platfroms[i] = new PlatFrom(L, R, H);
            }
            List<PlatFrom> pfList = new ArrayList<PlatFrom>(Arrays.asList(platfroms));//该方法是将数组转化为list,但是注意原数组却不会发生变化
            Collections.sort(pfList);
            int c = 0;
            for (PlatFrom e : pfList) {
                platfroms[c++] = e;
            }
            int[][] time = new int[N + 1][2];

            int m = 0;
            for (int k = N; k >= 0; k--) {

                if (k == N) {
                    time[k][0] = platfroms[k].high;
//				System.out.print("time["+k+"][0]= "+time[k][0]);
                    time[k][1] = platfroms[k].high;
//				System.out.print("   time["+k+"][1]= "+time[k][1]);
                } else {
                    m = leftUnder(k);
                    if (m == -1) {
                        if (platfroms[k].high > MAX) time[k][0] = 999999;
                        else time[k][0] = platfroms[k].high;
//					System.out.print("无m值  time["+k+"][0]= "+time[k][0]);
                    } else {
                        time[k][0] = platfroms[k].high - platfroms[m].high + Math.min(time[m][0] + platfroms[k].Lx - platfroms[m].Lx, time[m][1] + platfroms[m].Rx - platfroms[k].Lx);
//					System.out.print(" m= "+m+" time["+k+"][0]= "+time[k][0]);
                    }
                    m = rightUnder(k);
                    if (m == -1) {
                        if (platfroms[k].high > MAX) time[k][1] = 999999;
                        else time[k][1] = platfroms[k].high;
//					System.out.print("无m值  time["+k+"][1]= "+time[k][1]);
                    } else {
                        time[k][1] = platfroms[k].high - platfroms[m].high + Math.min(time[m][0] + platfroms[k].Rx - platfroms[m].Lx, time[m][1] + platfroms[m].Rx - platfroms[k].Rx);
//					System.out.print(" m= "+m+" time["+k+"][1]= "+time[k][1]);
                    }
                }
//			System.out.println();
            }
            System.out.println(time[0][1]);
            t--;
        }
    }

    static class PlatFrom implements Comparable<PlatFrom> {
        private int Lx;
        private int Rx;
        private int high;

        public PlatFrom(int x, int y, int z) {
            this.Lx = x;
            this.Rx = y;
            this.high = z;
        }

        //为了更好得显示数据（实际上为测试所需），我们重写toString方法
        @Override
        public String toString() {
            return "X=" + Lx + ",Y=" + Rx + ",Z=" + high + "。";
        }

        public int getX() {
            return Lx;
        }

        public int getY() {
            return Rx;
        }

        public int getZ() {
            return high;
        }

        //实现Comparable接口，重写接口方法
        //如果指定的数与参数相等返回0；如果指定的数小于参数返回 -1；
        //如果指定的数大于参数返回 1,执行交换，此时升序排序，倒序情况则相反。
        @Override
        public int compareTo(PlatFrom next) {
            return next.getZ() - this.high;

        }
    }

    static int leftUnder(int k) {//返回左端点下面的平台
        for (int m = k + 1; m <= N; m++) {
            if (platfroms[m].Lx <= platfroms[k].Lx && platfroms[k].Lx <= platfroms[m].Rx && platfroms[k].high - platfroms[m].high <= MAX) {
                return m;
            }
        }
        return -1;
    }

    static int rightUnder(int k) {//返回右端点下面的平台
        for (int m = k + 1; m <= N; m++) {
            if (platfroms[m].Lx <= platfroms[k].Rx && platfroms[k].Rx <= platfroms[m].Rx && platfroms[k].high - platfroms[m].high <= MAX) {
                return m;
            }
        }
        return -1;
    }
}


