package kmp;

import java.util.Arrays;

/**
 * 这个类是为了实现kmp算法匹配字符串
 * kmp的重点就在于会利用上一段匹配一半的字符串位置信息，而不是像暴力匹配一样直接从第二个字符继续匹配
 * 其重点是部分匹配表，部分匹配表的长度和str2一样，先找出前后缀匹配的字符，如果一样了，那么j++，j赋值到next匹配表中，否则就将
 * j = next[j-1]回溯到匹配为止，或者是j = 0
 * 而匹配的方式和部分匹配表很像
 *
 * @author abs
 * @Date 2019/8/4 - 12:51
 */
public class KmpMatch {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //String str2 = "BBC";

        int[] next = kmpNext("ABCDABD"); //[0, 1, 2, 0]
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpMatch(str1, str2, next);
        System.out.println("index=" + index); // 15了
    }
    public static int kmpMatch(String str1,String str2,int[] next){
        for (int i = 0,j=0; i < str1.length(); i++) {

            while(j >0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }

            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }

            if(j == str2.length()){
                return i - j +1;
            }
        }
        return -1;
    }
    public static int[] kmpNext(String str2){
        int[] next = new int[str2.length()];

        for (int i = 1,j=0; i < str2.length(); i++) {

            while(j > 0 && str2.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if(str2.charAt(i) == str2.charAt(j)){
                j++;
            }

            next[i] = j;
        }
        return next;
    }
}
