package horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;

/**
 * 这个类是为了解决马踏棋盘问题，这个问题主要是dfs+贪心算法解决
 * 首先是从当前节点出发，设置为已访问，然后找出下一个能走的点，遍历能走的点
 * 如果点未被访问过，那么就dfs递归才做，
 * 每次操作将棋盘位置设置为步数，同时设置为已访问
 * 如果操作到最后发现未完成，步数没有达到棋盘的大小，那么棋盘当前位置清零，访问设为未访问，回溯
 * 成功则设置finished为true
 *
 * @author abs
 * @Date 2019/8/5 - 7:30
 */
public class HorseChessboard {
    private static int X;
    private static int Y;
    private static boolean[] visted;
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        //测试骑士周游算法是否正确
        X = 8;
        Y = 8;
        int row = 1; //马儿初始位置的行，从1开始编号
        int column = 1; //马儿初始位置的列，从1开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visted = new boolean[X * Y];//初始值都是false
        //测试一下耗时
        long start = System.currentTimeMillis();
        travelChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");

        printChessBoard(chessboard);
    }

    private static void printChessBoard(int[][] chessboard) {
        //输出棋盘的最后情况
        System.out.println("-------------------------------");
        for(int[] rows : chessboard) {
            for(int step: rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    public static void travelChessboard(int[][] chessboard,int row,int column,int step){
        if (step == X*Y) {
            printChessBoard(chessboard);
        }

        visted[row * X + column] = true;
        chessboard[row][column] = step;
        ArrayList<Point> ps = next(new Point(column,row));

        sort(ps);
        int newRow = -1;
        int newColumn = -1;
        while(ps.size() > 0){
            Point p1 = ps.remove(0);
            newRow = p1.y;
            newColumn = p1.x;
            if(!visted[newRow*X + newColumn]){
                travelChessboard(chessboard,newRow,newColumn,step+1);
            }
        }

        if(step < X*Y && !finished){
            chessboard[row][column] = 0;
            visted[row * X+column] =false;
        } else {
            finished = true;
        }
    }

    public static void sort(ArrayList<Point> next){
        //将其不递减的排序,排序的关键是他们的下一个访问的节点数目
        next.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                return count1 - count2;
            }
        });
    }
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //表示马儿可以走5这个位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }
}
