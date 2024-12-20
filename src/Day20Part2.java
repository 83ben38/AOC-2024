import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day20Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }
    public static void part2() throws FileNotFoundException {
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
        int[][] distances = new int[distance[end[0]][end[1]]][2];
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length;j++) {
                if (distance[i][j]!=0){
                    distances[distance[i][j]-1] = new int[]{i,j};
                }
            }
        }
        int total = 0;
        for (int i = 0; i < distances.length; i++) {
            for (int j = i+100; j < distances.length; j++) {
                int dist = Math.abs(distances[j][0]-distances[i][0]) + Math.abs(distances[j][1]-distances[i][1]);
                if (dist <= 20 && j-(dist+i) >= 100){
                    total++;
                }
            }
        }
        System.out.println(total);
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
