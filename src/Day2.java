import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        int total = 0;
        sus : while (sc.hasNextLine()){
            Scanner sc2 = new Scanner(sc.nextLine());
            boolean increasing;
            int n1 = sc2.nextInt();
            int n2 = sc2.nextInt();
            increasing = n2 > n1;
            if (n2-n1 == 0 || Math.abs(n2-n1)>3){
                continue;
            }
            int last = n2;
            while(sc2.hasNextInt()){
                int next = sc2.nextInt();
                if (next-last==0 || Math.abs(next-last) > 3 || (increasing ? last > next : last < next)){
                    continue sus;
                }
                last = next;
            }
            total++;
        }
        System.out.println(total);
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        int total = 0;
        while (sc.hasNextLine()){
            ArrayList<Integer> values = new ArrayList<>();
            Scanner sc2 = new Scanner(sc.nextLine());
            while (sc2.hasNextInt()){
                values.add(sc2.nextInt());
            }
            for (int i = 0; i < values.size(); i++) {
                int z = values.remove(i);
                if (isPossible(values)){
                    total++;
                    break;
                }
                values.add(i,z);
            }
        }
        System.out.println(total);
    }
    public static boolean isPossible(ArrayList<Integer> nums){
        boolean increasing;
        int n1 = nums.get(0);
        int n2 = nums.get(1);
        increasing = n2 > n1;
        if (n2-n1 == 0 || Math.abs(n2-n1)>3){
            return false;
        }
        int last = n2;
        for(int i = 2; i < nums.size(); i++){
            int next = nums.get(i);
            if (next-last==0 || Math.abs(next-last) > 3 || (increasing ? last > next : last < next)){
                return false;
            }
            last = next;
        }
        return true;
    }
}
