import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day18 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        boolean[][] corrupted = new boolean[71][71];
        for (int i = 0; i < 1024; i++) {
            String str = sc.nextLine();
            int n1 = Integer.parseInt(str.substring(0,str.indexOf(",")));
            int n2 = Integer.parseInt(str.substring(str.indexOf(',')+1));
            corrupted[n1][n2] = true;
        }
        boolean[][] checked = new boolean[71][71];
        ArrayList<int[]> toCheck = new ArrayList<>();
        toCheck.add(new int[]{0,0});
        checked[0][0] = true;
        while (!toCheck.isEmpty()){
            ArrayList<int[]> checkNext = new ArrayList<>();
            for (int[] ints : toCheck) {
                int x = ints[0];
                int y = ints[1];

                if (x == 70 && y == 70) {
                    System.out.println(total);
                    return;
                }
                for (int j = -1; j < 2; j += 2) {
                    if (x + j >= 0 && x + j <= 70) {
                        if (!(corrupted[x + j][y] || checked[x + j][y])) {
                            checkNext.add(new int[]{x + j, y});
                            checked[x + j][y] = true;
                        }
                    }
                    if (y + j >= 0 && y + j <= 70) {
                        if (!(corrupted[x][y + j] || checked[x][y + j])) {
                            checkNext.add(new int[]{x, y + j});
                            checked[x][y + j] = true;
                        }
                    }
                }
            }

            total++;
            toCheck = checkNext;
        }

    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        boolean[][] corrupted = new boolean[71][71];
        for (int i = 0; i < 1024; i++) {
            String str = sc.nextLine();
            int n1 = Integer.parseInt(str.substring(0,str.indexOf(",")));
            int n2 = Integer.parseInt(str.substring(str.indexOf(',')+1));
            corrupted[n1][n2] = true;
        }
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            int n1 = Integer.parseInt(str.substring(0,str.indexOf(",")));
            int n2 = Integer.parseInt(str.substring(str.indexOf(',')+1));
            corrupted[n1][n2] = true;
            boolean[][] checked = new boolean[71][71];
            ArrayList<int[]> toCheck = new ArrayList<>();
            toCheck.add(new int[]{0, 0});
            checked[0][0] = true;
            boolean doable = false;
            sus : while (!toCheck.isEmpty()) {
                ArrayList<int[]> checkNext = new ArrayList<>();
                for (int[] ints : toCheck) {
                    int x = ints[0];
                    int y = ints[1];

                    if (x == 70 && y == 70) {
                        doable = true;
                        break sus;
                    }
                    for (int j = -1; j < 2; j += 2) {
                        if (x + j >= 0 && x + j <= 70) {
                            if (!(corrupted[x + j][y] || checked[x + j][y])) {
                                checkNext.add(new int[]{x + j, y});
                                checked[x + j][y] = true;
                            }
                        }
                        if (y + j >= 0 && y + j <= 70) {
                            if (!(corrupted[x][y + j] || checked[x][y + j])) {
                                checkNext.add(new int[]{x, y + j});
                                checked[x][y + j] = true;
                            }
                        }
                    }
                }
                toCheck = checkNext;
            }
            if (!doable){
                System.out.println(n1+","+n2);
                return;
            }
        }
    }
}
