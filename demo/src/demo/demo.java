package demo;

import java.util.Scanner;

public class demo {	
	static int color[]= {0,0,0,0};//分别记录c n s h 的数量
	static int number[]= {0,0,0,0,0,0,0,0,0,0,0,0};//记录数字1-13的个数
	static int score=0;//记录成绩
	static String temporary=null;
	static char temp[];
	static char question_02;
	static int count=0;//问题3中组合数的个数
	static int sum=5;//问题3中组合的和
	
	public static void main(String[] args) {
		
		for(int i=0;i<3;i++) {
			System.out.println(temporary);
			temp=temporary.toCharArray();
			init(temp);
		}
		
		//question01_01
		if(question_01_01()) score+=4;
		
		//获取 start card
		System.out.println(temporary);
		temp=temporary.toCharArray();
		question_02=temp[1];//记录start card 的花色
		init(temp);
		//question01_02
		if(question_01_02()) score++;
		
		
		//question02 
		if(question_02=='C') score+=color[0];
		if(question_02=='N') score+=color[1];
		if(question_02=='S') score+=color[2];
		if(question_02=='H') score+=color[3];
		
		//question03
		score+=question03();
		
		//question04
		score+=question04();
		
		//question05
		score+=question05(color);
		System.out.println("socre "+score);
	}
	//初始化数组
	static void init(char[] temp) {     
		 if(temp[0]=='A') number[0]++;
		 if(temp[0]=='2') number[1]++;
		 if(temp[0]=='3') number[2]++;
		 if(temp[0]=='4') number[3]++;
		 if(temp[0]=='5') number[4]++;
		 if(temp[0]=='6') number[5]++;
		 if(temp[0]=='7') number[6]++;
		 if(temp[0]=='8') number[7]++;
		 if(temp[0]=='9') number[8]++;
		 if(temp[0]=='T') number[9]++;
		 if(temp[0]=='J') number[10]++;
		 if(temp[0]=='Q') number[11]++;
		 if(temp[0]=='K') number[12]++;
		 if(temp[1]=='C') color[0]++;
		 if(temp[1]=='D') color[1]++;
		 if(temp[1]=='S') color[2]++;
		 if(temp[1]=='H') color[3]++;
	}
	
	//记录手牌相同数为 4 5 
	static boolean question_01_01() {
		for(int i=0;i<12;i++) {
			if(number[i]==4) return true;
		}
		return false;
	}
	static boolean question_01_02() {
		for(int i=0;i<12;i++) {
			if(number[i]==5) return true;
		}
		return false;
	}
	
	//question03
    static int question03() {
        demo.quickSort(number, 0, number.length-1);
        demo.sum(number.length-1);
        return count;
    }
    private  static int[] sum(int n) {
        if(n == 0){
            return new int[]{number[0]};
        }        
        int length = (int)Math.pow(2, (n+1))-1;
        int a[] = new int[length];
        int b[] = sum(n-1);       
        int i=0;
        for(i=0; i<(length-1)/2; i++) {
            a[i] = b[i];
            int temp = b[i] + number[n];
            if(temp == sum){
                count++;
            }
            a[i+(length+1)/2] = temp;
        }
        if(number[n] == sum) {
            count++;
        }
        a[(length-1)/2] = number[n];
        return a;
    }
    private static void quickSort(int []array, int left, int right) {
        if(left >= right) {
            return;
        }
        int q = pagenation(array, left, right);
        quickSort(array, left, q-1);
        quickSort(array, q+1, right);
    }
    private static int pagenation(int []array, int left, int right) {
        int i=left, j=right+1;
        int p = (int)Math.random()*(right-left+1)+left;
        swap(array,left, p);
        int x = array[left];
        
        while(true) {
            while(i<right && array[++i] < x);
            while(j>left && array[--j] > x);
            if(i > j) {
                break;
            }
            swap(array,i,j);
        }
        return j;
    }
    private static void swap(int [] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
	
	//找到相同数字的手牌并处理
	static int question04() {
		//same2 2point same3 6point same4 12point same5 20point
		int same2=0,same3=0,same4=0,same5=0;
		for(int i=0;i<12;i++) {
			if(number[i]==2) same2++;
			if(number[i]==3) same3++;
			if(number[i]==4) same4++;
			if(number[i]==5) same5++;
		}
		return same2*2+same3*6+same4*12+same5*20;
	}
	
	//求最大连续个数的长度  5个数中3个以上连续故只有一个连续数
	static int question05(int[] number) { 
	    if (number == null || number.length == 0) return -1; 
	    int maxSucc = 1; 
	    int temp = 1; 
	    for(int i = 1; i < number.length; i++) { 
	        if (number[i] -1 == number[i - 1]) { 
	            temp++; 
	        } else { 
	            if (temp > maxSucc) { 
	                maxSucc = temp; 
	            } 
	            temp = 1; 
	        } 
	    } 
	    return maxSucc > temp ? maxSucc : temp; 
	}  
}
