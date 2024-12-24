import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day24Part2 {
    public static void main(String[] args) throws FileNotFoundException{
        part2();
    }


    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        ArrayList<String> availableCommands = new ArrayList<>();
        ArrayList<String> availableToSwap = new ArrayList<>();
        int k = 0;
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            availableCommands.add(str);
            String str1 = str.substring(0,3);
            String operator = str.substring(4,7);
            int n = 0;

            if (operator.equals("OR ")){
                n = -1;
            }
            String str2 = str.substring(8+n,11+n);
            if (!(str1.startsWith("x")||str1.startsWith("y"))) {
                availableToSwap.add(str1);
                availableToSwap.add(str2);
            }
            if (str.charAt(str.length()-3)=='z'){
                availableToSwap.add(str.substring(str.length()-3));
                if (!checkIfWorks(availableCommands,k)){
                    for (int i = 0; i < availableToSwap.size(); i++) {
                        for (int j = i+1; j < availableToSwap.size(); j++) {
                            ArrayList<String> newAvailableCommands = new ArrayList<>(availableCommands);
                            for (int l = 0; l < newAvailableCommands.size(); l++) {
                                String command = availableCommands.get(l);
                                if (command.substring(command.length()-3).equals(availableToSwap.get(i))){
                                    newAvailableCommands.set(l,command.substring(0,command.length()-3) + availableToSwap.get(j));
                                }
                                else if (command.substring(command.length()-3).equals(availableToSwap.get(j))){
                                    newAvailableCommands.set(l,command.substring(0,command.length()-3) + availableToSwap.get(i));
                                }
                            }
                            if (checkIfWorks(newAvailableCommands,k)){
                                System.out.println("Swapped " + availableToSwap.get(i) + " and " + availableToSwap.get(j));
                                return;
                            }
                        }
                    }
                    System.out.println("Failed all swaps on " + k);
                    return;
                }
                k++;
                availableToSwap = new ArrayList<>();
            }
        }
    }
    public static boolean checkIfWorks(ArrayList<String> availableCommands, int k){
        for (int j = 0; j < 100; j++) {
            HashMap<String,Boolean> h = new HashMap<>();
            long sum = 0;
            for (int i = 0; i <= k; i++) {
                String s = i + "";
                if (i < 10){
                    s = "0"+i ;
                }
                boolean b1 = Math.random()>0.5;
                boolean b2 = Math.random()>0.5;
                h.put("x"+s,b1);
                h.put("y"+s,b2);
                if (b1){
                    sum+= (long) Math.pow(2,i);
                }
                if (b2){
                    sum+= (long) Math.pow(2,i);
                }
            }
            runCommands(new ArrayList<>(availableCommands),h);
            if (calculateValue(h)!=sum%Math.pow(2,(k+1))){
                return false;
            }
        }
        return true;
    }
    public static long calculateValue(HashMap<String,Boolean> values){
        long total = 0;
        for (int i = 99; i >= 0; i--) {
            if (i < 10){
                if (values.containsKey("z0" + i)){
                    total*=2;
                    total+=values.get("z0"+i) ? 1 : 0;
                }
            }
            else if (values.containsKey("z" + i)){
                total*=2;
                total+=values.get("z"+i) ? 1 : 0;
            }
        }
        return total;
    }
    public static void runCommands(ArrayList<String> availableCommands,HashMap<String,Boolean> values){
        int lastAvailableCommands = availableCommands.size();
        while (!availableCommands.isEmpty()){
            for (int i = 0; i < availableCommands.size(); i++) {
                String str = availableCommands.get(i);
                String str1 = str.substring(0,3);
                String operator = str.substring(4,7);
                int n = 0;

                if (operator.equals("OR ")){
                    n = -1;
                }
                String str2 = str.substring(8+n,11+n);
                String str3 = str.substring(15+n,18+n);
                if (values.containsKey(str1)&&values.containsKey(str2)){
                    boolean b1 = values.get(str1);
                    boolean b2 = values.get(str2);
                    values.put(str3,switch (operator){
                        case "AND" -> b1&&b2;
                        case "XOR" -> b1^b2;
                        default -> b1||b2;
                    });
                    availableCommands.remove(i);
                    i--;
                }

            }
            if (lastAvailableCommands == availableCommands.size()){
                return;
            }
            lastAvailableCommands = availableCommands.size();
        }
    }
}
