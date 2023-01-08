package oj;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strings = new String[n+1];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = scanner.nextLine();
        }
        lengthSort(strings);
    }

    public static void lengthSort(String[] strings){
        for (int i = strings.length-1;i>0;i--){
            for (int j = 0;j<i;j++){
                if (strings[j].length()<strings[j+1].length()){
                    String temp = strings[j];
                    strings[j] = strings[j+1];
                    strings[j+1] = temp;
                }
                if (strings[j].length()==strings[j+1].length()){
                    if (strings[j].compareToIgnoreCase(strings[j+1])>0){
                        String temp = strings[j];
                        strings[j] = strings[j+1];
                        strings[j+1] = temp;
                    }
                }
            }
        }
        for (int i=0;i<strings.length-1;i++){
            System.out.println(strings[i]);
        }
    }
}
