import java.util.Scanner;

public class main
{
    static public void main(String[] args)
    {
        double time_ini =  System.currentTimeMillis();
        double duree, percentage;
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        percentage = Percolation.montecarlo(n);
        duree = System.currentTimeMillis() - time_ini;
        System.out.println(percentage);
        System.out.println(duree);

        double time_ini2 =  System.currentTimeMillis();
        double duree2, percentage2;
        int n2;
        Scanner sc2 = new Scanner(System.in);
        n2 = sc2.nextInt();
        percentage2 = Percolation.montecarlo2(n2);
        duree2 = System.currentTimeMillis() - time_ini2;
        System.out.println(percentage2);
        System.out.println(duree2);

//        double time_ini =  System.currentTimeMillis();
//        double duree, percentage;
//        int n;
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        percentage = Percolation.montecarlo(n);
//        duree = System.currentTimeMillis() - time_ini;
//        System.out.println(percentage);
//        System.out.println(duree);
    }
}