package com.model.tetris;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player  {
    String name;

    public Player(){
        try {
            String filePath = "src/main/resources/animais.txt";
            File lbd = new File(filePath);
            Scanner s = new Scanner(lbd);
            StringBuffer sb = new StringBuffer();
            List<String> f = new ArrayList<String>();

            while (s.hasNext()){
                String data = s.nextLine();
                sb.append(data);
                sb.append("\n");
                String[] fields = data.split(",");

                for (String a: fields){
                    if(!a.isEmpty()) {
                        f.add(a);
                    }
                }
            }

            this.name = f.get(f.size()-1);
            f.clear();

            String result = sb.toString();
            result=result.replaceAll(this.name,"");
            PrintWriter writer = new PrintWriter(new File(filePath));
            writer.append(result);
            writer.flush();



            lbd.delete();
        } catch (FileNotFoundException e) {
            System.out.println("ANIMAIS FILE IS MISSING.");
        }
    }

    public Player(String name){
        this.name = name;
    }


    public String getName(){
        return name;
    }

}

