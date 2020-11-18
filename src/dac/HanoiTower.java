package dac;

/**
 * @author abs
 * @Date 2019/8/4 - 9:35
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5,'a','b','c');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("将第" + num + "个盘子" + "从"+a+"移动到"+c);
        }else{
            //分为两步，上面所有的盘移动b柱上，最后一个盘移到c盘上，然后将b的盘子以到c盘上
            hanoiTower(num-1,a,c,b);
            System.out.println("将第" + num + "个盘子" + "从"+a+"移动到"+c);
            hanoiTower(num-1,b,a,c);
        }
    }
}
