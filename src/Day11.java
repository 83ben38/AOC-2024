import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        HashMap<Long,Long> oldStones = new HashMap<>();
        while (sc.hasNextInt()){
           oldStones.put(sc.nextLong(),1l);
        }
        for (int i = 0; i < 25; i++) {
            HashMap<Long,Long> newStones = new HashMap<>();
            for (Long t: oldStones.keySet()){
                if (t == 0){
                    newStones.put(1l,newStones.getOrDefault(1,0l)+oldStones.get(t));
                }
                else if ((t+"").length()%2==0){
                    String s = t +"";
                    long n1 = Integer.parseInt(s.substring(0,s.length()/2));
                    long n2 = Integer.parseInt(s.substring(s.length()/2));
                    newStones.put(n1,newStones.getOrDefault(n1,0l)+oldStones.get(t));
                    newStones.put(n2,newStones.getOrDefault(n2,0l)+oldStones.get(t));
                }
                else{
                    newStones.put(t*2024,newStones.getOrDefault(t*2024,0l)+oldStones.get(t));
                }
            }
            oldStones = newStones;
        }


        for (Long t: oldStones.keySet()){
            total+=oldStones.get(t);
        }
        System.out.println(total);
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        HashMap<Long,Long> oldStones = new HashMap<>();
        while (sc.hasNextInt()){
            oldStones.put(sc.nextLong(),1l);
        }
        for (int i = 0; i < 75; i++) {
            HashMap<Long,Long> newStones = new HashMap<>();
            for (Long t: oldStones.keySet()){
                if (t == 0){
                    newStones.put(1l,newStones.getOrDefault(1,0l)+oldStones.get(t));
                }
                else if ((t+"").length()%2==0){
                    String s = t +"";
                    long n1 = Integer.parseInt(s.substring(0,s.length()/2));
                    long n2 = Integer.parseInt(s.substring(s.length()/2));
                    newStones.put(n1,newStones.getOrDefault(n1,0l)+oldStones.get(t));
                    newStones.put(n2,newStones.getOrDefault(n2,0l)+oldStones.get(t));
                }
                else{
                    newStones.put(t*2024,newStones.getOrDefault(t*2024,0l)+oldStones.get(t));
                }
            }
            oldStones = newStones;
        }


        for (Long t: oldStones.keySet()){
            total+=oldStones.get(t);
        }
        System.out.println(total);
    }
}
