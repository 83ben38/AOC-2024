import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day23 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    static HashMap<String,ArrayList<String>> connections = new HashMap<>();
    static HashMap<String,Boolean> values = new HashMap<>();
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            String s1 = str.substring(0,2);
            String s2 = str.substring(3,5);
            if (!connections.containsKey(s1)){
                connections.put(s1,new ArrayList<>());
            }
            ArrayList<String> n = connections.get(s1);
            n.add(s2);
            if (!connections.containsKey(s2)){
                connections.put(s2,new ArrayList<>());
            }
            n = connections.get(s2);
            n.add(s1);
            values.put(s1+s2,true);
        }
        long minus = 0;
        long plus = 0;
        for (String st: connections.keySet()){
            if (st.startsWith("t")){
                ArrayList<String> st1 = connections.get(st);
                for (int i = 0; i < st1.size(); i++) {
                    for (int j = i+1; j < st1.size(); j++) {
                        if (values.getOrDefault(st1.get(i)+st1.get(j),false) || values.getOrDefault(st1.get(j)+st1.get(i),false)){
                            total++;
                            if (st1.get(i).startsWith("t")){
                                minus++;
                            }
                            if (st1.get(j).startsWith("t")){
                                minus++;
                            }
                            if (st1.get(i).startsWith("t") && st1.get(j).startsWith("t")){
                                plus++;
                            }
                        }
                    }
                }
            }

        }
        System.out.println(total - (minus/2) + (plus/3));
    }
    public static void part2() throws FileNotFoundException {
        ArrayList<String> largestGroup = new ArrayList<>();
        for (String st: connections.keySet()){
            ArrayList<String> st1 = connections.get(st);
            for (int i = 0; i < st1.size(); i++) {
                ArrayList<String> group = new ArrayList<>();
                group.add(st);
                group.add(st1.get(i));
                    for (int j = 0; j < st1.size(); j++) {
                        boolean addable = true;
                        for (int k = 0; k < group.size(); k++) {
                            if (!(values.getOrDefault(st1.get(j) + group.get(k), false) || values.getOrDefault(group.get(k)+st1.get(j) , false))) {
                                addable = false;
                                break;
                            }
                        }
                        if (addable){
                            group.add(st1.get(j));
                        }
                    }
                    if (group.size() > largestGroup.size()){
                        largestGroup = group;
                    }

            }

        }
        largestGroup.sort(String::compareTo);
        String result = "";
        for (int i = 0; i < largestGroup.size(); i++) {
            result += largestGroup.get(i)+",";
        }
        System.out.println(result);
    }

}
