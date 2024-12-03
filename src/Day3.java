import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        boolean enabled = true;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                if (str.startsWith("do()",i)){
                    enabled = true;
                }
                if (str.startsWith("don't()",i)){
                    enabled = false;
                }
                if (str.startsWith("mul(", i) && enabled){
                    int d = str.substring(i).indexOf(")")+i;
                    int d2 = str.substring(i).indexOf(",")+i;
                    if (d2 > d){
                        continue;
                    }
                    String num1 = str.substring(i+4,d2);
                    String num2 = str.substring(d2+1,d);
                    try{
                        total+=Integer.parseInt(num1)*Integer.parseInt(num2);
                    }
                    catch(Exception ignored){

                    }
                }
            }
        }
        System.out.println(total);
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                if (str.startsWith("mul(", i)){
                    int d = str.substring(i).indexOf(")")+i;
                    int d2 = str.substring(i).indexOf(",")+i;
                    if (d2 > d){
                        continue;
                    }
                    String num1 = str.substring(i+4,d2);
                    String num2 = str.substring(d2+1,d);
                    try{
                        total+=Integer.parseInt(num1)*Integer.parseInt(num2);
                    }
                    catch(Exception ignored){

                    }
                }
            }
        }
        System.out.println(total);
    }
}
