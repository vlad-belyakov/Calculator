package Vledd.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Build {
    public static void main(String[] args) throws Exception {

        System.out.println("Введите ваше выражение");
        System.out.println("Список операторов, готовых к использованию в данной версии программы: ");
        Compariable c = new Compariable();
        for(int i = 0; i < c.allOperators.size() - 1; i++){
            if(i < c.allOperators.size() - 2) {
                System.out.print(c.allOperators.get(i) + " | ");
            }
            else{
                System.out.println(c.allOperators.get(i) + " | ");
            }
        }
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String line = sc.readLine();

        var calc = new Calculator(line);

        System.out.println("Ответ : " + calc.calc(calc.list).getResult());
    }
}
