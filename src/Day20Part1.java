import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day20Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        boolean[][] walls = new boolean[0][];
        int k = 0;
        int[] start = new int[0];
        int[] end = new int[0];
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            if (walls.length==0){
                walls = new boolean[str.length()][141];
            }
            for (int i = 0; i < str.length(); i++) {
                walls[i][k] = str.charAt(i)=='#';
                if (str.charAt(i)=='S'){
                    start = new int[]{i, k};
                }
                if (str.charAt(i)=='E'){
                    end = new int[]{i, k};
                }
            }
            k++;
        }
        int[][] distance = new int[walls.length][walls[0].length];
        runValues(1,start,distance,end,walls);
        checkValues(new int[][][]{distance},distance,2);
    }
    public static void checkValues(int[][][] past, int[][] distance, int toGo){
        if (toGo == 0){
            long total = 0;
            for (int i = 0; i < past.length; i++) {
                for (int j = 0; j < past[i].length; j++) {
                    for (int k = 0; k < past[i][j].length; k++) {
                        if (distance[j][k]-past[i][j][k] >= 100 && past[i][j][k] != 0 && distance[j][k]!=0){
                            System.out.println(distance[j][k]-past[i][j][k]);
                            total++;
                        }
                    }
                }
            }
            System.out.println(total);
            return;
        }
        int[][][] future = new int[past.length*4][past[0].length][past[0][0].length];

        for (int j = 0; j < past.length; j++) {
            for (int k = 0; k < past[j].length; k++) {
                for (int l = 0; l < past[j][k].length; l++) {
                    if (past[j][k][l] != 0) {
                        if (k + 1 < past[j].length) {
                            future[j][k + 1][l] = past[j][k][l] + 1;
                        }
                        if (k - 1 >= 0) {
                            future[j + (past.length)][k - 1][l] = past[j][k][l] + 1;
                        }
                        if (l - 1 >= 0) {
                            future[j + (past.length * 2)][k][l - 1] = past[j][k][l] + 1;
                        }
                        if (l + 1 < past[j][k].length) {
                            future[j + (past.length * 3)][k][l + 1] = past[j][k][l] + 1;
                        }
                    }
                }
            }
        }

        checkValues(future,distance,toGo-1);
    }
    public static void runValues(int current, int[] currentPos, int[][] distance, int[] endingPos, boolean[][] walls){
        distance[currentPos[0]][currentPos[1]] = current;
        if (currentPos[0] == endingPos[0] && currentPos[1] == endingPos[1]){
            return;
        }
        for (int i = -1; i < 2; i+=2) {
            if (i+currentPos[0] >= 0 && i+currentPos[0] < walls.length){
                if (!walls[i+currentPos[0]][currentPos[1]]) {
                    if (distance[i+currentPos[0]][currentPos[1]]==0){
                        int[] newPos = new int[]{i+currentPos[0],currentPos[1]};
                        runValues(current+1, newPos, distance, endingPos, walls);
                    }
                }
            }
            if (i+currentPos[1] >= 0 && i+currentPos[1] < walls[0].length){
                if (!walls[currentPos[0]][i+currentPos[1]]) {
                    if (distance[currentPos[0]][i+currentPos[1]]==0){
                        int[] newPos = new int[]{currentPos[0],i+currentPos[1]};
                        runValues(current+1, newPos, distance, endingPos, walls);
                    }
                }
            }
        }
    }
}
