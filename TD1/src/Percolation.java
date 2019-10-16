public class Percolation {
    static int size = 10;
    static int length = size * size;
    static boolean grid[] = new boolean[length];

    public static void init(){
        int i;
        for (i = 0; i < length; i++) grid[i] =false;
        for (i = 0; i < length; i++) UnionFind.equiv[i] =i;
        for (i = 0; i < length; i++) UnionFind.height[i] =0;
    }


    public static int random_shadow(){
        int noir;
        noir = (int) Math.floor( Math.random()*length);
        while (grid[noir] == true) noir = (int) Math.floor( Math.random()*length);
        grid[noir] = true;
        return noir;
    }
// * noir true; - blanc false

    public static boolean is_percolation(int n){
        int i;
        boolean up = false, down = false;
        for (i = 0; i<length; i = i+10){
            if (UnionFind.find(i) == UnionFind.find(n))  up = true;
        }
        for (i = 9; i<length; i = i+10){
            if (UnionFind.find(i) == UnionFind.find(n)) down = true;
        }
        return (up&down);
    }

    public static void propagate_union(int x){
        if (grid[x]){
            if (x-1>=0 && x%size!=0 && grid[x-1] &&  UnionFind.find(x-1) != UnionFind.find(x)) {
                UnionFind.union(x-1,x);
                propagate_union(x-1);
            }
            if (x+1<length && x%size!=size-1 && grid[x+1] && UnionFind.find(x+1) != UnionFind.find(x)) {
                UnionFind.union(x+1,x);
                propagate_union(x+1);
            }
            if (x-size>=0 && grid[x-size] && UnionFind.find(x-size) != UnionFind.find(x)) {
                UnionFind.union(x-size,x);
                propagate_union(x-size);
            }
            if (x+size<length && grid[x+size] && UnionFind.find(x+size) != UnionFind.find(x)) {
                UnionFind.union(x+size,x);
                propagate_union(x+size);
            }
        }
    }

    public static double percolation(){
        int i;
        float num_noir = 0;
        double percentage;
        while (true) {
            i = random_shadow();
            if (UnionFind.equiv[i] == i) {
                propagate_union(i);
                num_noir++;
                if (is_percolation(i)) break;
            }
        }
        percentage = num_noir / length;
        return percentage;
    }

    public static double  montecarlo(int n){
        double moyen=0;
        int i;
        for (i=0; i<n; i++){
            init();
            moyen = moyen + percolation();
        }
        moyen = moyen/n;
        return moyen;
    }

    //2eme optimisation

    public static void propagate_union2(int x){
        if (grid[x]){
            if (x-1>=0 && x%size!=0 && grid[x-1] &&  UnionFind.find2(x-1) != UnionFind.find2(x)) {
                UnionFind.union2(x-1,x);
                propagate_union2(x-1);
            }
            if (x+1>=0 && x%size!=size-1 && grid[x+1] && UnionFind.find2(x+1) != UnionFind.find2(x)) {
                UnionFind.union2(x+1,x);
                propagate_union2(x+1);
            }
            if (x-size>=0 && grid[x-size] && UnionFind.find2(x-size) != UnionFind.find2(x)) {
                UnionFind.union2(x-size,x);
                propagate_union2(x-size);
            }
            if (x+size<length && grid[x+size] && UnionFind.find2(x+size) != UnionFind.find2(x)) {
                UnionFind.union2(x+size,x);
                propagate_union2(x+size);
            }
        }
    }

    public static double percolation2(){
        int i;
        float num_noir = 0;
        double percentage;
        while (true) {
            i = random_shadow();
            if (UnionFind.equiv[i] == i) {
                propagate_union2(i);
                num_noir++;
                if (is_percolation(i)) break;
            }
        }
        percentage = num_noir / length;
        return percentage;
    }

    public static double  montecarlo2(int n){
        double moyen=0;
        int i;
        for (i=0; i<n; i++){
            init();
            moyen = moyen + percolation2();
        }
        moyen = moyen/n;
        return moyen;
    }
}

