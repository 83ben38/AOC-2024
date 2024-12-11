import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        String str = sc.nextLine();
        for (int i = 0; i < str.length(); i++) {
            total+=str.charAt(i) - '0';
        }
        int[] nums = new int[(int) total];
        int j = 0;
        int p = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int k = 0; k < str.charAt(i) - '0'; k++) {
                if (i%2==0){
                    nums[j] = p;
                }
                else{
                    nums[j] = -1;
                }
                j++;
            }
            if (i%2==0){
                p++;
            }
        }
        j = 0;
        sus : for (int i = nums.length-1; i >= 0; i--) {
            if (nums[i]!=-1){
                while (nums[j]!=-1){
                    j++;
                    if (j == i){
                        break sus;
                    }
                }
                nums[j] = nums[i];
                nums[i] = -1;
            }
        }
        total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1){
                break;
            }
            else{
                total+= (long) i *nums[i];
            }
        }
        System.out.println(total);
    }
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long total = 0;
        String str = sc.nextLine();
        ArrayList<Integer> values = new ArrayList<>(str.length()+1);
        ArrayList<Integer> values2 = new ArrayList<>((str.length()+1)/2);
        ArrayList<Integer> associates = new ArrayList<>((str.length()+1)/2);
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            values.add(str.charAt(i)-'0');
            if (i%2==0){
                associates.add(j);
                values2.add(str.charAt(i)-'0');
                j++;
            }
        }
        values.add(0);
        for (int i = values2.size()-1; i >= 0; i--) {
            int z = associates.indexOf(i);
            for (int k = 1; k < values.size() && k < (z*2); k+=2) {
                if (values.get(k) >= values2.get(i)){
                    int d = values.remove((z*2));
                    values.set((z*2)-1,values.get((z*2)-1) +values.remove((z*2))+d);
                    associates.remove(z);
                    associates.add((k+1)/2,i);
                    values.add((k+1),d);
                    values.add((k+2),values.get(k)-d);
                    values.set(k,0);
                    break;
                }
            }
        }
        j = 0;
        for (int i = 0; i < values.size(); i++) {
            for (int k = 0; k < values.get(i); k++) {
                if (i%2==0){
                    total+= (long) associates.get((i / 2)) *j;
                }
                j++;
            }
        }
        System.out.println(total);
    }
}
