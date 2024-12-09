import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        HashMap<Character,ArrayList<Integer>> antennaLocations = new HashMap<>();
        HashMap<Integer,Boolean> overlap = new HashMap<>();
        int xDimensions = 0;
        int yDimensions = 0;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            xDimensions = str.length();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i)!='.'){
                    if (!antennaLocations.containsKey(str.charAt(i))){
                        antennaLocations.put(str.charAt(i),new ArrayList<>());
                    }
                    ArrayList<Integer> antenna = antennaLocations.get(str.charAt(i));
                    antenna.add(xDimensions*yDimensions+i);
                }
            }
            yDimensions++;
        }
        for (Character c: antennaLocations.keySet()){
            ArrayList<Integer> allLocations = antennaLocations.get(c);
            for (int i = 0; i < allLocations.size(); i++) {
                int x1 = allLocations.get(i)%xDimensions;
                int y1 = allLocations.get(i)/xDimensions;
                for (int j = i+1; j < allLocations.size(); j++) {
                    int x2 = allLocations.get(j)%xDimensions;
                    int y2 = allLocations.get(j)/xDimensions;
                    int newX1 = (x1+(x1-x2));
                    int newX2 = (x2+(x2-x1));
                    int newY1 = (y1+(y1-y2));
                    int newY2 = (y2+(y2-y1));
                    if (newX1 >= 0 && newX1 < xDimensions && newY1 >= 0 && newY1 < yDimensions) {
                        overlap.put(newX1 + (newY1 * xDimensions), true);
                    }
                    if (newX2 >= 0 && newX2 < xDimensions && newY2 >= 0 && newY2 < yDimensions) {
                        overlap.put(newX2 + (newY2 * xDimensions), true);
                    }
                }
            }
        }
        System.out.println(overlap.size());
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        HashMap<Character,ArrayList<Integer>> antennaLocations = new HashMap<>();
        HashMap<Integer,Boolean> overlap = new HashMap<>();
        int xDimensions = 0;
        int yDimensions = 0;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            xDimensions = str.length();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i)!='.'){
                    if (!antennaLocations.containsKey(str.charAt(i))){
                        antennaLocations.put(str.charAt(i),new ArrayList<>());
                    }
                    ArrayList<Integer> antenna = antennaLocations.get(str.charAt(i));
                    antenna.add(xDimensions*yDimensions+i);
                }
            }
            yDimensions++;
        }
        for (Character c: antennaLocations.keySet()){
            ArrayList<Integer> allLocations = antennaLocations.get(c);
            for (int i = 0; i < allLocations.size(); i++) {
                int x1 = allLocations.get(i)%xDimensions;
                int y1 = allLocations.get(i)/xDimensions;
                for (int j = i+1; j < allLocations.size(); j++) {
                    int x2 = allLocations.get(j)%xDimensions;
                    int y2 = allLocations.get(j)/xDimensions;
                    int newX = x1-x2;
                    int newY = y1-y2;
                    for (int k = 0; x1+(newX*k) < xDimensions && y1+(newY*k) < yDimensions && x1+(newX*k) >= 0 && y1+(newY*k) >= 0; k++) {
                        overlap.put(x1+(newX*k) + (y1+(newY*k))*xDimensions,true);
                    }
                    for (int k = -1;  x1+(newX*k) < xDimensions && y1+(newY*k) < yDimensions && x1+(newX*k) >= 0 && y1+(newY*k) >= 0; k--) {
                        overlap.put(x1+(newX*k) + (y1+(newY*k))*xDimensions,true);
                    }
                }
            }
        }
        System.out.println(overlap.size());
    }
}
