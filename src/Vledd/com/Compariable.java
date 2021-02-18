package Vledd.com;

import java.util.ArrayList;

public class Compariable {
    public Compariable(){
    }
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
        add("root");
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

    //Есть ли определенные символы в строке
    protected boolean include(String b, ArrayList<String> list){
        boolean check = false;
        for(int i = 0; i < list.size() - 1; i++){
            if(b.equals(list.get(i))){
                check = true;
                break;
            }
        }
        return check;
    }

    protected boolean include(String b, String a, ArrayList<String> list){
        boolean check = false;
        for(int i = 0; i < list.size() - 1; i++){
            if(b.equals(a) && b.equals(list.get(i))){
                check = true;
                break;
            }
        }
        return check;
    }

    protected boolean includeDigits(String b){
        boolean check = false;
        for(int i = 0; i < digits.size() - 1; i++){
            if(b.endsWith(digits.get(i))){
                check = true;
                break;
            }
        }
        return check;
    }

    protected boolean include(String b, ArrayList<String> list, ArrayList<String> list2){
        boolean check = false;
        boolean check2 = false;
        for(int i = 0; i < list.size() - 1; i++){
            if(b.equals(list.get(i))){
                check = true;
                break;
            }
        }
        for(int i = 0; i < list2.size() - 1; i++){
            if(b.equals(list2.get(i))){
                check2 = true;
                break;
            }
        }
        return check && check2 == true;
    }
}
