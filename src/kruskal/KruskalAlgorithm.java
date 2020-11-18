package kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 这个类是为了实现kruskal算法，最小生成树的算法，这个算法的核心是通过选择最小边来连成最小生成树
 * 拿到所有的边集合，弄成类的集合形式，对其进行升序排序，
 * 然后一个循环,循环边的个数，创建一个同等大的结果数组边，判断当前的边是否形成回路，不是就加进结果数组，同时更新终点数组,动态数组
 * 是就直接循环下一个
 * 判断回路的关键是，当前边的两个节点的终点是否是同一个
 *
 * @author abs
 * @Date 2019/8/4 - 22:47
 */
public class KruskalAlgorithm {
    private static final int INF =Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        Graph graph = new Graph(vertexs.length,vertexs,matrix);
        ArrayList<Edge> edges = kruskal(graph);
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }
    public static ArrayList<Edge> kruskal(Graph graph){
        ArrayList<Edge> edges = getEdges(graph);
        Collections.sort(edges);
        int[] end = new int[graph.edgeNum];
        ArrayList<Edge> res = new ArrayList<>();

        for (int i = 0; i < graph.edgeNum; i++) {
            Edge edge = edges.get(i);

            int m = getEnd(end,graph.getIndex(edge.start));
            int n = getEnd(end,graph.getIndex(edge.end));
            if(m != n){
                res.add(edge);
                end[m] = n;
            }
        }
        return res;
    }

    public static int getEnd(int[] end,int index){
        while(end[index] != 0){
            index = end[index];
        }
        return index;
    }
    public static ArrayList<Edge> getEdges(Graph graph){
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < graph.vertexNum; i++) {
            for (int j = i+1; j < graph.vertexNum; j++) {
                if(graph.edges[i][j] != INF){
                    Edge edge = new Edge(graph.getVertex(i),graph.getVertex(j),graph.edges[i][j]);
                    edges.add(edge);
                }
            }
        }
        return  edges;
    }
}
class Graph{
    int vertexNum;
    char[] vertex;
    int[][] edges;
    int edgeNum;

    public int getIndex(char input){
        for (int i = 0; i < vertexNum; i++) {
            if(vertex[i] == input){
                return i;
            }
        }
        return -1;
    }

    public char getVertex(int index){
        return vertex[index];
    }

    public Graph(int vertexNum, char[] vertex, int[][] edges) {
        this.vertexNum = vertexNum;
        this.vertex = vertex;
        this.edges = edges;
        getEdgeNum();
    }

    public int getEdgeNum(){
        for (int i = 0; i < vertexNum; i++) {
            for (int j = i+1; j < vertexNum; j++) {
                if(edges[i][j] != Integer.MAX_VALUE){
                    edgeNum++;
                }
            }
        }
        return edgeNum;
    }
}
class Edge implements Comparable<Edge>{
    char start;
    char end;
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
