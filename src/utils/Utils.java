package utils;

import java.util.Scanner;

public class Utils {
    public static int safeNextInt(Scanner sc){
        while(!sc.hasNextInt()){ sc.next(); System.out.println("Enter a number"); }
        return sc.nextInt();
    }
}
