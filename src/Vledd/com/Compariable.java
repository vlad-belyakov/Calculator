package Vledd.com;

import java.util.ArrayList;

public class Compariable {
    public Compariable(){
    }
    protected final ArrayList<String> exitCommands = new ArrayList<>(){{
        add("выход");
        add("exit");
        add("конец");
        add("end");
        add("оаоаоа");
    }};
    protected final ArrayList<String> digits = new ArrayList<>(){{
        add("0");
        add("1");
        add("2");
        add("3");
        add("4");
        add("5");
        add("6");
        add("7");
        add("8");
        add("9");
    }};
    protected final ArrayList<String> allOperators = new ArrayList<>()
    {{
        add("√");
        add("^");
        add("*");
        add("+");
        add("sin");
        add("cos");
        add("tg");
        add("ctg");
        add("/");
        add("-");
    }};
    protected final ArrayList<String> bracketsOperators = new ArrayList<>()
    {{
        add("[");
        //add("]");
        add("(");
        //add(")");
    }};
    protected final ArrayList<String> OpBracketsOperators = new ArrayList<>()
    {{
        add("[");
        add("(");
    }};
    protected final ArrayList<String> ClBracketsOperators = new ArrayList<>()
    {{
        add("]");
        add(")");
    }};
    //Есть ли определенные символы в строке
    protected boolean ravno(String b, ArrayList<String> list){
        boolean check = false;
        for (String s : list) {
            if (b.equalsIgnoreCase(s)) {
                check = true;
                break;
            }
        }
        return check;
    }

    protected boolean ravno(String b, String a, ArrayList<String> list){
        boolean check = false;
        for (String s : list) {
            if (b.equalsIgnoreCase(a) && b.equalsIgnoreCase(s)) {
                check = true;
                break;
            }
        }
        return check;
    }

    protected boolean includeDigits(String b){
        boolean check = false;
        for (String digit : digits) {
            if (b.endsWith(digit)) {
                check = true;
                break;
            }
        }
        return check;
    }

    protected boolean ravno(String b, ArrayList<String> list, ArrayList<String> list2){
        boolean check = false;
        boolean check2 = false;
        for (String s : list) {
            if (b.equalsIgnoreCase(s)) {
                check = true;
                break;
            }
        }
        for (String s : list2) {
            if (b.equalsIgnoreCase(s)) {
                check2 = true;
                break;
            }
        }
        return check && check2;
    }
}
