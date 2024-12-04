import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        ArrayList<ArrayList<Character>> chars = new ArrayList<>();
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            chars.add(new ArrayList<>());
            for (int i = 0; i < str.length(); i++) {
                chars.getLast().add(str.charAt(i));
            }
        }
        int total = 0;
        for (int i = 1; i < chars.size()-1; i++) {
            for (int j = 1; j < chars.get(i).size()-1; j++) {
                if (chars.get(i).get(j) == 'A'){
                    char[] chars1 = new char[]{chars.get(i-1).get(j-1),chars.get(i+1).get(j-1),chars.get(i+1).get(j+1),chars.get(i-1).get(j+1)};
                    int countM = 0;
                    for (int k = 0; k < chars1.length; k++) {
                        if (chars1[k] == 'M'){
                            countM++;
                        }
                    }
                    int countS = 0;
                    for (int k = 0; k < chars1.length; k++) {
                        if (chars1[k] == 'S'){
                            countS++;
                        }
                    }
                    if (countM == 2 && countS == 2 && chars1[0]!=chars1[2]){
                        total++;
                    }
                }
            }
        }
        System.out.println(total);
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        ArrayList<ArrayList<Character>> chars = new ArrayList<>();
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            chars.add(new ArrayList<>());
            for (int i = 0; i < str.length(); i++) {
                chars.getLast().add(str.charAt(i));
            }
        }
        int total = 0;
        char[] chars2 = new char[]{'X','M','A','S'};
        for (int i = 0; i < chars.size(); i++) {
            for (int j = 0; j < chars.getFirst().size(); j++) {
                if (chars.get(i).get(j) == 'X'){
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            boolean p1 = true;
                            for (int m = 1; m < 4; m++) {
                                boolean b1 = i + (k * m) < 0 || i + (k * m) >= chars.size();
                                boolean b2 = j + (l * m) < 0 || j + (l * m) >= chars.getFirst().size();

                                if (!(b1 || b2)) {
                                    if (chars.get(i + (k * m)).get(j + (l * m)) != chars2[m]) {
                                        p1 = false;
                                    }
                                } else {
                                    p1 = false;
                                }
                            }
                            if (p1) {
                                total++;
                            }
                        }

                    }
                }
            }
        }
        System.out.println(total);
    }
}
