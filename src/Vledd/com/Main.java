package Vledd.com;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите ваше выражение");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        var calc = new Calculator(line);

        System.out.println("Ответ : " + calc.calc().getResult());
    }
}
