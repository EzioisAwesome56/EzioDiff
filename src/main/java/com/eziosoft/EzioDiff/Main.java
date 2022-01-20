package com.eziosoft.EzioDiff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 1){
            System.err.println("Error: you must select which mode to operate in!");
            System.exit(-1);
        }
        String selection = args[0];
        ArrayList<String> gamer = new ArrayList<>();
        Collections.addAll(gamer, args);
        gamer.remove(0);
        String[] dank = gamer.toArray(new String[0]);
        if (selection.equals("sort")){
            FileSort.sort(dank);
        } else if (selection.equals("copyname")){
            BatchRename.rename(dank);
        } else {
            System.err.println("Error: invalid selection!");
        }
    }
}
