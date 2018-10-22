package com.company;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Terminal {

    private static File curFile;
    private final File root;

    public Terminal() {
        curFile = new File("C:\\Users\\basem\\Desktop\\CLI root");
        root = new File("C:\\Users\\basem\\Desktop\\CLI root");
    }

    public void cp(String source, String dest) {
        try {
            File fileSource = new File(source);
            File fileDest = new File(dest);
            Files.copy(fileSource.toPath(), fileDest.toPath());
            //Files.walkFileTree(fileSource.toPath(), fileDest.toPath());
            System.out.println("1 File Copied Successfully.");
        }
        catch (Exception e){
            System.out.println("Error "+e);
        }
    }

    public void mv(String source, String dest) {
        try{
            File fileSource = new File(source);
            File fileDest = new File(dest);
            Files.move(fileSource.toPath(), fileDest.toPath());
            System.out.println("1 File/Folder Moved Successfully.");
        }
        catch (Exception e){
            System.out.println("Error "+e);
        }
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

    public void cat(String path){
        File temp_file = new File(path);
        try {
            Scanner sc = new Scanner(temp_file);
            sc.useDelimiter("\\Z");
            System.out.println(sc.next());
        }catch(FileNotFoundException ex){
            System.out.println("File not found!");
        }
    }

    public void cat(String sourcePath,String destPath,boolean overwrite){
        File temp_file1 = new File(sourcePath);
        File temp_file2 = new File(destPath);
        if(overwrite){ //using > operator
            try {
                Scanner sc = new Scanner(temp_file1);
                sc.useDelimiter("\\Z");
                BufferedWriter writer = new BufferedWriter(new FileWriter(temp_file2));
                writer.write(sc.next());
                writer.close();
            }catch(FileNotFoundException ex){
                System.out.println("File not found!");
            }catch(IOException ioex){
                System.out.println("Something went wrong!");
            }
        }
        else{          //using >> operator (append)
            try {
                Scanner sc = new Scanner(temp_file1);
                sc.useDelimiter("\\Z");
                BufferedWriter writer = new BufferedWriter(new FileWriter(temp_file2,true));
                writer.newLine();
                writer.append(sc.next());
                writer.close();
            }catch(FileNotFoundException ex){
                System.out.println("File not found!");
            }catch(IOException ioex){
                System.out.println("Something went wrong!");
            }
        }
    }

}
