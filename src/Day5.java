import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        int total = 0;
        HashMap<Integer,ArrayList<Integer>> notAllowed = new HashMap<>();
        while(sc.hasNextLine()){
            String next = sc.nextLine();
            if (!next.contains("|")){
                break;
            }
            ArrayList<Integer> toAdd = new ArrayList<>();
            if (notAllowed.containsKey(Integer.parseInt(next.substring(0,2)))){
                toAdd = notAllowed.get(Integer.parseInt(next.substring(0,2)));
            }
            else{
                notAllowed.put(Integer.parseInt(next.substring(0,2)),toAdd);
            }
            toAdd.add(Integer.parseInt(next.substring(3)));
        }
        while (sc.hasNextLine()){
            String next = sc.nextLine();
            ArrayList<Integer> previouslyAdded = new ArrayList<>();
            boolean works = true;
            int valueToAdd = 0;
            for (int i = 0; i < (next.length()+1)/3; i++) {
                int nextInt = Integer.parseInt(next.substring(i*3,(i*3)+2));
                ArrayList<Integer> bad = notAllowed.getOrDefault(nextInt,new ArrayList<>());
                for (int j = 0; j < previouslyAdded.size(); j++) {
                    if (bad.contains(previouslyAdded.get(j))){
                        works = false;
                        break;
                    }
                }
                if (i == (next.length()+1)/6){
                    valueToAdd = nextInt;
                }
                if (!works){
                    break;
                }
                previouslyAdded.add(nextInt);
            }
            if (works){
                total+=valueToAdd;
            }
        }
        System.out.println(total);
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        int total = 0;
        HashMap<Integer,ArrayList<Integer>> notAllowed = new HashMap<>();
        while(sc.hasNextLine()){
            String next = sc.nextLine();
            if (!next.contains("|")){
                break;
            }
            ArrayList<Integer> toAdd = new ArrayList<>();
            if (notAllowed.containsKey(Integer.parseInt(next.substring(0,2)))){
                toAdd = notAllowed.get(Integer.parseInt(next.substring(0,2)));
            }
            else{
                notAllowed.put(Integer.parseInt(next.substring(0,2)),toAdd);
            }
            toAdd.add(Integer.parseInt(next.substring(3)));
        }
        while (sc.hasNextLine()){
            String next = sc.nextLine();
            ArrayList<Integer> previouslyAdded = new ArrayList<>();
            boolean works = true;
            for (int i = 0; i < (next.length()+1)/3; i++) {
                int nextInt = Integer.parseInt(next.substring(i*3,(i*3)+2));
                ArrayList<Integer> bad = notAllowed.getOrDefault(nextInt,new ArrayList<>());
                for (int j = 0; j < previouslyAdded.size(); j++) {
                    if (bad.contains(previouslyAdded.get(j))){
                        works = false;
                        break;
                    }
                }
                previouslyAdded.add(nextInt);
            }
            if (!works){
                int toAdd = 0;
                int k = (previouslyAdded.size()+1)/2;
                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < previouslyAdded.size(); j++) {
                        ArrayList<Integer> bad = notAllowed.getOrDefault(previouslyAdded.get(j),new ArrayList<>());
                        boolean good = true;
                        for (int l = 0; l < bad.size(); l++) {
                            if (previouslyAdded.contains(bad.get(l))){
                                good = false;
                                break;
                            }
                        }
                        if (good){
                            toAdd = previouslyAdded.get(j);
                            previouslyAdded.remove(j);
                            break;
                        }
                    }

                }
                total+=toAdd;
            }
        }
        System.out.println(total);
    }
}
