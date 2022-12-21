package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
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
        Scanner in = new Scanner(System.in, Charset.forName("UTF-8"));
        List<String> logan = new ArrayList<>();
            boolean botSwitcher = true;
            String humanPhrase = in.nextLine();
            logan.add(humanPhrase);
            while (!humanPhrase.equals(OUT)) {
                if (botSwitcher && !humanPhrase.equals(STOP) || humanPhrase.equals(CONTINUE)) {
                    String botPhrase = phrases.get((int) (Math.random() * phrases.size()));
                    System.out.println(botPhrase);
                    logan.add(botPhrase);
                    botSwitcher = true;
                }
                if (humanPhrase.equals(STOP)) {
                    botSwitcher = false;
                }
                humanPhrase = in.nextLine();
                logan.add(humanPhrase);
            }
            saveLog(logan);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("UTF-8")))) {
            phrases = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
        e.printStackTrace();
    }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(botAnswers, Charset.forName("UTF-8")))) {
            var sb = new StringBuilder();
            log.stream().
                    map(s -> s + "\n").
                    forEach(sb::append);
            pw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String botReplies = "./data/botAnswers.txt";
        String chatHistory = "./data/chatHistory.txt";
        ConsoleChat cc = new ConsoleChat(botReplies, chatHistory);
        cc.run();
    }
}
