package com.eziosoft.EzioDiff;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BatchRename {

    public static void rename(String[] args) throws IOException {
        if (args.length < 3){
            System.err.println("Error: this program requires 3 arguments, in order:");
            System.out.println("file to make copies of");
            System.out.println("Folder of files to obtain filenames from");
            System.out.println("Output Folder");
            System.exit(-1);
        }
        File input = new File(args[0]);
        if (!input.exists() || input.isDirectory()){
            System.err.println("Error: input file either doesnt exist or is directory!");
            System.err.println(input.getAbsolutePath());
            System.exit(-1);
        }
        File nameFolder = new File(args[1]);
        if (!nameFolder.exists() || !nameFolder.isDirectory()){
            System.err.println("Error: input name folder does not exist or is a file!");
            System.err.println(nameFolder.getAbsolutePath());
            System.exit(-1);
        }
        File out = new File(args[2]);
        if (!out.exists() || !out.isDirectory()){
            System.err.println("Error: output does not exist or is a file!");
            System.err.println(out.getAbsolutePath());
            System.exit(-1);
        }
        // do the stuff
        File[] files = nameFolder.listFiles();
        int total = 0;
        for (File f : files){
            Files.copy(Paths.get(input.getAbsolutePath()), Paths.get(new File(out, f.getName()).getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            total++;
            System.out.println("Created file " + f.getName());
        }
        System.out.println("Operation completed. Created " + total + " copies of " + input.getName());
    }
}
