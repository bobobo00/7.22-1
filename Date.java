package java_1;

import java.util.Scanner;

public class Date {
	private int year;
	private int month;
	private int day;
	private static final int[] Days_0f_Month= {
	31,28,31,30,31,30,31,31,30,31,30,31
	}; 
	
	public Date(){
		this.year=year;
		this.month=month;
		this.day=day;
	}
	
	public boolean juerDate(){
		if(this.year>3000||this.year<-1) {
			System.out.println("年份的范围为0-3000");
			return false ;
		}
		if(this.month>12||this.month<0){
			System.out.println("月份的范围为1-12");
			return false;
		}
		if(this.day>Days_0f_Month[this.month-1]||this.day<0) {
			System.out.println("当前月份没有该天");
			return false;
		}
		return true;
	}
	public void inputDate(int year,int month,int day) {
		this.year=year;
		this.month=month;
		this.day=day;
	}
	public void printDate() {
		if(!(juerDate())) {
			System.out.println("请输入正确的年-月-日");
		}else {
		System.out.println("年-月-日："+year+"-"+month+"-"+day);
		}
	}
	public Date after(int days) {
		while(days>(Days_0f_Month[this.month-1]-this.day)) {
			days=days-Days_0f_Month[this.month-1];
			if((this.month+1)>12) {
				this.year=year+1;
				this.month=1;
			}
			else {
			this.month=month+1;
			}
		}
		this.day=day+days;
		return this;
	}
	public Date immuateableAfter(int days) {
		Date other=new Date();
		other.year=this.year;
		other.month=this.month;
		other.day=this.day;
		while(days>(Days_0f_Month[this.month-1]-other.day)) {
			days=days-Days_0f_Month[other.month-1];
			if((other.month+1)>12) {
				other.year=year+1;
				other.month=1;
			}
			else {
				other.month=month+1;
			}
		}
		other.day=day+days;
		return other;
	}
	public Date before(int days) {
		while(days>(Days_0f_Month[this.month-1]-this.day)) {
			days=days-Days_0f_Month[this.month-1];
			if((this.month-1)<1) {
				this.year=year-1;
				this.month=12;
			}
			else {
			this.month=month-1;
			}
		}
		this.day=day-days;
		return this;
	}
	public static int diff(Date a, Date b) {
		int days=0;
		int day_b=b.day;
		b.month--;
		int day_a=(calcDaysOfMonth(a.year, a.month)-a.day);
		a.month++;
		
		while(b.month!=0) {
			day_b+=calcDaysOfMonth(b.year, b.month);
			b.month--;
			}
		while(a.month!=13) {
			day_a+=calcDaysOfMonth(a.year, a.month);
			a.month++;
		}
		
		a.year++;
		for(;b.year>a.year;a.year++) {
			if(isLeapYear(a.year)) {
				days+=366;
			}
			else {
			days+=365;
			}
		}
		days+=(day_b+day_a);
		return days;
	}
	private static boolean isLeapYear(int year) {
		if(year%4==0&&year%100!=0) {
			return true;
		}
		if(year%400==0) {
			return true;
		}
		return false;
	}
	
	private static int calcDaysOfMonth(int year, int month) {
		if(isLeapYear(year)) {
			Days_0f_Month[1]=29;
		}
		return Days_0f_Month[month-1];
	}
	public static void main(String[] args) {
		Date d=new Date();
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入年-月-日");
		int year=scanner.nextInt();
		int month=scanner.nextInt();
		int day=scanner.nextInt();
		d.inputDate(year, month,day);
		d. printDate();
		System.out.println("请输入目标日期大于当前日期天数，并将当前日期修改为目标日期：");
		d. after(scanner.nextInt());
		d. printDate();
		System.out.println("请输入目标日期小于当前日期天数，并将当前日期修改为目标日期：");
		d.before(scanner.nextInt());
		System.out.println("请输入目标日期大于当前日期天数，不修改当前日期：");
		Date o=d.immuateableAfter(scanner.nextInt());
		o.printDate();
		Date a=new Date();
		Date b=new Date();
		System.out.println("请输入a的年-月-日");
		a.inputDate(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
		System.out.println("请输入b的年-月-日");
		b.inputDate(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
		System.out.println("相差"+diff(a, b)+"天");
	}

}
