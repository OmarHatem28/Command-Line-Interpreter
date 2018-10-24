package com.company;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {

    private static File curFile;
    private final File root;
    private final ArrayList<String> allCommands = new ArrayList<>();
    private final ArrayList<String> description = new ArrayList<>();
    private final ArrayList<String> argNeeded = new ArrayList<>();

    public Terminal() {
        curFile = new File("C:\\Users\\basem\\Desktop\\CLI root");
        root = new File("C:\\Users\\basem\\Desktop\\CLI root");
        fillCommands();
    }

    private void fillCommands() {
        allCommands.add("cd");
        description.add(" : Change Directory");
        argNeeded.add("Takes Required Destination Path");

        allCommands.add("cp");
        description.add(" : Copy A file");
        argNeeded.add("Takes Source File Path & Destination File Path");

        allCommands.add("ls");
        description.add(" : List all Files/Folders in current directory");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("rm");
        description.add(" : Remove a File");
        argNeeded.add("Takes the Required File Path");

        allCommands.add("mv");
        description.add(" : Move a File/Folder");
        argNeeded.add("Takes Source File Path & Destination File Path");

        allCommands.add("cat");
        description.add(" : Print Content of 1 or more files to the terminal or to another file. \">\" to override the file and \">>\" to append to file");
        argNeeded.add("cat file1 file2,fileN or file1,file2,fileN > fileX or file1,file2,fileN >> fileX");

        allCommands.add("pwd");
        description.add(" : Print Working Directory");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("more");
        description.add(" : Print Content of a file with a scrollable manner");
        argNeeded.add("Path to file Required");

        allCommands.add("help");
        description.add(" : Print the description of all the commands");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("args");
        description.add(" : Print the required arguments for a specific command");
        argNeeded.add("Takes a specific Command");

        allCommands.add("date");
        description.add(" : Print Current Date/Time");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("exit");
        description.add(" : Terminate the process");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("mkdir");
        description.add(" : Make new Directory");
        argNeeded.add("Takes the name of the new Directory");

        allCommands.add("rmdir");
        description.add(" : Remove Directory");
        argNeeded.add("Takes the name of the Directory");

        allCommands.add("clear");
        description.add(" : Clear Terminal Screen");
        argNeeded.add("Doesn't have Arguments");
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
        if ( arr.length == 0 ){
            System.out.println("Folder Is Empty");
            return;
        }
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
            System.out.println(temp_file.getName());
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

    public void help(){
        for ( int i=0;i<allCommands.size();i++){
            System.out.println(allCommands.get(i) + description.get(i));
        }
    }

    public String args(String s) {
        for ( int i=0;i<allCommands.size();i++){
            if ( s.equals(allCommands.get(i)) ){
                return allCommands.get(i) + " : " + argNeeded.get(i);
            }
        }
        return "";
    }

    public void more(String name){
        String path = pwd() + "\\" + name;
        File temp_file = new File(path);
        try{
            System.out.println(temp_file.getName());
            Scanner sc = new Scanner(temp_file);
            Scanner doesNothing = new Scanner(System.in);
            while(sc.hasNextLine()){
                for(int i=0;i<10;i++) {
                    if (!sc.hasNextLine())
                        break;
                    System.out.println(sc.nextLine());
                }
                String dummy = doesNothing.nextLine();
            }
            System.out.println("End of file.");
        }catch(FileNotFoundException ex){
            System.out.println("File not found!");
        }
    }

    public void mkdir(String name){
        String path = pwd() + "\\" + name;
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
            // mkdirs creates non-existent parent directories in path
            // mkdir doesn't
        }
    }

    public void rmdir(String name){
        String path = pwd() + "\\" + name;
        File dir= new File(path);
        if(dir.exists() && dir.isDirectory()){
            File[] files = dir.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    rmdir(files[i].getPath());
                }
                else {
                    files[i].delete();
                }
            }
            if ( dir.delete() ){
                System.out.println("Folder "+name+" Deleted Successfully");
                return;
            } else {
                System.out.println("Couldn't Delete Folder "+name);
                return;
            }

        }
        else {
            System.out.println("Folder Doesn't Exist");
            return;
        }
    }

    public void rm(String path){
        File file= new File(path);
        if(file.exists() && file.isFile()){
            file.delete();
        }
        else
            System.out.println("File doesn't exist!");
    }
}
