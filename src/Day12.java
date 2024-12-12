import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        while (sc.hasNextLine()){
            map.add(new ArrayList<>());
            String str=  sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                map.getLast().add(str.charAt(i));
            }
        }
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j)!='0'){
                    long perimeter = getAreaPerimeter(map,i,j);
                    long area = 0;
                    for (int k = 0; k < map.size(); k++) {
                        for (int l = 0; l < map.get(k).size(); l++) {
                            if (map.get(k).get(l) == '1'){
                                area++;
                                map.get(k).set(l,'0');
                            }
                        }
                    }
                    total+=perimeter*area;
                }
            }
        }
        System.out.println(total);
    }
    public static long getAreaPerimeter(ArrayList<ArrayList<Character>> map, int x, int y){
        char c = map.get(x).get(y);
        long total = 0;
        map.get(x).set(y,'1');
        for (int i = -1; i < 2; i+=2) {
            if (x+i<0||x+i==map.size()){
                total++;
            }
            else{
                if (map.get(x+i).get(y) == '1'){

                }
                else if (map.get(x+i).get(y) == c){
                    total+=getAreaPerimeter(map,x+i,y);
                }
                else{
                    total++;
                }
            }
            if (y+i<0||y+i==map.getFirst().size()){
                total++;
            }
            else{
                if (map.get(x).get(y+i) == '1'){

                }
                else if (map.get(x).get(y+i) == c){
                    total+=getAreaPerimeter(map,x,y+i);
                }
                else{
                    total++;
                }
            }
        }
        return total;
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        while (sc.hasNextLine()){
            map.add(new ArrayList<>());
            String str=  sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                map.getLast().add(str.charAt(i));
            }
        }
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j)!='0'){
                    long perimeter = getAreaSides(map,i,j);
                    long area = 0;
                    for (int k = 0; k < map.size(); k++) {
                        for (int l = 0; l < map.get(k).size(); l++) {
                            if (map.get(k).get(l) == '1'){
                                area++;
                                map.get(k).set(l,'0');
                            }
                        }
                    }
                    total+=perimeter*area;
                }
            }
        }
        System.out.println(total);
    }
    public static long getAreaSides(ArrayList<ArrayList<Character>> map, int x, int y){
        char c = map.get(x).get(y);
        long total = 0;
        map.get(x).set(y,'1');
        ArrayList<int[]> queue = new ArrayList<>();
        for (int i = -1; i < 2; i+=2) {
            if (x+i<0||x+i==map.size()){
                total++;
            }
            else{
                if (map.get(x+i).get(y) == '1'){
                    for (int j = -1; j < 2; j+=2) {
                        if (y+j<0||y+j==map.getFirst().size()){
                            total--;
                        }
                        else {
                            char c1 = map.get(x+i).get(y+j);
                            char c2 = map.get(x).get(y+j);
                            if (c1 != '1' && c1 != c && c2 != '1' && c2 != c){
                                total--;
                            }
                        }
                    }
                }
                else if (map.get(x+i).get(y) == c){
                    queue.add(new int[]{x + i, y});
                }
                else{
                    total++;
                }
            }
            if (y+i<0||y+i==map.getFirst().size()){
                total++;
            }
            else{
                if (map.get(x).get(y+i) == '1'){
                    for (int j = -1; j < 2; j+=2) {
                        if (x+j<0||x+j==map.size()){
                            total--;
                        }
                        else {
                            char c1 = map.get(x+j).get(y+i);
                            char c2 = map.get(x+j).get(y);
                            if (c1 != '1' && c1 != c && c2 != '1' && c2 != c){
                                total--;
                            }
                        }
                    }
                }
                else if (map.get(x).get(y+i) == c){
                    queue.add(new int[]{x, y+i});
                }
                else{
                    total++;
                }
            }
        }
        for (int i = 0; i < queue.size(); i++) {
            if (map.get(queue.get(i)[0]).get(queue.get(i)[1])!='1') {
                total += getAreaSides(map, queue.get(i)[0], queue.get(i)[1]);
            }
        }
        return total;
    }
}
