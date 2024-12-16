import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        ArrayList<ArrayList<Character>> chars = new ArrayList<>();
        int x = -1;
        int y = -1;
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.isEmpty()) {
                break;
            }
            chars.add(new ArrayList<>());
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i)=='@'){
                    x = chars.size()-1;
                    y = i;
                    chars.getLast().add('.');
                }
                else {
                    chars.getLast().add(str.charAt(i));
                }

            }
        }
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                int xDif = 0;
                int yDif = 0;
                switch (str.charAt(i)) {
                    case '<' -> yDif = -1;
                    case '>' -> yDif = 1;
                    case '^' -> xDif = -1;
                    case 'v' -> xDif = 1;
                }
                if (chars.get(x + xDif).get(y + yDif) == '.') {
                    x += xDif;
                    y += yDif;
                    continue;
                }
                int newX = x + xDif;
                int newY = y + yDif;
                while (true) {
                    if (chars.get(newX).get(newY) == '#') {
                        break;
                    }
                    if (chars.get(newX).get(newY) == '.') {
                        chars.get(newX).set(newY, 'O');
                        chars.get(x + xDif).set(y + yDif, '.');
                        x += xDif;
                        y += yDif;
                        break;
                    }
                    newX += xDif;
                    newY += yDif;
                }
            }
        }
        for (int i = 0; i < chars.size(); i++) {
            for (int j = 0; j < chars.get(i).size(); j++) {
                if (chars.get(i).get(j)=='O'){
                    total+= 100L *i+j;
                }
            }
        }
        System.out.println(total);
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        ArrayList<ArrayList<Character>> chars = new ArrayList<>();
        int x = -1;
        int y = -1;
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.isEmpty()) {
                break;
            }
            chars.add(new ArrayList<>());
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i)=='@'){
                    x = chars.size()-1;
                    y = i*2;
                    chars.getLast().add('.');
                    chars.getLast().add('.');
                }
                else if (str.charAt(i)=='O'){
                    chars.getLast().add('[');
                    chars.getLast().add(']');
                }
                else {
                    chars.getLast().add(str.charAt(i));
                    chars.getLast().add(str.charAt(i));
                }

            }
        }
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i)=='<'||str.charAt(i)=='>'){
                    if (pushHorizontal(x,y+(str.charAt(i)=='<'?-1:1),chars,str.charAt(i)=='>')){
                        y += (str.charAt(i)=='<'?-1:1);
                    }
                }
                else{
                    boolean[][] toMove =  new boolean[chars.size()][chars.getFirst().size()];
                    if(pushVertical(x+(str.charAt(i)=='^'?-1:1),y,chars,str.charAt(i)=='^',toMove)){
                        x+=(str.charAt(i)=='^'?-1:1);
                        if (str.charAt(i)=='^') {
                            for (int j = 0; j < toMove.length; j++) {
                                for (int k = 0; k < toMove[j].length; k++) {
                                    if (toMove[j][k]) {
                                        chars.get(j -1).set(k, chars.get(j).get(k));
                                        chars.get(j).set(k, '.');
                                    }
                                }
                            }
                        }
                        else{
                            for (int j = toMove.length-1; j >= 0; j--) {
                                for (int k = 0; k < toMove[j].length; k++) {
                                    if (toMove[j][k]) {
                                        chars.get(j +1).set(k, chars.get(j).get(k));
                                        chars.get(j).set(k, '.');
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < chars.size(); i++) {
            for (int j = 0; j < chars.get(i).size(); j++) {
                if (chars.get(i).get(j)=='['){
                    total+= 100L *i+j;
                }
            }
        }
        System.out.println(total);
    }
    public static boolean pushHorizontal(int x, int y, ArrayList<ArrayList<Character>> chars, boolean right){
        if (chars.get(x).get(y)=='#') {
            return false;
        }
        else if (chars.get(x).get(y)=='.'){
            return true;
        }
        int yDif = right ? 1 : -1;
        if (pushHorizontal(x,y+yDif,chars,right)){
            chars.get(x).set(y+yDif,chars.get(x).get(y));
            chars.get(x).set(y,'.');
            return true;
        }
        return false;
    }
    public static boolean pushVertical(int x, int y, ArrayList<ArrayList<Character>> chars, boolean up, boolean[][] checked){
        if (checked[x][y]){
            return true;
        }
        if (chars.get(x).get(y)=='#'){
            return false;
        }
        else if (chars.get(x).get(y)=='.'){
            return true;
        }
        checked[x][y] = true;
        if (chars.get(x).get(y) == '['){
            if (!pushVertical(x,y+1,chars,up,checked)){
                return false;
            }
        }
        if (chars.get(x).get(y) == ']'){
            if (!pushVertical(x,y-1,chars,up,checked)){
                return false;
            }
        }
        int xDif = up ? -1 : 1;
        if (pushVertical(x+xDif,y,chars,up,checked)){
            return true;
        }
        return false;
    }
}
