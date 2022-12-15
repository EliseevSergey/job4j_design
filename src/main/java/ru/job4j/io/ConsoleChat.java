package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private String path;
    private String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }
    public void run() {
        List<String> phrases = readPhrases();
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        try (PrintWriter pw = new PrintWriter(new FileWriter(botAnswers))) {
            String humanPhrase = CONTINUE;
            while (!humanPhrase.equals(OUT)) {
                humanPhrase = in.nextLine();
                switch ()
                boolean botOn = !humanPhrase.equals(STOP);
                boolean botSwitcherOFF = !humanPhrase.equals(STOP);
                boolean botSwitcherON = humanPhrase.equals(CONTINUE);
                sb.append(humanPhrase + "\n");
                if (humanPhrase.equals(OUT)) {
                    break;
                }
                while (botSwitcherON && botSwitcherOFF) {
                    String botPhrase = phrases.get((int) (Math.random() * phrases.size()));
                    sb.append(botPhrase + "\n");
                    System.out.println(botPhrase);
                }
                if (humanPhrase)

            }
            pw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            phrases = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
        e.printStackTrace();
    }
        return phrases;
    }

    private void saveLog(List<String> log){

    }

    public static void main(String[] args) {
        String botReplies = "./data/botAnswers.txt";
        String chatHistory = "./data/chatHistory.txt";
        ConsoleChat cc = new ConsoleChat(chatHistory, botReplies);
        cc.run();
    }
}
