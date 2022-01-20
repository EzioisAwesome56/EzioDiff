package com.eziosoft.EzioDiff;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSort {

    public static void sort(String[] args) throws IOException {
        System.out.println("Eziosoft file sorter");
        if (args.length < 3){
            System.err.println("Requires 3 arguments!");
            System.err.println("1- path to original folder of files");
            System.err.println("2- path to folder to compare");
            System.err.println("3- path to output folder");
            System.exit(-1);
        }
        File in = new File(args[0]);
        File comp = new File(args[1]);
        File out = new File(args[2]);
        if (!in.exists() || !in.isDirectory()){
            System.err.println("Error: input folder either doesnt exist or is file!");
            System.err.println(in.getAbsolutePath());
            System.exit(-1);
        }
        if (!comp.exists() || !comp.isDirectory()){
            System.err.println("Error: comparison folder either doesnt exist or is file!");
            System.err.println(comp.getAbsolutePath());
            System.exit(-1);
        }
        if (!out.exists() || !out.isDirectory()){
            System.err.println("Error: output folder either doesnt exist or is file!");
            System.err.println(out.getAbsolutePath());
            System.exit(-1);
        }
        File[] list = comp.listFiles();
        int diff = 0;
        for (File f : list){
            if (!FileUtils.contentEquals(f, new File(in, f.getName()))){
                Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(new File(out, f.getName()).getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(f.getName() + " is different from source directory");
                diff++;
            }
        }
        System.out.println("processed " + list.length + " files, " + diff + " different files copied.");
    }
}
