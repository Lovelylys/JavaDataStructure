package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 这个类是为了展示贪心算法，实现从广播台中选择能够覆盖全部地区，最少数目的广播台，
 * 首先是创建多个广播台，hashmap，string，hashset，set放置覆盖地区
 * 创建一个总的areas，hashset放置全部地区
 *
 *
 * 方法，传进来，所有的地区，以及多个广播台，返回一个list即可，
 * 方法内
 * 创建一个临时的hashset，放置选择出最大的广播台的地区
 *  * 创建一个maxKey，放置该最大广播台的key
 *  创建一个list，放置每次选择的maxKey
 *  while 总的地区不为空，则一直循环
 *  每次循环，清空临时的set，然后从头开始扫描到临时set中，比较大小个数，结束循环后
 *  list添加，总地区清空添加的地区
 *
 * @author abs
 * @Date 2019/8/4 - 13:09
 */
public class BroadCast {
    public static void main(String[] args) {
        //创建广播电台,放入到Map
        HashMap<String,HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        ArrayList<String> list = getList(allAreas,broadcasts);
        System.out.println(list);
    }
    /*
     * 创建一个临时的hashset，放置选择出最大的广播台的地区
     *  * 创建一个maxKey，放置该最大广播台的key
     *  创建一个list，放置每次选择的maxKey
     *  while 总的地区不为空，则一直循环
     *  每次循环，清空临时的set，然后从头开始扫描到临时set中，比较大小个数，结束循环后
     *  list添加，总地区清空添加的地区
     */
    public static ArrayList<String> getList(HashSet<String> allAreas, HashMap<String,HashSet<String>> broadCasts){
        ArrayList<String> list = new ArrayList<>();
        HashSet<String> tempSet = new HashSet<>();
        String maxKey = null;

        while(allAreas.size() > 0){
            maxKey = null;//每次循环开始的时候，最大的数字都要初始化，因为是新的一轮，allAreas已经变化了

            for(String key : broadCasts.keySet()){
                tempSet.clear();
                tempSet.addAll(broadCasts.get(key));
                tempSet.retainAll(allAreas);// 交集,但是最后一次tempSet不一定会是最大的交集，maxKey才是最大的，temp可能不是最大的

                if(tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadCasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if(maxKey != null){
                list.add(maxKey);
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }
        return list;
    }
}
