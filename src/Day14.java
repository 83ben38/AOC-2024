import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) throws Exception {
        part1();
        part2();
    }
    static final int xDim = 101;
    static final int yDim = 103;
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        ArrayList<int[]> robots = new ArrayList<>();
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            int[] z = new int[4];
            z[0] = Integer.parseInt(str.substring(str.indexOf("=")+1,str.indexOf(",")));
            z[1] = Integer.parseInt(str.substring(str.indexOf(",")+1,str.indexOf(" ")));
            z[2] = Integer.parseInt(str.substring(str.lastIndexOf("=")+1,str.lastIndexOf(",")));
            z[3] = Integer.parseInt(str.substring(str.lastIndexOf(",")+1));
            robots.add(z);
        }

        for (int j = 0; j < robots.size(); j++) {
            int[] z = robots.get(j);
            z[0]+=z[2]*100;
            z[1]+=z[3]*100;
            while (z[0] < 0){
                z[0]+=xDim;
            }
            while (z[0]>=xDim){
                z[0]-=xDim;
            }
            while (z[1]<0){
                z[1]+=yDim;
            }
            while (z[1]>=yDim){
                z[1]-=yDim;
            }
        }

        int[] total = new int[4];
        for (int i = 0; i < robots.size(); i++) {
            int[] z = robots.get(i);
            int v = 0;
            if (z[0] < xDim/2){
                v++;
            }
            else if (!(z[0] > xDim/2)){
                continue;
            }
            if (z[1] < yDim/2){
                v+=2;
            }
            else if (!(z[1] > yDim/2)){
                continue;
            }
            total[v]++;

        }
        System.out.println((long)total[0]*total[1]*total[2]*total[3]);
    }
    public static void part2() throws Exception {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        ArrayList<int[]> robots = new ArrayList<>();
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            int[] z = new int[4];
            z[0] = Integer.parseInt(str.substring(str.indexOf("=")+1,str.indexOf(",")));
            z[1] = Integer.parseInt(str.substring(str.indexOf(",")+1,str.indexOf(" ")));
            z[2] = Integer.parseInt(str.substring(str.lastIndexOf("=")+1,str.lastIndexOf(",")));
            z[3] = Integer.parseInt(str.substring(str.lastIndexOf(",")+1));
            robots.add(z);
        }
        File f = new File("./src/solution.txt");
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < 10403; i++) {
            fw.write(i  + " seconds: " + toString(robots)+"\n");
            for (int j = 0; j < robots.size(); j++) {
                int[] z = robots.get(j);
                z[0] += z[2];
                z[1] += z[3];
                while (z[0] < 0) {
                    z[0] += xDim;
                }
                while (z[0] >= xDim) {
                    z[0] -= xDim;
                }
                while (z[1] < 0) {
                    z[1] += yDim;
                }
                while (z[1] >= yDim) {
                    z[1] -= yDim;
                }
            }
        }
        fw.flush();
        fw.close();
    }
    public static String toString(ArrayList<int[]> robots){
        boolean[][] values = new boolean[xDim][yDim];
        for (int i = 0; i < robots.size(); i++) {
            if (values[robots.get(i)[0]][robots.get(i)[1]]){
                return "\n----------";
            }
            else{
                values[robots.get(i)[0]][robots.get(i)[1]] = true;
            }
        }
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            strb.append("\n");
            for (int j = 0; j < values[i].length; j++) {
                strb.append(values[i][j]?".":"#");
            }
        }
        return strb.toString();
    }
}
