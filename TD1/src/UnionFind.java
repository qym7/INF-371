public class UnionFind {
    static int equiv[] = new int[Percolation.length];
    static int height[] = new int[Percolation.length];

    public static int find(int x){
        if (x != equiv[x])  equiv[x] = find(equiv[x]);
        return equiv[x];
    }

    static void union(int x, int y){
        int xroot = find(x);
        int yroot = find(y);
        if (xroot == yroot) return;
        if (height[xroot] > height[yroot])
            equiv[yroot] = xroot;
        else if (height[xroot] < height[yroot]) equiv[xroot] = yroot;
        else {
            equiv[xroot] = yroot;
            height[yroot] ++;
        }
    }

//    public static int union(int x, int y){
//        int new_height;
//        if (height[find(x)]>=height[find(y)]){
//            equiv[find(y)] = find(x);
//            height[find(x)] = Math.max(height[find(y)]+1,height[find(x)]);
//            height[find(y)] = 0;
//            new_height = height[find(x)];
//        }
//        else {
//            equiv[find(x)] = equiv[find(y)];
//            height[find(x)] = 0;
//            new_height = height[find(y)];
//        }
//        return new_height;
//    }

    public static int find2(int x){
        int i;
        while (x != equiv[x]) {
            i = equiv[x];
            equiv[x] = equiv[i];
            x = i;
        }
        return x;
    }

    public static int union2(int x, int y){
        equiv[find(y)] = find(x);
        return find(x);
    }

}