import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day21Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        setup();
        part2();
    }
    static HashMap<Character,int[]> pos = new HashMap<>();
    static HashMap<Character,int[]> pos0 = new HashMap<>();
    public static void setup(){
        pos.put('<', new int[]{1, 0});
        pos.put('^', new int[]{0, 1});
        pos.put('v', new int[]{1, 1});
        pos.put('>', new int[]{1, 2});
        pos.put('A', new int[]{0, 2});
        pos0.put('7',new int[]{0,0});
        pos0.put('8',new int[]{0,1});
        pos0.put('9',new int[]{0,2});
        pos0.put('4',new int[]{1,0});
        pos0.put('5',new int[]{1,1});
        pos0.put('6',new int[]{1,2});
        pos0.put('1',new int[]{2,0});
        pos0.put('2',new int[]{2,1});
        pos0.put('3',new int[]{2,2});
        pos0.put('0',new int[]{3,1});
        pos0.put('A',new int[]{3,2});
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        while (sc.hasNextLine()){
            String desired = sc.nextLine();
            long  k =lengthOfInstructions2(desired);
            System.out.println(k);
            total+=k * (Integer.parseInt(desired.substring(0,desired.length()-1)));
        }
        System.out.println(total);
    }
    static int numRecures = 25;
    public static long lengthOfInstructions2(String a){
        a = "A" + a;
        long total = 0;
        for (int i = 1; i < a.length(); i++) {
            int[] pos1 = pos0.get(a.charAt(i-1));
            int[] pos2 = pos0.get(a.charAt(i));
            long minLength = Long.MAX_VALUE;
            if (!(pos1[0]==3&&pos2[1]==0)){
                StringBuilder k = new StringBuilder();
                if (pos1[1] > pos2[1]){
                    k.append("<".repeat(pos1[1] - pos2[1]));
                }
                else{
                    k.append(">".repeat(pos2[1] - pos1[1]));
                }
                if (pos1[0] > pos2[0]){
                    k.append("^".repeat(pos1[0] - pos2[0]));
                }
                else{
                    k.append("v".repeat(pos2[0] - pos1[0]));
                }
                k.append("A");
                long value = lengthOfInstructions(k.toString(),numRecures);
                if (minLength > value){
                    minLength = value;
                }
            }
            if (!(pos2[0]==3&&pos1[1]==0)){
                StringBuilder k = new StringBuilder();
                if (pos1[0] > pos2[0]){
                    k.append("^".repeat(pos1[0] - pos2[0]));
                }
                else{
                    k.append("v".repeat(pos2[0] - pos1[0]));
                }
                if (pos1[1] > pos2[1]){
                    k.append("<".repeat(pos1[1] - pos2[1]));
                }
                else{
                    k.append(">".repeat(pos2[1] - pos1[1]));
                }
                k.append("A");
                long value = lengthOfInstructions(k.toString(),numRecures);
                if (minLength > value){
                    minLength = value;
                }
            }




            total+=minLength;
        }
        return total;
    }
    static HashMap<String,HashMap<Integer,Long>> done = new HashMap<>();
    public static long lengthOfInstructions(String a, int numRecures){
        if (done.containsKey(a)){
            if (done.get(a).containsKey(numRecures)){
                return done.get(a).get(numRecures);
            }
        }
        if (numRecures == 0){
            return a.length();
        }
        a = "A" + a;
        long total = 0;
        for (int i = 1; i < a.length(); i++) {
            int[] pos1 = pos.get(a.charAt(i-1));
            int[] pos2 = pos.get(a.charAt(i));
            long minLength = Long.MAX_VALUE;
            if (!(pos1[0]==0&&pos2[1]==0)){
                StringBuilder k = new StringBuilder();
                if (pos1[1] > pos2[1]){
                    k.append("<".repeat(pos1[1] - pos2[1]));
                }
                else{
                    k.append(">".repeat(pos2[1] - pos1[1]));
                }
                if (pos1[0] > pos2[0]){
                    k.append("^".repeat(pos1[0] - pos2[0]));
                }
                else{
                    k.append("v".repeat(pos2[0] - pos1[0]));
                }
                k.append("A");
                long value = lengthOfInstructions(k.toString(),numRecures-1);
                if (minLength > value){
                    minLength = value;
                }
            }
            if (!(pos2[0]==0&&pos1[1]==0)){
                StringBuilder k = new StringBuilder();
                if (pos1[0] > pos2[0]){
                    k.append("^".repeat(pos1[0] - pos2[0]));
                }
                else{
                    k.append("v".repeat(pos2[0] - pos1[0]));
                }
                if (pos1[1] > pos2[1]){
                    k.append("<".repeat(pos1[1] - pos2[1]));
                }
                else{
                    k.append(">".repeat(pos2[1] - pos1[1]));
                }
                k.append("A");
                long value = lengthOfInstructions(k.toString(),numRecures-1);
                if (minLength > value){
                    minLength = value;
                }
            }




            total+=minLength;
        }
        done.put(a.substring(1),done.getOrDefault(a.substring(1),new HashMap<>()));
        done.get(a.substring(1)).put(numRecures,total);
        return total;
    }
}
