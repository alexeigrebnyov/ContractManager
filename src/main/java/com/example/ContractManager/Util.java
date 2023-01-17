package com.example.ContractManager;

import java.time.LocalDate;
import java.util.Date;

public class Util {
    public static void main(String[] args) {


        String one = "10790\n" +
                "10792\n" +
                "10846\n" +
                "10847\n" +
                "10904\n" +
                "10905\n" +
                "11387\n" +
                "11388\n" +
                "12430\n" +
                "12432\n" +
                "12433\n";
        String[] mass = one.split("\\s+");
        one="";
        for (int i = 0; i < mass.length ; i++) {
            one+= mass[i]+",";
        }
        System.out.println(LocalDate.parse("2018-05-05").compareTo(LocalDate.parse("2018-05-05")));
    }
}
