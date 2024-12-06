import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        int total = 0;
        ArrayList<ArrayList<Boolean>> visited = new ArrayList<>();
        ArrayList<ArrayList<Boolean>> block = new ArrayList<>();
        int xPos = -1;
        int yPos = -1;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            visited.add(new ArrayList<>());
            block.add(new ArrayList<>());
            for (int i = 0; i < str.length(); i++) {
                visited.getLast().add(false);
                block.getLast().add(str.charAt(i) == '#');
                if (str.charAt(i) == '^'){
                    xPos = visited.size()-1;
                    yPos = i;
                }
            }
        }
        int xDir = -1;
        int yDir = 0;
        visited.get(xPos).set(yPos,true);
        while (true){
            if (xPos+xDir < 0 || yPos+yDir < 0 || xPos+xDir >= block.size() || yPos+yDir >= block.getFirst().size()){
                break;
            }
            if (block.get(xPos+xDir).get(yPos+yDir)){
                int zDir = -xDir;
                xDir = yDir;
                yDir = zDir;
            }
            else{
                xPos+=xDir;
                yPos+=yDir;
                visited.get(xPos).set(yPos,true);
            }
        }
        for (int i = 0; i < visited.size(); i++) {
            for (int j = 0; j < visited.get(i).size(); j++) {
                if (visited.get(i).get(j)){
                    total++;
                }
            }
        }
        System.out.println(total);
    }
    static int startXPos;
    static int startYPos;
    static int xDim;
    static ArrayList<ArrayList<Integer>> xBlocks = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> yBlocks = new ArrayList<>();
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        int total = 0;
        ArrayList<ArrayList<Boolean>> block = new ArrayList<>();
        ArrayList<ArrayList<Boolean>> checked = new ArrayList<>();
        int xPos = -1;
        int yPos = -1;
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            block.add(new ArrayList<>());
            checked.add(new ArrayList<>());
            yBlocks.add(new ArrayList<>());
            if (xBlocks.size() < str.length()){
                for (int i = 0; i < str.length(); i++) {
                    xBlocks.add(new ArrayList<>());
                }
            }
            for (int i = 0; i < str.length(); i++) {
                block.getLast().add(str.charAt(i) == '#');
                if (str.charAt(i) == '#') {
                    yBlocks.getLast().add(i);
                    xBlocks.get(i).add(yBlocks.size()-1);
                }
                checked.getLast().add(false);
                if (str.charAt(i) == '^'){
                    xPos = block.size()-1;
                    yPos = i;
                }
            }
        }
        xDim = block.size();
        int xDir = -1;
        int yDir = 0;
        startXPos = xPos;
        startYPos = yPos;

        while (true){
            if (xPos+xDir < 0 || yPos+yDir < 0 || xPos+xDir >= block.size() || yPos+yDir >= block.getFirst().size()){
                break;
            }
            if (block.get(xPos+xDir).get(yPos+yDir)){
                int zDir = -xDir;
                xDir = yDir;
                yDir = zDir;
            }
            else{
                xPos+=xDir;
                yPos+=yDir;
                if (!checked.get(xPos).get(yPos)){
                    checked.get(xPos).set(yPos,true);
                    int i;
                    for (i = 0; i <= yBlocks.get(xPos).size(); i++) {
                        if (i == yBlocks.get(xPos).size()){
                            yBlocks.get(xPos).add(i,yPos);
                            break;
                        }
                        else if (yBlocks.get(xPos).get(i) > yPos){
                            yBlocks.get(xPos).add(i,yPos);
                            break;
                        }
                    }
                    int j;
                    for (j = 0; j <= xBlocks.get(yPos).size(); j++) {
                        if (j == xBlocks.get(yPos).size()){
                            xBlocks.get(yPos).add(j,xPos);
                            break;
                        }
                        else if (xBlocks.get(yPos).get(j) > xPos){
                            xBlocks.get(yPos).add(j,xPos);
                            break;
                        }
                    }
                    if (checkForLoop()){
                        total++;
                    }

                    yBlocks.get(xPos).remove(i);
                    xBlocks.get(yPos).remove(j);
                }

            }
        }
        System.out.println(total);
    }
    public static boolean checkForLoop(){
        int xDir = -1;
        int yDir = 0;
        int xPos = startXPos;
        int yPos = startYPos;
        HashMap<Integer,String> map = new HashMap<>();
        while (true){
            if (xDir == -1){
                for (int i = xBlocks.get(yPos).size()-1; i >= -1; i--) {
                    if (i == -1){
                        return false;
                    }
                    if (xBlocks.get(yPos).get(i) < xPos){
                        String str = map.getOrDefault(yPos*xDim + xBlocks.get(yPos).get(i),"");
                        if (str.contains("^")){
                            return true;
                        }
                        else{
                            str+="^";
                            map.put(yPos*xDim + xBlocks.get(yPos).get(i),str);
                        }
                        xPos = xBlocks.get(yPos).get(i)+1;
                        break;
                    }
                }
            }
            if (xDir == 1){
                for (int i = 0; i <= xBlocks.get(yPos).size(); i++) {
                    if (i == xBlocks.get(yPos).size()){
                        return false;
                    }
                    if (xBlocks.get(yPos).get(i) > xPos){
                        String str = map.getOrDefault(yPos*xDim + xBlocks.get(yPos).get(i),"");
                        if (str.contains("v")){
                            return true;
                        }
                        else{
                            str+="v";
                            map.put(yPos*xDim + xBlocks.get(yPos).get(i),str);
                        }
                        xPos = xBlocks.get(yPos).get(i)-1;
                        break;
                    }
                }
            }
            if (yDir == -1){
                for (int i = yBlocks.get(xPos).size()-1; i >= -1; i--) {

                    if (i == -1){
                        return false;
                    }
                    if (yBlocks.get(xPos).get(i) < yPos){
                        String str = map.getOrDefault(yBlocks.get(xPos).get(i)*xDim+xPos,"");
                        if (str.contains("<")){
                            return true;
                        }
                        else{
                            str+="<";
                            map.put(yBlocks.get(xPos).get(i)*xDim+xPos,str);
                        }
                        yPos = yBlocks.get(xPos).get(i)+1;
                        break;
                    }
                }
            }
            if (yDir == 1){
                for (int i = 0; i <= yBlocks.get(xPos).size(); i++) {
                    if (i == yBlocks.get(xPos).size()){
                        return false;
                    }
                    if (yBlocks.get(xPos).get(i) > yPos){
                        String str = map.getOrDefault(yBlocks.get(xPos).get(i)*xDim+xPos,"");
                        if (str.contains(">")){
                            return true;
                        }
                        else{
                            str+=">";
                            map.put(yBlocks.get(xPos).get(i)*xDim+xPos,str);
                        }
                        yPos = yBlocks.get(xPos).get(i)-1;
                        break;
                    }
                }
            }
            int zDir = -xDir;
            xDir = yDir;
            yDir = zDir;
        }
    }
}
