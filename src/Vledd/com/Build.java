package Vledd.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Build {
    public static void main(String[] args) throws Exception {

        System.out.println("Список операторов, готовых к использованию в данной версии программы: ");
        Compariable c = new Compariable();
        for(int i = 0; i < c.allOperators.size(); i++){
            if(i < c.allOperators.size() - 1) {
                System.out.print(c.allOperators.get(i) + "  |  ");
            }
            else{
                System.out.println(c.allOperators.get(i) + "  |  ");
            }
        }

        System.out.println("Введите ваше выражение");

        boolean True = true;
        loop:
        while(True) {
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
            String line = sc.readLine();

            if (!c.ravno(line, c.exitCommands)) {
                var calc = new Calculator(line);

                System.out.println("Ответ : " + calc.calc(calc.list).getResult());
                continue loop;
            } else {
                True = false;
            }

        }
        System.out.println("Прекращение работы калькулятора");
    }
}
