import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day24Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
    }
    static HashMap<String,Boolean> values = new HashMap<>();
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            if (str.isEmpty()){
                break;
            }
            values.put(str.substring(0,3),str.charAt(5)=='1');
        }
        ArrayList<String> availableCommands = new ArrayList<>();
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            availableCommands.add(str);
        }
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

        }
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
        System.out.println(total);
    }
}
