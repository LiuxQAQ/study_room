package com.lx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareTwoTimeUtil {

    public static void main(String[] args) {

        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start1 = sf.parse("2019-05-23 22:31:00");
            Date end1 =   sf.parse("2019-05-23 22:31:57");
            System.out.println("result = " + compareTwoTime(start1,end1));
            System.out.println("***正确***");

            Date start2 = sf.parse("2019-05-23 22:38:58");
            Date end2 =   sf.parse("2019-05-23 22:32:55");
            System.out.println("result = " +compareTwoTime(start2,end2));
            System.out.println("***错误***");
        }catch (Exception e){
        }

        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start1 = sf.parse("2019-04-23 22:31:00");
            Date end1 =   sf.parse("2019-04-23 22:31:57");

            Date start2 = sf.parse("2019-04-23 22:31:58");
            Date end2 =   sf.parse("2019-04-23 22:32:55");
            System.out.println(compareTwoTime(start1,end1,start2,end2));
            System.out.println("==========");
        }catch (Exception e){
        }
    }

    public static Boolean compareTwoTime(Date startOne,Date endOne,Date startTwo,Date endTwo){
        //after 当start1小于等于end2时返回flase  before end1大于等于start2返回flase
        if ((!startOne.after(endTwo))&&(!endOne.before(startTwo))){
//            System.out.println("时间重叠");
            //时间重叠
            return true;
        }
//        System.out.println("时间不重叠");
        //时间不重叠
        return false;
    }


    public static Boolean compareTwoTime(Date startOne,Date endOne ){
        //after 当start1小于等于end2时返回false  before end1大于等于start2返回false
        Boolean flag  =  startOne.after(endOne);
        System.out.println("flag = "+flag);
        if(flag) {
//            System.out.println("Exception 开始时间要小于结束时间！");
            return true;
        }
//        System.out.println("正确！");
        return false;
    }
}
