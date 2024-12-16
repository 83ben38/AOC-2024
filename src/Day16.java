import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day16 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static ArrayList<ArrayList<Integer>>[] minValues = new ArrayList[4];
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        int total = Integer.MAX_VALUE;
        ArrayList<ArrayList<Character>> chars = new ArrayList<>();
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            chars.add(new ArrayList<>());
            for (int i = 0; i < str.length(); i++) {
                chars.getLast().add(str.charAt(i));
            }
        }
        for (int i = 0; i < 4; i++) {
            minValues[i] = new ArrayList<>();
            for (int j = 0; j < chars.size(); j++) {
                minValues[i].add(new ArrayList<>());
                for (int k = 0; k < chars.getFirst().size(); k++) {
                    minValues[i].getLast().add(-1);
                }
            }
        }
        ArrayList<int[]> positions = new ArrayList<>();
        positions.add(new int[]{chars.size()-2,1,1,0});
        while (!positions.isEmpty()){
            int[] t = positions.removeFirst();
            minValues[t[2]].get(t[0]).set(t[1],t[3]);
            int[] move1 = new int[]{t[0],t[1],t[2],t[3]+1};
            if (t[2]==0){
                move1[0]--;
            }
            else if (t[2]==1){
                move1[1]++;
            }
            else if (t[2]==2){
                move1[0]++;
            }
            else{
                move1[1]--;
            }
            if (chars.get(move1[0]).get(move1[1])=='#'){

            }
            else if (minValues[move1[2]].get(move1[0]).get(move1[1])!=-1){

            }
            else{
                for (int i = 0; i <= positions.size(); i++) {
                    if (i==positions.size()){
                        positions.add(move1);
                        break;
                    }
                    int[] z = positions.get(i);
                    if (z[0]==move1[0]&&z[1]==move1[1]&&z[2]==move1[2]){
                        z[3] = Math.min(z[3],move1[3]);
                        break;
                    }
                    if (z[3]>move1[3]){
                        positions.add(i,move1);
                        break;
                    }
                }
            }
            move1 = new int[]{t[0],t[1],t[2],t[3]+1000};
            if (t[2]==0){
                move1[2]=3;
            }
            else{
                move1[2]=t[2]-1;
            }
            if (minValues[move1[2]].get(move1[0]).get(move1[1])!=-1){

            }
            else{
                for (int i = 0; i <= positions.size(); i++) {
                    if (i==positions.size()){
                        positions.add(move1);
                        break;
                    }
                    int[] z = positions.get(i);
                    if (z[0]==move1[0]&&z[1]==move1[1]&&z[2]==move1[2]){
                        z[3] = Math.min(z[3],move1[3]);
                        break;
                    }
                    if (z[3]>move1[3]){
                        positions.add(i,move1);
                        break;
                    }
                }
            }
            move1 = new int[]{t[0],t[1],t[2],t[3]+1000};
            if (t[2]==3){
                move1[2]=0;
            }
            else{
                move1[2]=t[2]+1;
            }
            if (minValues[move1[2]].get(move1[0]).get(move1[1])!=-1){

            }
            else{
                for (int i = 0; i <= positions.size(); i++) {
                    if (i==positions.size()){
                        positions.add(move1);
                        break;
                    }
                    int[] z = positions.get(i);
                    if (z[0]==move1[0]&&z[1]==move1[1]&&z[2]==move1[2]){
                        z[3] = Math.min(z[3],move1[3]);
                        break;
                    }
                    if (z[3]>move1[3]){
                        positions.add(i,move1);
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            int z = minValues[i].get(1).get(chars.getFirst().size()-2);
            if (z < total){
                total = z;
            }
        }
        System.out.println(total);
    }
    public static ArrayList<ArrayList<Boolean>>[] benchable = new ArrayList[4];
    public static void part2() throws FileNotFoundException {
        for (int p = 0; p < 4; p++) {
            benchable[p] = new ArrayList<>();
            for (int i = 0; i < minValues[0].size(); i++) {
                benchable[p].add(new ArrayList<>());
                for (int j = 0; j < minValues[0].get(i).size(); j++) {
                    benchable[p].getLast().add(false);
                }
            }
        }

        ArrayList<int[]> possiblePositions = new ArrayList<>();
        int best = -1;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int z = minValues[i].get(1).get(minValues[i].getFirst().size()-2);
            if (z < max){
                max = z;
                best = i;
            }
        }
        possiblePositions.add(new int[]{1,minValues[0].getFirst().size()-2,best});
        while (!possiblePositions.isEmpty()){
            int[] t =possiblePositions.removeFirst();
            benchable[t[2]].get(t[0]).set(t[1],true);
            int[] move1 = new int[]{t[0],t[1],t[2]};
            if (t[2]==0){
                move1[0]++;
            }
            else if (t[2]==1){
                move1[1]--;
            }
            else if (t[2]==2){
                move1[0]--;
            }
            else{
                move1[1]++;
            }
            if (minValues[move1[2]].get(move1[0]).get(move1[1])==-1){

            }
            else if (minValues[t[2]].get(t[0]).get(t[1])-minValues[move1[2]].get(move1[0]).get(move1[1])!=1){

            }
            else{
                for (int i = 0; i <= possiblePositions.size(); i++) {
                    if (i==possiblePositions.size()){
                        possiblePositions.add(move1);
                        break;
                    }
                    int[] z = possiblePositions.get(i);
                    if (z[0]==move1[0]&&z[1]==move1[1]&&z[2]==move1[2]){
                        break;
                    }
                }
            }
            move1 = new int[]{t[0],t[1],t[2]};
            if (t[2]==3){
                move1[2]=0;
            }
            else{
                move1[2]=t[2]+1;
            }
            if (minValues[t[2]].get(t[0]).get(t[1])-minValues[move1[2]].get(move1[0]).get(move1[1])!=1000){

            }
            else{
                for (int i = 0; i <= possiblePositions.size(); i++) {
                    if (i==possiblePositions.size()){
                        possiblePositions.add(move1);
                        break;
                    }
                    int[] z = possiblePositions.get(i);
                    if (z[0]==move1[0]&&z[1]==move1[1]&&z[2]==move1[2]){
                        break;
                    }
                }
            }
            move1 = new int[]{t[0],t[1],t[2]};
            if (t[2]==0){
                move1[2]=3;
            }
            else{
                move1[2]=t[2]-1;
            }
            if (minValues[t[2]].get(t[0]).get(t[1])-minValues[move1[2]].get(move1[0]).get(move1[1])!=1000){

            }
            else{
                for (int i = 0; i <= possiblePositions.size(); i++) {
                    if (i==possiblePositions.size()){
                        possiblePositions.add(move1);
                        break;
                    }
                    int[] z = possiblePositions.get(i);
                    if (z[0]==move1[0]&&z[1]==move1[1]&&z[2]==move1[2]){
                        break;
                    }
                }
            }
        }
        int total = 0;
        for (int i = 0; i < benchable[0].size(); i++) {
            for (int j = 0; j < benchable[0].getFirst().size(); j++) {
                boolean sittable = false;
                for (int k = 0; k < 4; k++) {
                    if (benchable[k].get(i).get(j)){
                        sittable = true;
                    }
                }
                if (sittable){
                    total++;
                }
            }
        }
        System.out.println(total);
    }
}
