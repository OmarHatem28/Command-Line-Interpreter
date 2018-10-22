package com.company;

import java.util.Scanner;

public class Main extends Thread{

    public static void main(String[] args) {

        Parser parser = new Parser();
        parser.parse("pwd");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        // cp     "C:\Users"    "E:\my test"    "|"    cd "E:\Users"
        int ind = command.indexOf("|");
        while ( ind != -1 ){
            String sub = command.substring(0,ind);
            command = command.substring(ind+1);
            if ( parser.parse(sub) ){
                System.out.println("Success");
            }
            else {
                System.out.println("Fail");
            }
            ind = command.indexOf("|");
        }
        parser.parse(command);
    }
}
