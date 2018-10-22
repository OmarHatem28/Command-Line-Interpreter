package com.company;

public class Main extends Thread{

    public static void main(String[] args) {

        Parser parser = new Parser();
        if ( parser.parse("cp     \"C:\\Users\"    \"E:\\my test\"    \"|\"    cd \"E:\\Users\"") ){
            System.out.println("Success");
        }
        else {
            System.out.println("Fail");
        }

    }
}
