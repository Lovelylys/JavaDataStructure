package binarysearchnorecursion;

/**
 * 不使用递归实现二分查找
 *
 * @author abs
 * @Date 2019/8/4 - 9:29
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        //测试
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 22);
        System.out.println("index=" + index);//
    }

    public static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid;
            }else if(value < arr[mid]){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1; //如果找不到的情况下
    }
}
