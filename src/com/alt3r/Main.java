package com.alt3r;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String sample = in.nextLine();
        Object[] result = Solver.execute(sample);
        if (result[0].equals("roman")) {
            System.out.println(Solver.convertToRoman((Integer) result[1]));
        } else {
            System.out.println(result[1]);
        }
        in.close();
    }
}
