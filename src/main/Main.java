package main;

import java.util.Scanner;
import main.ui.TextUI;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        TextUI ui = new TextUI(scanner);
        ui.start();

    }
}
