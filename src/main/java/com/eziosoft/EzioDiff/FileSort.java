package com.eziosoft.EzioDiff;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public class FileSort {

    public static void sort(String[] args) throws IOException {
        System.out.println("Eziosoft file sorter");
        if (args.length < 3){
            System.err.println("Requires 3 arguments!");
            System.err.println("1- path to original folder of files");
            System.err.println("2- path to folder to compare");
            System.err.println("3- path to output folder");
            // April 22, 2022 update
            System.err.println("Additional Flags (place after the 3 req'd arguments)");
            System.err.println("i - ignore files that do not exist in source");
            System.err.println("m - move files to output instead of copying them");
            System.exit(-1);
        }
        // we need to look for the additional flags first
        // java is fucking dense and we need to convert to a list first
        List<String> why = Arrays.asList(args);
        boolean ignore = why.contains("i");
        boolean move = why.contains("m");
        if (ignore){
            System.out.println("Notice: running in IGNORE mode!");
        }
        if (move){
            System.out.println("Notice: running in MOVE mode!");
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
        File currentFile;
        for (File f : list){
            currentFile = new File(in, f.getName());
            // are we running in ignore mode?
            if (ignore){
                // does it exist? if not, skip it
                if (!currentFile.exists()){
                    System.out.println("File " + currentFile.getName() + " does not exist in source directory! Skipping...");
                    continue;
                }
            }
            if (!FileUtils.contentEquals(f, currentFile)){
                if (move){
                    Files.move(Paths.get(f.getAbsolutePath()), Paths.get(new File(out, f.getName()).getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.copy(Paths.get(f.getAbsolutePath()), Paths.get(new File(out, f.getName()).getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                }
                System.out.println(f.getName() + " is different from source directory");
                diff++;
            }
        }
        System.out.println("processed " + list.length + " files, " + diff + " different files " + (move ? "moved" : "copied") + ".");
    }
}
