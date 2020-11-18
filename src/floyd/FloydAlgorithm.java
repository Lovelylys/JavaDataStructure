package floyd;

import java.util.Arrays;

/**
 * 这个类是为了实现floyd算法，生成最短路径，这个算法生成的是所有节点到其他节点的最短路径
 * 将所有的节点做为中间节点，遍历其他节点经过此节点是否路径变短，变短则更新路径和前置节点信息
 * 初始化路径和前置节点信息
 * @author abs
 * @Date 2019/8/5 - 7:14
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length,vertex,matrix);
        //调用弗洛伊德算法
        floyd(graph);
        graph.show();
    }

    public static void floyd(Graph graph){
        int len;
        for (int k = 0; k < graph.vertexNum; k++) {
            for (int i = 0; i < graph.vertexNum; i++) {
                for (int j = 0; j < graph.vertexNum; j++) {
                    len = graph.distance[i][k] + graph.distance[k][j];
                    if(len < graph.distance[i][j]){
                        graph.distance[i][j] = len;
                        graph.pre[i][j] = graph.pre[k][j];
                    }
                }
            }
        }
    }
}
class Graph{
    int vertexNum;
    char[] vertex;
    int[][] distance;
    int[][] pre;

    public Graph(int vertexNum, char[] vertex, int[][] edges) {
        this.vertexNum = vertexNum;
        this.vertex = vertex;
        this.distance = edges;
        this.pre = new int[vertexNum][vertexNum];

        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    public void show(){
        //为了显示便于阅读，我们优化一下输出
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        for (int k = 0; k < distance.length; k++) {
            // 先将pre数组输出的一行
            for (int i = 0; i < distance.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            // 输出dis数组的一行数据
            for (int i = 0; i < distance.length; i++) {
                System.out.print("("+vertex[k]+"到"+vertex[i]+"的最短路径是" + distance[k][i] + ") ");
            }
            System.out.println();
            System.out.println();

        }
    }
}
