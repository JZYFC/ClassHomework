package io.github.jzyfc.One;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date() {
        this.year = Calendar.getInstance().get(Calendar.YEAR);
        this.month = Calendar.getInstance().get(Calendar.MONTH);
        this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public void showDate() {
        System.out.printf("%d年%d月%d日\n", this.year, this.month, this.day);
    }

    public void setDate() {
        while (true) {
            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("请输入年份：");
                this.year = sc.nextInt();
                System.out.println("请输入月份：");
                this.month = sc.nextInt();
                System.out.println("请输入日期：");
                this.day = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("输入格式错误，请重新输入");
            }
        }
    }
}
