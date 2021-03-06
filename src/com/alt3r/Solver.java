package com.alt3r;

import java.util.HashMap;

public class Solver {

    private static String numberType(String toCheckValue) throws Exception
    {
        String[] arabicNumbers = {
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10"
        };
        String[] romanNumbers = {
                "I", "II", "III", "IV", "V",
                "VI", "VII", "VIII", "IX", "X"
        };
        boolean arabic = false, roman = false;
        for (String element : arabicNumbers) {
            if (element.equals(toCheckValue)) {
                arabic = true;
                break;
            }
        }
        for (String element : romanNumbers) {
            if (element.equals(toCheckValue)) {
                roman = true;
                break;
            }
        }
        String result;
        if (arabic) {
            result = "arabic";
        } else if (roman) {
            result = "roman";
        } else {
            throw new Exception("Error! Inappropriate numbers.");
        }
        return result;
    }

    private static int[] convertToArabic(String firstNumber, String secondNumber)
    {
        HashMap<String, Integer> roman = new HashMap<>() {
            {
                put("I", 1);
                put("II", 2);
                put("III", 3);
                put("IV", 4);
                put("V", 5);
                put("VI", 6);
                put("VII", 7);
                put("VIII", 8);
                put("IX", 9);
                put("X", 10);
            }
        };
        return new int[]{ roman.get(firstNumber), roman.get(secondNumber) };
    }

    public static String convertToRoman(int number) throws Exception
    {
        String result;
        if (number > 0) {
            result = intToRoman(number);
        } else {
            throw new Exception("Error! Roman numerals do not include zero or negative numbers");
        }
        return result;
    }

    private static String intToRoman(int num)
    {
        String[] m = {"", "M", "MM", "MMM"};
        String[] c = {"", "C", "CC", "CCC", "CD", "D",
                "DC", "DCC", "DCCC", "CM"};
        String[] x = {"", "X", "XX", "XXX", "XL", "L",
                "LX", "LXX", "LXXX", "XC"};
        String[] i = {"", "I", "II", "III", "IV", "V",
                "VI", "VII", "VIII", "IX"};

        String thousands = m[num/1000];
        String hundreds = c[(num%1000)/100];
        String tens = x[(num%100)/10];
        String ones = i[num%10];

        return thousands + hundreds + tens + ones;
    }

    private static int computation(String sign, int[] numbers) throws Exception
    {
        int result;
        switch (sign) {
            case "+":
                result = numbers[0] + numbers[1];
                break;
            case "-":
                result = numbers[0] - numbers[1];
                break;
            case "*":
                result = numbers[0] * numbers[1];
                break;
            case "/":
                result = numbers[0] / numbers[1];
                break;
            default:
                throw new Exception("Error!");
        }
        return result;
    }

    public static Object[] execute(String sample) throws Exception
    {
        String formatSample = sample.replaceAll("\\s+","");
        String sign;
        String[] parts;
        if (formatSample.contains("+")) {
            sign = "+";
            parts = formatSample.split("\\+");
        } else if (formatSample.contains("-")) {
            sign = "-";
            parts = formatSample.split("-");
        } else if (formatSample.contains("*")) {
            sign = "*";
            parts = formatSample.split("\\*");
        } else if (formatSample.contains("/")) {
            sign = "/";
            parts = formatSample.split("/");
        } else {
            throw new Exception("Error!");
        }
        String firstNumberType = numberType(parts[0]);
        String secondNumberType = numberType(parts[1]);
        int[] numbers = new int[2];
        if (!firstNumberType.equals(secondNumberType)) {
            throw new Exception("Error! Different numbers in calculation.");
        } else if (firstNumberType.equals("arabic")) {
            numbers = new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
        } else if (firstNumberType.equals("roman")) {
            numbers = convertToArabic(parts[0], parts[1]);
        }
        return new Object[]{firstNumberType, computation(sign, numbers)};
    }

}
