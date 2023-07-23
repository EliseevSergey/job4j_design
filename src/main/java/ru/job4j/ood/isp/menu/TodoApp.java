package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    public static final Integer ADD_IN_ROOT = 1;
    public static final Integer ADD_FOR_PARENT = 2;
    public static final Integer ACTION = 3;
    public static final Integer SHOW_MENU_TREE = 4;
    public static final Integer EXIT = 5;

    public static final ActionDefault DEFAULT_ACTION =  new ActionDefault();

    public static final String UI_MENU = """
                Select-1 > ADD IN ROOT. 1st LEVEL.
                Select-2 > ADD FOR PARENT.
                Select-3 > ACTION.
                Select-4 > SHOW MENU TREE.
                Select-5 > QUIT.
                """;

    public static final String SET_IN_ROOT = "INPUT 1ST LEVEL ELEMENT NAME: ";
    public static final String FOR_PARENT = "INPUT PARENT ELEMENT NAME: ";
    public static final String FOR_CHILD = "INPUT CHILD ELEMENT NAME: ";
    public static final String ACT = "SEE ACTION: ";
    public static final String SHOW = "SEE FULL TREE: ";
    public static final String END = "EXIT.";

    private static void start(Scanner scanner) {
        SimpleMenu menu = new SimpleMenu();

        boolean run = true;
        while (run) {
            System.out.println(UI_MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());

            if (ADD_IN_ROOT == userChoice) {
                System.out.println(SET_IN_ROOT);
                String rootElmName = scanner.nextLine();
                menu.add(Menu.ROOT, rootElmName, DEFAULT_ACTION);
            }

            if (ADD_FOR_PARENT == userChoice) {
                System.out.println(FOR_PARENT);
                String parent = scanner.nextLine();
                System.out.println(FOR_CHILD);
                String child = scanner.nextLine();
                menu.add(parent, child, DEFAULT_ACTION);
            }

            if (ACTION == userChoice) {
                System.out.println(ACT);
                DEFAULT_ACTION.delegate();
            }

            if (SHOW_MENU_TREE == userChoice) {
                System.out.println(SHOW);
                Printer printer = new Printer();
                printer.print(menu);
            }

            if (EXIT == userChoice) {
                run = false;
                System.out.println(END);
            }
        }
    }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            start(sc);
        }
}
