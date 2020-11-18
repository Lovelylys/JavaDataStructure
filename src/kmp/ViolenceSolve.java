package kmp;

/**
 * 这个算法是为了暴力匹配字符串
 * 所谓暴力匹配就是，两个字符串，转为字符数组，两个指针
 * 当相等的时候，两个指针一起移动，不等的时候，str1的指针重新从匹配的第二个字符回溯移动，即i = i - （j-1）
 * 而j 指针归零，整个过程直到j等于str2的长度则返回，找到了字符串
 * @author abs
 * @Date 2019/8/4 - 12:44
 */
public class ViolenceSolve {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //测试暴力匹配算法
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceSolve(str1, str2);
        System.out.println("index=" + index);
    }
    public static int violenceSolve(String str1,String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while(i < str1.length() && j < str2.length()){
            if(chars1[i] == chars2[j]){
                i++;
                j++;
            }else{
                i = i -(j-1);
                j = 0;
            }
        }
        if(j == str2.length()){
            return i - j;
        }else{
            return -1;
        }
    }
}
