package com.company;

import java.io.File;
import java.util.ArrayList;

public class Parser {

    public ArrayList<String> args = new ArrayList<>();
    public String cmd;
    private Terminal terminal;

    public Parser(){
        terminal = new Terminal();
    }

    public Boolean parse(String command){

        String[] words =command.split("\"");
        ArrayList<String> CLI = new ArrayList<>();

        for ( int i=0;i<words.length;i++){
            words[i] = words[i].trim();
            if ( words[i].length() > 0 ){
                CLI.add(words[i]);
            }
        }
//        for(String w:CLI){
//            System.out.println(w);
//        }
//        System.out.println("=================================================");

        if ( CLI.size() == 0 ){
            return false;
        }

        File file;

        switch ( CLI.get(0) ){
            case "cd":
                if ( CLI.size() > 2 ){
                    System.out.println("Invalid Arguments cd takes 0 or 1 Arguments, Write help for more details");
                    return false;
                }
                if ( CLI.size() == 2 ){
                    if ( CLI.get(1).equals("..") ){
                        String path = terminal.pwd();
                        for ( int i=path.length()-1;i>=0;i--){
                            if ( path.charAt(i) == '\\' ){
                                path = path.substring(0,i);
                                break;
                            }
                        }
                        terminal.cd(path);
                        break;
                    }
                    file = new File(CLI.get(1));
                    if ( file.isDirectory() ) {
                        terminal.cd(CLI.get(1));
                        break;
                    }
                    file = new File(terminal.pwd()+"\\"+CLI.get(1));
                    if ( file.isDirectory() ){
                        terminal.cd(terminal.pwd()+"\\"+CLI.get(1));
                        break;
                    }
                    System.out.println("Invalid Path");
                    return false;
                }
                terminal.cd("");
                break;
                //======================================================================================================
            case "cp":
                if ( CLI.size() != 3 ){
                    System.out.println("Invalid Arguments cp takes 2 Arguments, Write help for more details");
                    return false;
                }
                terminal.cp(CLI.get(1),CLI.get(2));
                cmd = "cp";
                break;
                //======================================================================================================
            case "mv":
                if ( CLI.size() != 3 ){
                    System.out.println("Invalid Arguments mv takes 2 Arguments, Write help for more details");
                    return false;
                }
                terminal.mv(CLI.get(1),CLI.get(2));
                cmd = "mv";
                break;
                //======================================================================================================
            case "rm":
                if ( CLI.size() != 2 ){
                    System.out.println("Invalid Arguments rm takes 1 Argument, Write help for more details");
                    return false;
                }
                terminal.rm(CLI.get(1));
                cmd = "rm";
                break;
                //======================================================================================================
            case "mkdir":
                if ( CLI.size() != 2 ){
                    System.out.println("Invalid Arguments mkdir takes 1 Argument, Write help for more details");
                    return false;
                }
                terminal.mkdir(CLI.get(1));
                cmd = "mkdir";
                break;
                //======================================================================================================
            case "rmdir":
                if ( CLI.size() != 2 ){
                    System.out.println("Invalid Arguments rmdir takes 1 Argument, Write help for more details");
                    return false;
                }
                String path = terminal.pwd() + "\\" + CLI.get(1);
                terminal.rmdir(path);
                cmd = "rmdir";
                break;
                //======================================================================================================
            case "pwd":
                if ( CLI.size() > 1 ){
                    System.out.println("Invalid Arguments pwd takes no Arguments, Write help for more details");
                    return false;
                }
                System.out.print(terminal.pwd() + "> ");
                break;
                //======================================================================================================
            case "clear":
                if ( CLI.size() > 1 ){
                    System.out.println("Invalid Arguments clear takes no Arguments, Write help for more details");
                    return false;
                }
                terminal.clear();
                break;
                //======================================================================================================
            case "ls":
                if ( CLI.size() > 1 ){
                    System.out.println("Invalid Arguments ls takes no Arguments, Write help for more details");
                    return false;
                }
                terminal.ls();
                break;
                //======================================================================================================
            case "help":
                if ( CLI.size() > 1 ){
                    System.out.println("Invalid Arguments help takes no Arguments");
                    return false;
                }
                terminal.help();
                cmd = "help";
                break;
                //======================================================================================================
            case "date":
                if ( CLI.size() > 1 ){
                    System.out.println("Invalid Arguments help takes no Arguments, Write help for more details");
                    return false;
                }
                terminal.date();
                break;
                //======================================================================================================
            case "args":
                if ( CLI.size() != 2 ){
                    System.out.println("Invalid Arguments args takes 1 Argument, Write help for more details");
                    return false;
                }
                cmd = "args";
                System.out.println(terminal.args(CLI.get(1)));
                break;
                //======================================================================================================
            case "more":
                if ( CLI.size() != 2 ){
                    System.out.println("Invalid Arguments more takes 1 Argument, Write help for more details");
                    return false;
                }
                cmd = "more";
                terminal.more(CLI.get(1));
                break;
                //======================================================================================================
            case "cat":
                if ( CLI.size() < 2 ){
                    System.out.println("Invalid Arguments cat takes at least 1 Argument, Write help for more details");
                    return false;
                }
                int ind = -1;
                boolean flag = false;
                for ( int i=1;i<CLI.size();i++){
                    if ( CLI.get(i).equals(">") ){
                        ind = i;
                        flag=true;
                        break;
                    }
                    if ( CLI.get(i).equals(">>") ){
                        ind = i;
                        flag=false;
                        break;
                    }
                }
                if ( ind == -1 ){
                    for ( int i=1;i<CLI.size();i++){
                        terminal.cat(CLI.get(i));
                    }
                    break;
                }
                else{
                    for ( int i=1;i<ind && ind+1 < CLI.size();i++){
                        terminal.cat(CLI.get(i),CLI.get(ind+1),flag);
                        if ( flag ){
                            flag = false;
                        }
                    }
                }
//                for ( int i=0;i<CLI.size();i++){
//                    String path = terminal.pwd();
//                    path += "\\";
//
//                    if ( CLI.get(i).equals(">") || new File(path+CLI.get(i)).isFile() ){
//                        args.add(CLI.get(i));
//                    }
//                    else {
//                        System.out.println("Path "+CLI.get(i)+" Is Invalid.");
//                        return false;
//                    }
//                }
                break;
                //======================================================================================================
            case "exit":
                System.exit(0);
                break;
                //======================================================================================================

        }
        return true;
    }

}
