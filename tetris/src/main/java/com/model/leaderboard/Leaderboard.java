package com.model.leaderboard;

import com.model.tetris.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Leaderboard {
    static List<Player> players = new ArrayList<Player>();
    static List<Integer> scores = new ArrayList<Integer>();
    static final String entry = "Menu";

    public Leaderboard() throws FileNotFoundException {
        try {
            File lbd = new File("src/main/resources/Leaderboard.txt");
            Scanner s = new Scanner(lbd);
            List<String> f = new ArrayList<String>();

            while (s.hasNextLine()){
                String data = s.nextLine();
                String[] fields = data.split(",");

                for (String a: fields){
                    f.add(a);
                }

                Player p = new Player(f.get(0));
                players.add(p);
                scores.add(Integer.parseInt(f.get(1)));

                f.clear();
            }

            lbd.delete();
        } catch (FileNotFoundException e) {
            System.out.println("LEADERBOARD FILE IS MISSING.");
        }

    }

    public boolean isSelectedMenu() {
        return true;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public List<Integer> getScores(){
        return scores;
    }

    public boolean checkIfEnters(int score) {
        return (score > scores.get(scores.size() - 1));
    }

    public void addScore(int score, Player player) throws IOException {
        int i = 0;
        boolean found = false;

       while (i>=0 && i<=5) {
            if (score > scores.get(i)) {
               if(!found) {
                    players.add(i, player);
                    scores.add(i, score);
                    players.remove(players.size() - 1);
                    scores.remove(scores.size() - 1);
                    found = true;
                }
            }
            i++;
        }
        save();
    }

    public void save() throws IOException {
        String filePath = "src/main/resources/Leaderboard.txt";
        File lbd = new File(filePath);
        StringBuffer sb = new StringBuffer();


        for (int i = 0; i < 6; i++){
            String line = players.get(i).getName() + "," + scores.get(i) + "\n";
            sb.append(line);
        }

        String result = sb.toString();
        PrintWriter writer = new PrintWriter(lbd);
        writer.append(result);
        writer.flush();
    }
}
