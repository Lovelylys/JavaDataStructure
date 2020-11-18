package dynamic;

import java.util.Arrays;

/**
 * 这个类是为了解决0 1背包问题，动态规划
 * 动态规划求依赖于上一次的求解结果，背包的大小，从小到大求解，上一次的求解结果就会是下一个的基础
 *
 * @author abs
 * @Date 2019/8/4 - 12:21
 */
public class KnapspackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] value = {1500, 3000, 2000};
        int m = 4;// 背包的数量
        int n = value.length;// 物件的数量
        int[][] v = new int[n + 1][m + 1];
        int[][] path = new int[v.length][v[0].length];

        for (int i = 0; i < value.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        for (int i = 1; i < v.length; i++) {//从第一个开始，忽视掉前面初始化的第一行和第一列
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i-1][j],value[i-1] + v[i-1][j - w[i-1]]);
                    if (v[i - 1][j] > value[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = value[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1; //记录最优的解
                    }
                }
            }
        }

        for (int[] ints : v) {
            System.out.println(Arrays.toString(ints));
        }
        int i = v.length - 1;
        int j = v[0].length - 1;
        while(j > 0 && i > 0){
            if(path[i][j] == 1){
                System.out.println("第"+ i + "个物品放进背包");
                j -= w[i-1];
            }
            i--;
        }
    }
}
