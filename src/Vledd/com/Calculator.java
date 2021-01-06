package Vledd.com;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Calculator {
    private ArrayList<String> list;
    ArrayList<String> operatorz = new ArrayList<String>()
    {{
        add("root");
        add("^");
        add("*");
        add("+");
        add("-");
        add("sin");
        add("cos");
        add("tg");
        add("ctg");
        add("/");
    }};

    public Calculator(String primer) throws Exception {
        var v = validate(primer);
        if (v.existError) {
            throw new Exception(v.textError);
        }
        var prim = primer.replace(" ", "");
        prim = prim.toLowerCase();
        var line = spaces(prim);
        String[] linee = line.split(" ");

        this.list = new ArrayList<>(Arrays.asList(linee));
        deletingVoids();
        workingOnMinuses();
    }

    private ArrayList<String> deletingVoids() {
        for(int i = 0; i < this.list.size(); i++){
            if(this.list.get(i).equals("")){
                this.list.remove(i);
                i--;
            }
        }
        return this.list;
    }

    private ArrayList<String> workingOnMinuses() {
        for (int i = 0; (i < this.list.size()) && (this.list.size() > 1); i++) {
            if ((i == 0) && (this.list.get(i).equals("-"))) {
                this.list.set(i + 1, "" + (-1 * Integer.parseInt(this.list.get(i + 1))));
                this.list.remove(i);
            }
            else if((i > 0) && (this.list.get(i).equals("-")) && (this.list.get(i + 1).equals("-"))){
                this.list.set(i + 1, "+");
                this.list.remove(i);
                i--;
            }
            else if((i > 0) && comparisonOperators(this.list.get(i)) && (this.list.get(i + 1).equals("-"))){
                this.list.set(i + 1, "-1");
                this.list.add(i + 2, "*");
            }
        }
        return this.list;
    }

    private boolean comparisonOperators(String b){
        boolean check = false;
        for(int i = 0; i < operatorz.size() - 1; i++){
            if(b.equals(operatorz.get(i))){
                check = true;
                break;
            }
        }
        return check;
    }

    //Умножение
    private double multiplication(double a,double b){
        var res = a * b;
        System.out.println( a + " * " + b + " = " + res);
        return res;
    }
    //Деление
    private double dividing(double a,double b){
        var res = a / b;
        System.out.println( a + " / " + b + " = " + res);
        return res;
    }
    //Вычитание
    private double subtraction(double a,double b){
        var res = a - b;
        System.out.println( a + " - " + b + " = " + res);
        return res;
    }
    //Сложение
    private double summ(double a,double b){
        var res = a + b;
        System.out.println( a + " + " + b + " = " + res);
        return res;
    }
    //Степень
    private double level(double a, double b){
        var res = Math.pow(a, b);
        System.out.println( a + "^" + b + " = " + res);
        return res;
    }
    //Корень
    private double root(double a, double b){
        var res = Math.pow(a, 1/b);
        System.out.println( b + "√" + a + " = " + res);
        return res;
    }
    //Тригонометрические функции
    private double sinus(double a, double b){
        var res = Math.sin(b);
        System.out.println("sin" + b + " = " + res);
        return res;
    }

    private double cosinus(double a, double b){
        var res = Math.cos(b);
        System.out.println("cos" + b + " = " + res);
        return res;
    }

    private double tangens(double a, double b){
        var res = Math.tan(b);
        System.out.println("tg" + b + " = " + res);
        return res;
    }

    private double cotangens(double a, double b){
        var res = 1 / Math.tan(b);
        System.out.println("ctg" + b + " = " + res);
        return res;
    }
    //операции
    private double operator(double a, String c, double b) {
        if (c.equals("*")) {
            return multiplication(a, b);
        } else if (c.equals("/")) {
            return dividing(a, b);
        } else if (c.equals("+")) {
            return summ(a, b);
        } else if (c.equals("-")) {
            return subtraction(a, b);
        } else if (c.equals("^")) {
            return level(a, b);
        } else if (c.equals("root")) {
            return root(a, b);
        } else if (c.equals("sin")) {
            return sinus(a, b);
        } else if (c.equals("cos")) {
            return cosinus(a, b);
        } else if (c.equals("tg")) {
            return tangens(a, b);
        } else if(c.equals("ctg")) {
            return cotangens(a, b);
        } else {
            return 0;
        }
    }

    private double convertToDub(String ch) {
        return Double.parseDouble(ch);
    }

    private String convertToSt(double ch) {
        return String.valueOf(ch);
    }

    private String spaces(String a) {
        a = a.replace("+", " + ");
        a = a.replace("-", " - ");
        a = a.replace("/", " / ");
        a = a.replace("*", " * ");
        a = a.replace("^", " ^ ");
        a = a.replace("root", " root ");
        a = a.replace("(", "( ");
        a = a.replace(")", " )");
        a = a.replace("=", " =");
        a = a.replace("sin", " sin ");
        a = a.replace("cos", " cos ");
        a = a.replace("tg", " tg ");
        a = a.replace("ctg", " ctg ");
        return a;
    }

    private boolean checkOperators(String a, ArrayList<String> list){
        return list.contains(a);
    }

    private Brackets brackets() {
        int opbracket = 0;
        int clbracket = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals("(")){
                opbracket = i;
            }
            else if(list.get(i).equals(")")){
                clbracket = i;
                break;
            }
        }
        return new Brackets(opbracket,clbracket);
    }

    private boolean existAbs(){
        return this.list.contains("[") || this.list.contains("]");
    }

    private Brackets getCuttedList(){
        var nlist = new ArrayList<String>();
        var br = brackets();
        for(int i = br.opbrackets + 1; i < br.clbrackets; i++) {
            nlist.add(list.get(i));
        }
        br.list = nlist;
        return br;
    }

    private Result run(ArrayList<String> list)  {
        while(checkOperators("sin", list) || checkOperators("cos", list) || checkOperators("tg", list) || checkOperators("ctg", list)) {
            for (int index=0; index < list.size(); index++) {
                if (list.get(index).equals("sin") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "sin", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
                else if (list.get(index).equals("cos") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "cos", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
                else if (list.get(index).equals("tg") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "tg", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
                else if (list.get(index).equals("ctg") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "ctg", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
            }
        }
        while(checkOperators("^", list) || checkOperators("root", list)) {
            for (int index=0; index < list.size(); index++) {
                if (list.get(index).equals("^") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "^", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
                else if (list.get(index).equals("root") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "root", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
            }
        }
        while(checkOperators("*", list) || checkOperators("/", list)) {
            for (int index=0; index < list.size(); index++) {
                if (list.get(index).equals("*") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "*", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
                else if (list.get(index).equals("/") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "/", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
            }
        }
        while(checkOperators("+", list) || checkOperators("-", list)) {
            for (int index=0; index < list.size(); index++) {
                if (list.get(index).equals("+") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "+", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
                else if (list.get(index).equals("-") && index != 0) {
                    var result = operator(convertToDub(list.get(index-1)), "-", convertToDub(list.get(index+1)));
                    return new Result(convertToSt(result), index);
                }
            }
        }
        return null;
    }

    public Calculator calc() {
        /*while (this.list.size() > 1) {
            if (existAbs()) {
                var cut = getCuttedList();
                Result res = new Result("0", 0);
                while (cut.list.size() > 1) {
                    res = this.run(cut.list);
                    changeResult(res, cut.list);
                }
                changeResultBr(res, cut);
            } else {
                var res = this.run(this.list);
                changeResult(res, this.list);
            }
        }*/
        while (this.list.size() > 1) {
            if (existSkobka()) {
                var cut = getCuttedList();
                Result res = new Result("0", 0);
                while (cut.list.size() > 1) {
                    res = this.run(cut.list);
                    changeResult(res, cut.list);
                }
                changeResultBr(res, cut);
            } else {
                var res = this.run(this.list);
                changeResult(res, this.list);
            }
        }
        return this;
    }

    private boolean existSkobka() {
        return list.contains("(") || list.contains(")");
    }

    private void changeResult(Result res, ArrayList<String> list) {
        list.set(res.Index, res.Chislo);
        list.remove(res.Index+1);
        list.remove(res.Index-1);
    }

    private void changeResultBr(Result res, Brackets br) {
        list.set(br.opbrackets, res.Chislo);
        if (br.clbrackets >= br.opbrackets + 1) {
            list.subList(br.opbrackets+1, br.clbrackets+1).clear();
        }
    }

    public String getResult(){
            return this.list.get(0).replace(".0", "");
    }

    private Validate validate(String primer){
        var va = new Validate(primer);
        va.run();
        return va;
    }

}
