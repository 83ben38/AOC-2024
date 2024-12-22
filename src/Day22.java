import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day22 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        while (sc.hasNextLine()) {
            long n = sc.nextLong();
            for (int i = 0; i < 2000; i++) {
                n = mix(n, n * 64);
                n = prune(n);
                n = mix(n, n / 32);
                n = prune(n);
                n = mix(n, n * 2048);
                n = prune(n);
            }
            total += n;
        }
        System.out.println(total);
    }
    public static long mix(long n, long m){
        return n^m;
    }
    public static long prune(long n){
        return n % 16777216;
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        HashMap<Integer,Long> possibleSequences = new HashMap<>();
        while (sc.hasNextLine()) {
            long n = sc.nextLong();
            HashMap<Integer,Boolean> done = new HashMap<>();
            int k = 0;
            for (int i = 0; i < 2000; i++) {
                int z = (int)n%10;
                n = mix(n, n * 64);
                n = prune(n);
                n = mix(n, n / 32);
                n = prune(n);
                n = mix(n, n * 2048);
                n = prune(n);
                k*=20;
                k += ((int)(n%10)-z)+9;
                if (i>2){
                    if (done.containsKey(k)){

                    }
                    else{
                        done.put(k,true);
                        possibleSequences.put(k,possibleSequences.getOrDefault(k,0L)+(n%10));
                    }
                }
                k%=8000;
            }
        }
        for (Long t: possibleSequences.values()){
            if (t > total){
                total = t;
            }
        }
        System.out.println(total);
    }
}
