import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day24Sorter {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        HashMap<String,String> availableCommands = new HashMap<>();
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            availableCommands.put(str.substring(str.length()-3),str);
        }
        FileWriter fw = new FileWriter("./src/solution.txt");
        for (int i = 0; i <= 45; i++) {
            ArrayList<String> order = new ArrayList<>();
            String st = "z"+i;
            if (i < 10){
                st = "z0"+i;
            }
            String str = availableCommands.get(st);
            availableCommands.put(st,"");
            order.addFirst(str);
            ArrayList<String> toCheck = new ArrayList<>();
            toCheck.add(str.substring(0,3));
            int secondIndexOfSpace = str.substring(4).indexOf(" ")+4;
            toCheck.add(str.substring(secondIndexOfSpace+1,secondIndexOfSpace+4));
            while (!toCheck.isEmpty()){
                st = toCheck.removeFirst();
                if (availableCommands.containsKey(st)) {
                    if (!availableCommands.get(st).isEmpty()) {
                        str = availableCommands.get(st);
                        availableCommands.put(st, "");
                        order.addFirst(str);
                        toCheck.add(str.substring(0, 3));
                        secondIndexOfSpace = str.substring(4).indexOf(" ") + 4;
                        toCheck.add(str.substring(secondIndexOfSpace + 1, secondIndexOfSpace + 4));
                    }
                }
            }
            for (int j = 0; j < order.size(); j++) {
                fw.write(order.get(j)+"\n");
            }
        }
        fw.flush();
        fw.close();
    }
}
