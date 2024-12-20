import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;

public class Day19 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    static BitSet possible = new BitSet(400000);
    static int maxLength = 0;
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;

        String str = sc.nextLine();
        while (str.contains(",")){
            possible.set( (int) calculateValue(str.substring(0,str.indexOf(","))));
            if (str.indexOf(",") > maxLength){
                maxLength = str.indexOf(",");
            }
            str = str.substring(str.indexOf(",")+2);
        }
        possible.set((int) calculateValue(str));
        sc.nextLine();
        while (sc.hasNextLine()){
            String v = sc.nextLine();
            memo = new HashMap<>();
            if (isPossible(v.toCharArray(),-1)){
                total++;
            }
        }
        System.out.println(total);
    }
    public static long calculateValue(String str){
        if (str.length() == 0){
            return 0;
        }
        if (str.length() == 1){
            return switch (str.charAt(0)){
                case 'w' -> 1;
                case 'u' -> 2;
                case 'b' -> 3;
                case 'r' -> 4;
                case 'g' -> 5;
                default -> -1;
            };
        }
        return calculateValue(str.substring(0,str.length()-1))*6 + switch (str.charAt(str.length()-1)){
            case 'w' -> 1;
            case 'u' -> 2;
            case 'b' -> 3;
            case 'r' -> 4;
            case 'g' -> 5;
            default -> -1;
        };
    }
    private static HashMap<Integer, Boolean> memo = new HashMap<>();
    public static boolean isPossible(char[] value, int pointer){
        if (pointer == value.length-1){
            return true;
        }
        if (memo.containsKey(pointer)){
            return false;
        }
        memo.put(pointer,false);
        int required = 0;
        for (int i = 1; i <= maxLength; i++) {
            if (pointer+i==value.length){

                return false;
            }
            required*=6;
            required+= switch (value[pointer+i]){
                case 'w' -> 1;
                case 'u' -> 2;
                case 'b' -> 3;
                case 'r' -> 4;
                case 'g' -> 5;
                default -> -1;
            };
            if (possible.get(required)){
                if (isPossible(value,pointer+i)){
                    return true;
                }
            }
        }
        return false;
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;

        sc.nextLine();
        sc.nextLine();
        while (sc.hasNextLine()){
            String v = sc.nextLine();
            memo2 = new HashMap<>();
            total+=isPossible2(v.toCharArray(),-1);
        }
        System.out.println(total);
    }
    private static HashMap<Integer,Long> memo2;
    public static long isPossible2(char[] value, int pointer){
        if (pointer == value.length-1){
            return 1;
        }
        if (memo2.containsKey(pointer)){
            return memo2.get(pointer);
        }
        int required = 0;
        long total = 0;
        for (int i = 1; i <= maxLength; i++) {
            if (pointer+i==value.length){
                memo2.put(pointer,total);
                return total;
            }
            required*=6;
            required+= switch (value[pointer+i]){
                case 'w' -> 1;
                case 'u' -> 2;
                case 'b' -> 3;
                case 'r' -> 4;
                case 'g' -> 5;
                default -> -1;
            };
            if (possible.get(required)){
                total+=isPossible2(value,pointer+i);
            }
        }
        memo2.put(pointer,total);
        return total;
    }
}
