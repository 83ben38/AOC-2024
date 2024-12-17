import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Day17 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/problem.txt"));
        long a = 0;
        long b = 0;
        long c = 0;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String str = sc.nextLine();
            if (i == 0){
                a = Long.parseLong(str.substring(str.indexOf(":")+2));
            }
        }
        sc.nextLine();
        String str = sc.nextLine();
        str = str.substring(str.indexOf(":")+2);
        for (int i = 0; i < (str.length()+1)/2; i+=2) {
            int operator = str.charAt((i*2))-'0';
            int operand = str.charAt((i*2)+2)-'0';
            if (operator == 0 || operator == 6 || operator == 7 || operator == 2 || operator == 5){
                long comboOperand = switch (operand){
                    case 4 -> a;
                    case 5 -> b;
                    case 6 -> c;
                    default -> operand;
                };
                if (operator == 0 || operator == 6 || operator == 7){
                    long division = a/(int)Math.pow(2,comboOperand);
                    if (operator == 0){
                        a = division;
                    }
                    if (operator == 6){
                        b = division;
                    }
                    if (operator ==  7){
                        c=  division;
                    }
                }
                if (operator == 2){
                    b = comboOperand%8;
                }
                if (operator == 5){
                    output.append(comboOperand%8).append(",");
                }
            }
            if (operator == 1){
                b = b^operand;
            }
            if (operator == 3){
                if (a != 0){
                    i = operand-2;
                }
            }
            if (operator == 4){
                b = b^c;
            }
        }
        System.out.println(output);
    }
    public static void part2() throws FileNotFoundException {
        String output = "2417750340175530";
        findA(0,output,output.length()-1);

    }
    public static void findA(long a, String output, int i){
        if (i == -1){
            System.out.println(a);
            return;
        }
        a*=8;
        for (int j = 0; j <= 8; j++) {
            if (j == 8){
                return;
            }
//            if (j==0&&i==output.length()-1){
//                a++;
//                continue;
//            }
            long b = a%8;
            b = b^7;
            long c = a / (long)(Math.pow(2,b));
            b = b^c;
            b = b^7;
            if (b%8==output.charAt(i) - '0'){
                findA(a,output,i-1);
            }
            a++;
        }

    }
    public static void part2test(){
        String output = "035430";
        findATest(0,output,output.length()-1);
    }
    public static void findATest(long a, String output, int i){
        if (i == -1){
            System.out.println(a*8);
            return;
        }
        a*=8;
        for (int j = 0; j <= 8; j++) {
            if (j == 8){
                return;
            }
            if (a%8==output.charAt(i) - '0'){
                findATest(a,output,i-1);
            }
            a++;
        }

    }
}
