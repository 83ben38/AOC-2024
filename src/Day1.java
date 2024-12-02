import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();
        while (sc.hasNextInt()){
            a1.add(sc.nextInt());
            a2.add(sc.nextInt());
        }
        a1.sort(Integer::compareTo);
        a2.sort(Integer::compareTo);
        long total = 0;
        for (int i = 0; i < a1.size(); i++) {
            total+=Math.abs(a1.get(i)-a2.get(i));
        }
        System.out.println(total);
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();
        while (sc.hasNextInt()){
            a1.add(sc.nextInt());
            a2.add(sc.nextInt());
        }
        long total = 0;
        for (Integer integer : a1) {
            for (Integer value : a2) {
                if ((int)integer == (int)value) {
                    total += integer;
                }
            }
        }
        System.out.println(total);
    }
}
