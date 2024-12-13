import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        while (sc.hasNextLine()){
            String str1 = sc.nextLine();
            String str2 = sc.nextLine();
            String str3 = sc.nextLine();
            int x = Integer.parseInt(str1.substring(str1.indexOf("+")+1,str1.indexOf(",")));
            int p = Integer.parseInt(str1.substring(str1.lastIndexOf("+")+1));
            int y = Integer.parseInt(str2.substring(str2.indexOf("+")+1,str2.indexOf(",")));
            int o = Integer.parseInt(str2.substring(str2.lastIndexOf("+")+1));
            int z = Integer.parseInt(str3.substring(str3.indexOf("=")+1,str3.indexOf(",")));
            int l = Integer.parseInt(str3.substring(str3.lastIndexOf("=")+1));
            double b = (double) (z - (l * y / o)) /(x-((double) (p * y) /o));
            if(Math.abs(b-Math.round(b))<0.01){
                double a = (z-x*Math.round(b))/(double)y;
                if (Math.abs(a-Math.round(a))<0.01){
                    total += (long) (Math.round(a)+Math.round(b)*3);
                }
            }
            /*for (int i = 0; i < 100; i++) {
                double a = (z-x*i)/(double)y;
                if (a*o+i*p==l) {
                    if (a == (int) a) {
                        if (a <= 100) {
                            total += (long) (a + i * 3);
                            break;
                        }
                    }
                }
            }*/
            sc.nextLine();
        }
        System.out.println(total);
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        while (sc.hasNextLine()){
            String str1 = sc.nextLine();
            String str2 = sc.nextLine();
            String str3 = sc.nextLine();
            double x = Integer.parseInt(str1.substring(str1.indexOf("+")+1,str1.indexOf(",")));
            double p = Integer.parseInt(str1.substring(str1.lastIndexOf("+")+1));
            double y = Integer.parseInt(str2.substring(str2.indexOf("+")+1,str2.indexOf(",")));
            double o = Integer.parseInt(str2.substring(str2.lastIndexOf("+")+1));
            double z = 10000000000000.0 +Integer.parseInt(str3.substring(str3.indexOf("=")+1,str3.indexOf(",")));
            double l = 10000000000000.0 +Integer.parseInt(str3.substring(str3.lastIndexOf("=")+1));
            double b = (double) (z - (l * y / o)) /(x-((double) (p * y) /o));
            if(Math.abs(b-Math.round(b))<0.01){
                double a = (z-x*Math.round(b))/(double)y;
                if (Math.abs(a-Math.round(a))<0.01){
                    total += (long) (Math.round(a)+Math.round(b)*3);
                }
            }
            sc.nextLine();
        }
        System.out.println(total);
    }
}
