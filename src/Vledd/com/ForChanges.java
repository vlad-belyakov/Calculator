package Vledd.com;

import java.util.ArrayList;
//Мусорный класс
public class ForChanges {
    ArrayList<String> list = new ArrayList<>();

    public ForChanges() {}
        private void workingOnMinuses() {
            Compariable c = new Compariable();
            for (int i = 0; (i < this.list.size() - 1) && (this.list.size() > 1); i++) {
                if (i == 0 && list.get(i).equals("-") && list.get(i + 1).equals("-")) {
                    list.remove(i);
                    list.remove(i);
                } else if (i > 0 && c.include(list.get(i), c.bracketsOperators) && list.get(i).equals("-") && list.get(i + 1).equals("-")) {
                    list.remove(i + 1);
                    list.remove(i + 1);
                    i--;
                } else if (i == 1 && list.get(0).equals("-") && list.get(1).equals("-")) {
                    list.remove(1);
                    list.remove(0);
                    i--;
                } else if (c.includeDigits(list.get(i)) && list.get(i + 1).equals("-") && list.get(i + 2).equals("-")) {
                    list.set(i + 1, "+");
                    list.remove(i + 2);
                } else if (!list.get(i).equals("-") && c.include(list.get(i), list.get(i + 1), c.allOperators)) {
                    list.remove(i);
                    i--;
                } else if (list.get(i).equals("-") && c.includeDigits(list.get(i + 1)) && !list.get(i - 1).equals("-")) {
                    //list.set(i + 1, convertFromDubToSt(-1.0 * convertFromStToDub(list.get(i + 1))));
                    list.remove(i);
                } else if (list.get(i).equals("-") && c.include(list.get(i + 1), c.bracketsOperators)) {
                    list.set(i, "-1");
                    list.add(i + 1, "*");
                }/* else if (i > 2 && c.includeDigits(list.get(i), c.digits) && list.get(i - 1).equals("-") && c.includeDigits(list.get(i - 2), c.allOperators)) {
                    list.set(i - 1, "-1");
                    list.add(i, "*");
                }*/
            }
        }


    private void workingOnMinuses2() {
        Compariable c = new Compariable();
        for (int i = 0; (i < this.list.size() - 1) && (this.list.size() > 1); i++) {
            if (list.get(i).equals("-")){
                if(i == 0 && list.get(i + 1).equals("-")){
                    list.remove(i);
                    list.remove(i);
                } else if(i == 1 && list.get(0).equals("-")){
                    list.remove(1);
                    list.remove(0);
                    i--;
                } else if(i > 0 && c.includeDigits(list.get(i - 1)) && list.get(i + 1).equals("-")){
                    list.set(i, "+");
                    list.remove(i + 1);
                }
            }
        }
    }
}
