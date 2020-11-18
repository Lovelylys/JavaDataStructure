package dijstra;


import java.util.Arrays;

/**
 * 这个算法是为了生成最短路径，在图中选择任意一点，生成其到其他所有节点的最短路径长度
 * 需要初始化三个最重要的参数
 * visited[] 做为已经访问过的节点数组
 * pre[] 是被访问节点的前置节点
 * distance[] 是出发点到其他所有节点的距离
 *
 * 访问第一个节点，更新节点的前置节点信息和距离信息，更新的关键是将该节点做为中间顶点，经过此节点的距离是否变短，此节点是否未被访问
 * 变短则替换前置节点和距离信息
 * 然后接着访问下一个节点，遍历所有的节点，从distance中找到边最小的节点，设为已访问
 * 下一个节点就是此时离该节点最近的节点，就是distance数组中边最小的节点了，广度优先搜索
 * 如此，循环，循环次数为图的节点个数
 *
 * @author abs
 * @Date 2019/8/4 - 23:38
 */
public class Dijstra {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建 Graph对象
        Graph graph = new Graph(vertex.length,vertex, matrix);
        graph.dijstra(2);
        graph.show();
    }

}
class Graph{
    int vertexNum;
    char[] vertex;
    int[][] edges;
    private VertexVisted vertexVisted;

    public void show(){
        vertexVisted.show();
    }
    public Graph(int vertexNum, char[] vertex, int[][] edges) {
        this.vertexNum = vertexNum;
        this.vertex = vertex;
        this.edges = edges;
    }

    public void dijstra(int index){
        vertexVisted = new VertexVisted(vertexNum,index);
        update(index);
        for (int i = 1; i < vertexNum; i++) {
            index = vertexVisted.updateAddr();
            update(index);
        }
    }

    public void update(int index){// 需要更新的就是前置节点和距离信息 找到该节点到其他节点的距离，做为中间顶点
        int len = 0;
        for (int i = 0; i < edges[index].length; i++) {
            len = vertexVisted.getDis(index) + edges[index][i];
            if(!vertexVisted.isVisted(i) && len < vertexVisted.getDis(i)){
                vertexVisted.updateDistance(i,len);
                vertexVisted.updatePre(i,index);
            }
        }
    }
}
class VertexVisted{
    int[] visted;
    int[] pre;
    int[] distance;

    public VertexVisted(int length,int index) {
        visted = new int[length];
        pre = new int[length];
        distance = new int[length];
        visted[index] = 1;
        Arrays.fill(distance,65535);
        distance[index] = 0;
    }

    public int updateAddr(){//找到当前节点的下一个节点，下一个节点的距离最近
        int min = 65535;
        int index = 0;
        for (int i = 0; i < visted.length; i++) {
            if(!isVisted(i) && distance[i] < min){
                min = distance[i];
                index = i;
            }
        }
        visted[index] = 1;
        return index;
    }

    public boolean isVisted(int index){
        return visted[index] == 1;
    }
    public void updatePre(int index,int j){
        pre[index] = j;
    }

    public void updateDistance(int index,int len){
        distance[index] = len;
    }

    public int getDis(int index){
        return distance[index];
    }

    public void show(){
        //为了好看最后的最短距离，我们处理
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : distance) {
            if (i != 65535) {
                System.out.print(vertex[count] + "("+i+") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }
}
