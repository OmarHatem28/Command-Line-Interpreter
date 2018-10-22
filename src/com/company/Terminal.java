package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Terminal {

    private static File curFile;
    private final File root;

    public Terminal() {
        curFile = new File("C:\\Users\\basem\\Desktop\\CLI root");
        root = new File("C:\\Users\\basem\\Desktop\\CLI root");
    }

    public void cp(String source, String dest) throws IOException {
        File fileSource = new File(source);
        File fileDest = new File(dest);
        Files.copy(fileSource.toPath(), fileDest.toPath());
        //Files.walkFileTree(fileSource.toPath(), fileDest.toPath());
    }

    public void mv(String source, String dest) throws IOException {
        File fileSource = new File(source);
        File fileDest = new File(dest);
        Files.move(fileSource.toPath(), fileDest.toPath());
    }

    public void ls() {
        String arr[] = curFile.list();
        for (String str : arr)
            System.out.println(str);
    }

    public String pwd() {
//        System.out.println(curFile.getPath());
        return curFile.getPath();
    }

    public void clear() {
        for(int i=0;i<20;i++)
            System.out.println();
    }

    public void cd(String new_dir ) {
        File temp_file = new File(new_dir);
        if(new_dir.isEmpty())   //cd with no args
            curFile = root;
        else
            curFile = temp_file;
    }

    public void date() {
        System.out.println(java.time.LocalDate.now() + " " + java.time.LocalTime.now());
    }

    public void cat(){

    }


}
