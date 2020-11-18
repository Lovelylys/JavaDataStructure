package prim;

/**
 * 这个类是为了实现最小生成树的prim算法
 * 这个算法的设计是，选择一个开始的节点，设为已访问，然后找到已经访问的节点列表，每个节点输出其下一个连接的节点
 * 访问集合中与下一个未被访问过的节点的最小边，输出前后的连接；
 * 循环直到所有的节点都这样被访问过，，即循环图的节点个数为次数
 * @author abs
 * @Date 2019/8/4 - 22:27
 */
public class Prim {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};

        //创建MGraph对象
        Graph graph = new Graph(verxs,data,weight);
        prim(graph,0);
    }
    public static void prim(Graph graph,int index){
        int[] visited = new int[graph.vertexNum];
        visited[index] = 1;
        for (int k = 1; k < graph.vertexNum; k++) {
            int min = 65536;
            int h1 = -1;
            int h2 = -1;
            for (int i = 0; i < graph.vertexNum; i++) {
                for (int j = 0; j < graph.vertexNum; j++) {
                    if(visited[i] == 1 && visited[j] == 0 && graph.edges[i][j] < min){
                        min = graph.edges[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println(graph.getVertex(h1) + "->" + graph.getVertex(h2) + ":weight" + min);
            visited[h2] = 1;
        }
    }
}
class Graph{
    int vertexNum;
    char[] vertex;
    int[][] edges;

    public Graph(int vertexNum, char[] vertex, int[][] edges) {
        this.vertexNum = vertexNum;
        this.vertex = vertex;
        this.edges = edges;
    }

    public char getVertex(int index){
        return vertex[index];
    }
}
