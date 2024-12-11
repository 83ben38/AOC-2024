import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        ArrayList<ArrayList<Integer>> values = new ArrayList<>();
        ArrayList<ArrayList<HashMap<Integer,Boolean>>> oldTotals = new ArrayList<>();
        int z = 0;
        while (sc.hasNextLine()){
            values.add(new ArrayList<>());
            oldTotals.add(new ArrayList<>());
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                values.getLast().add(str.charAt(i) - '0');
                oldTotals.getLast().add(new HashMap<>());
                if (str.charAt(i) == '0'){
                    oldTotals.getLast().getLast().put(z,true);
                    z++;
                }
            }
        }
        for (int i = 1; i <= 9; i++) {
            ArrayList<ArrayList<HashMap<Integer,Boolean>>> newTotals = new ArrayList<>(oldTotals.size());
            for (int j = 0; j < oldTotals.size(); j++) {
                newTotals.add(new ArrayList<>());
                for (int k = 0; k < oldTotals.get(j).size(); k++) {
                    HashMap<Integer,Boolean> newValues = new HashMap<>();
                    if (values.get(j).get(k)==i) {
                        for (int l = -1; l < 2; l += 2) {
                            if (j + l >= 0 && j + l < oldTotals.size()) {
                                for(Integer t: oldTotals.get(j+l).get(k).keySet()){
                                    newValues.put(t,true);
                                }
                            }
                            if (k + l >= 0 && k + l < oldTotals.getLast().size()) {
                                for(Integer t: oldTotals.get(j).get(k+l).keySet()){
                                    newValues.put(t,true);
                                }
                            }
                        }
                    }

                    newTotals.getLast().add(newValues);
                }
            }
            oldTotals = newTotals;
        }
        for (int i = 0; i < oldTotals.size(); i++) {
            for (int j = 0; j < oldTotals.get(i).size(); j++) {
                total+=oldTotals.get(i).get(j).size();
            }
        }
        System.out.println(total);
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        ArrayList<ArrayList<Integer>> values = new ArrayList<>();
        ArrayList<ArrayList<Integer>> oldTotals = new ArrayList<>();
        while (sc.hasNextLine()){
            values.add(new ArrayList<>());
            oldTotals.add(new ArrayList<>());
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                values.getLast().add(str.charAt(i) - '0');
                if (str.charAt(i) == '0'){
                    oldTotals.getLast().add(1);
                }
                else{
                    oldTotals.getLast().add(0);
                }
            }
        }
        for (int i = 1; i <= 9; i++) {
            ArrayList<ArrayList<Integer>> newTotals = new ArrayList<>(oldTotals.size());
            for (int j = 0; j < oldTotals.size(); j++) {
                newTotals.add(new ArrayList<>());
                for (int k = 0; k < oldTotals.get(j).size(); k++) {
                    int sum = 0;
                    if (values.get(j).get(k)==i) {
                        for (int l = -1; l < 2; l += 2) {
                            if (j + l >= 0 && j + l < oldTotals.size()) {
                                sum+= oldTotals.get(j+l).get(k);
                            }
                            if (k + l >= 0 && k + l < oldTotals.getLast().size()) {
                                sum+= oldTotals.get(j).get(k+l);
                            }
                        }
                    }
                    newTotals.getLast().add(sum);
                }
            }
            oldTotals = newTotals;
        }
        for (int i = 0; i < oldTotals.size(); i++) {
            for (int j = 0; j < oldTotals.get(i).size(); j++) {
                total+=oldTotals.get(i).get(j);
            }
        }
        System.out.println(total);
    }
}
