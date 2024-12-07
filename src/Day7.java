import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        while (sc.hasNextLine()){
            Scanner sc2 = new Scanner(sc.nextLine());
            String str = sc2.next();
            long value = Long.parseLong(str.substring(0,str.length()-1));
            ArrayList<Integer> values = new ArrayList<>();
            while (sc2.hasNextInt()){
                values.add(sc2.nextInt());
            }
            if (isPossible1(values,value,values.size()-1)){
                total+=value;
            }
        }
        System.out.println(total);
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        while (sc.hasNextLine()){
            Scanner sc2 = new Scanner(sc.nextLine());
            String str = sc2.next();
            long value = Long.parseLong(str.substring(0,str.length()-1));
            ArrayList<Integer> values = new ArrayList<>();
            while (sc2.hasNextInt()){
                values.add(sc2.nextInt());
            }
            if (isPossible2(values,value,values.size()-1)){
                total+=value;
            }
        }
        System.out.println(total);
    }
    public static boolean isPossible2(ArrayList<Integer> values, long value, int endingValue){
        if (endingValue == 0){
            return values.getFirst()==value;
        }
        int numDigits = numDigits(values.get(endingValue));
        if (value%Math.pow(10,numDigits)==values.get(endingValue)){
            if (isPossible2(values, (long) (value/Math.pow(10,numDigits)),endingValue-1)){
                return true;
            }
        }
        if (value % values.get(endingValue) == 0){
            if (isPossible2(values,value/values.get(endingValue),endingValue-1)){
                return true;
            }
        }
        return isPossible2(values,value-values.get(endingValue),endingValue-1);
    }
    public static boolean isPossible1(ArrayList<Integer> values, long value, int endingValue){
        if (endingValue == 0){
            return values.getFirst()==value;
        }
        if (value % values.get(endingValue) == 0){
            if (isPossible1(values,value/values.get(endingValue),endingValue-1)){
                return true;
            }
        }
        return isPossible1(values,value-values.get(endingValue),endingValue-1);
    }
    public static int numDigits(long value){
        if (value < 10){
            return 1;
        }
        else{
            return numDigits(value/10)+1;
        }
    }
}
