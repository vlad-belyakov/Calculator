package Vledd.com;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    private ArrayList<String> list;
    ArrayList<String> operatorz = new ArrayList<String>()
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
        /*add("[");
        add("]");
        add("(");
        add(")");*/
        add("-");
    }};
    ArrayList<String> operatorp = new ArrayList<String>()
    {{
        add("[");
        //add("]");
        add("(");
        //add(")");
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
        for (int i = 0; (i < this.list.size() - 1) && (this.list.size() > 1); i++) {
            if ((i == 0) && (this.list.get(i).equals("-"))) {
                this.list.set(i + 1, convertFromIntToSt(-1 * Integer.parseInt(this.list.get(i + 1))));
                this.list.remove(i);
            }
            else if((comparisonOperators(this.list.get(i), operatorp)) &&(this.list.get(i + 1).equals("-"))){
                //var p = this.list.get(i + 1);
                //var ii = Integer.parseInt(this.list.get(i + 1));
                this.list.set(i + 1, "-1");
                this.list.add(i + 2, "*");
            }
            else if((this.list.get(i).equals("-")) && (this.list.get(i + 1).equals("-"))){
                this.list.set(i + 1, "+");
                this.list.remove(i);
                i--;
            }
            else if((i > 0) && comparisonOperators(this.list.get(i), operatorz) && (this.list.get(i + 1).equals("-"))){
                this.list.set(i + 1, "-1");
                this.list.add(i + 2, "*");
            }
        }
        return this.list;
    }

    private boolean comparisonOperators(String b, ArrayList<String> list){
        boolean check = false;
        for(int i = 0; i < list.size() - 1; i++){
            if(b.equals(list.get(i))){
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
    //double value = 34.766674;
    //DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
    //String result = decimalFormat.format(value);
    //System.out.print(result);
    ////34,767
    private double sinus(double a, double b){
        var res =  convertFromFloatToDub(convertFromDubToFloat(Math.sin(Math.toRadians(b))));
        System.out.println("sin" + b + " = " + res);
        return res;
    }

    private double cosinus(double a, double b){
        var res =  convertFromFloatToDub(convertFromDubToFloat(Math.cos(Math.toRadians(b))));
        System.out.println("cos" + b + " = " + res);
        return res;
    }

    private double tangens(double a, double b){
        var res =  convertFromFloatToDub(convertFromDubToFloat(Math.tan(Math.toRadians(b))));
        System.out.println("tg" + b + " = " + res);
        return res;
    }

    private double cotangens(double a, double b){
        var res =  convertFromFloatToDub(convertFromDubToFloat(1 / Math.tan(Math.toRadians(b))));
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

    private double convertFromStToDub(String ch) {
        return Double.parseDouble(ch);
    }
    private int converFromStToInt(String ch){
        return Integer.valueOf(ch);
    }
    private String convertFromIntToSt(int ch){
        return String.valueOf(ch);
    }
    private String convertFromDubToSt(double ch) {
        return String.valueOf(ch);
    }
    private float convertFromDubToFloat(double ch){
        var s = String.valueOf(ch);
        float f;
        return  f = Float.parseFloat(s);
    }
    private double convertFromFloatToDub(float ch){
        var s = String.valueOf(ch);
        double d;
        return d = Double.parseDouble(s);
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
        a = a.replace("[", "[ ");
        a = a.replace("]", " ]");
        return a;
    }

    private boolean checkOperators(String a, ArrayList<String> list){
        return list.contains(a);
    }

    private Result run(ArrayList<String> list)  {
        while(checkOperators("sin", list) || checkOperators("cos", list) || checkOperators("tg", list) || checkOperators("ctg", list)) {
            for (int index=0; index < list.size(); index++) {
                if (list.get(index).equals("sin")) {
                    var result = operator(0, "sin", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
                else if (list.get(index).equals("cos")) {
                    var result = operator(0, "cos", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
                else if (list.get(index).equals("tg")) {
                    var result = operator(0, "tg", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
                else if (list.get(index).equals("ctg")) {
                    var result = operator(0, "ctg", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
            }
        }
        while(checkOperators("^", list) || checkOperators("root", list)) {
            for (int index=0; index < list.size(); index++) {
                if (list.get(index).equals("^") && index != 0) {
                    var result = operator(convertFromStToDub(list.get(index-1)), "^", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
                else if (list.get(index).equals("root") && index != 0) {
                    var result = operator(convertFromStToDub(list.get(index-1)), "root", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
            }
        }
        while(checkOperators("*", list) || checkOperators("/", list)) {
            for (int index=0; index < list.size(); index++) {
                if (list.get(index).equals("*") && index != 0) {
                    var result = operator(convertFromStToDub(list.get(index-1)), "*", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
                else if (list.get(index).equals("/") && index != 0) {
                    var result = operator(convertFromStToDub(list.get(index-1)), "/", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
            }
        }
        while(checkOperators("+", list) || checkOperators("-", list)) {
            for (int index=0; index < list.size(); index++) {
                if (list.get(index).equals("+") && index != 0) {
                    var result = operator(convertFromStToDub(list.get(index-1)), "+", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
                else if (list.get(index).equals("-") && index != 0) {
                    var result = operator(convertFromStToDub(list.get(index-1)), "-", convertFromStToDub(list.get(index+1)));
                    return new Result(convertFromDubToSt(result), index);
                }
            }
        }
        return null;
    }

    public Calculator calc() {
        while (this.list.size() > 1) {
            Result res = new Result("0", 0);
            if (existAbs(list)) {
                var cut = getCuttedAbsList();
                while (cut.list.size() > 1) {
                    res = this.run(cut.list);
                    changeResult(res, cut.list);
                }
                changeResultAbs(res, cut);
            } else if (existSkobka(list)) {
                var cut = getCuttedList();
                while (cut.list.size() > 1) {
                    res = this.run(cut.list);
                    changeResult(res, cut.list);
                }
                changeResultBr(res, cut);
            } else {
                res = this.run(this.list);
                changeResult(res, this.list);
            }
            System.out.println(this.list);
        }
        return this;
    }
    /*public Calculator calc2() {
        while (this.list.size() > 1) {
            if (existSkobka(list)){
                Result res = new Result("0", 0);
                var cut = getCuttedAbsList();
                res = this.run(cut.list);
                while(cut.list.size() > 1) {
                    if (existTrig(cut.list)) {
                        changeResult(res, cut.list);
                    } else if (existAbs(cut.list)) {
                        changeResultAbs(res, cut);
                    } else {
                        changeResult(res, cut.list);
                    }
                }
                changeResultBr(res, cut);
            }
             else if (existAbs(list)) {
                var cut = getCuttedList();
                Result res = new Result("0", 0);
                while (cut.list.size() > 1) {
                    if (existTrig(cut.list)) {
                        run(cut.
                        changeResult(res, cut.list);
                    } else if (existSkobka(cut.list)) {
                        changeResultBr(res, cut);
                    } else {
                        changeResult(res, cut.list);
                    }
                }
                changeResultAbs(res, cut);
            } else {
                 Result res = new Result("0", 0);
                res = this.run(this.list);
                changeResult(res, this.list);
            }
        }
        return this;
    }*/

    private boolean existSkobka(ArrayList<String> list) {
        return list.contains("(") && list.contains(")");
    }

    private boolean existAbs(ArrayList<String> list){
        return list.contains("[") && list.contains("]");
    }

    private boolean existTrig(ArrayList<String> list){
        return list.contains("sin") || list.contains("cos") || list.contains("tg") || list.contains("ctg");
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

    private Brackets absBrackets() {
        int opAbsbracket = 0;
        int clAbsbracket = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals("[")){
                opAbsbracket = i;
            }
            else if(list.get(i).equals("]")){
                clAbsbracket = i;
                break;
            }
        }
        return new Brackets(opAbsbracket,clAbsbracket);
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

    private Brackets getCuttedAbsList(){
        var nlist = new ArrayList<String>();
        var br = absBrackets();
        for(int i = br.opbrackets + 1; i < br.clbrackets ; i++) {
            nlist.add(list.get(i));
        }
        br.list = nlist;
        return br;
    }

    private void changeResult(Result res, ArrayList<String> list) {
        if (existTrig(list)) {
            list.set(res.Index, res.Chislo);
            list.remove(res.Index+1);
        } else {
            list.set(res.Index, res.Chislo);
            list.remove(res.Index+1);
            list.remove(res.Index-1);
        }
    }

    private void changeResultBr(Result res, Brackets br) {
        list.set(br.opbrackets, res.Chislo);
        if (br.clbrackets >= br.opbrackets + 1) {
            list.subList(br.opbrackets+1, br.clbrackets+1).clear();
        }
    }

    private void changeResultAbs(Result res, Brackets br) {
        var i = res.Chislo.replace("-", "");
        list.set(br.opbrackets, i);
        if (br.clbrackets >= br.opbrackets + 1) {
            list.subList(br.opbrackets+1, br.clbrackets+1).clear();
        }
    }

    public String getResult(){
            return this.list.get(0).replace(".0", "");
    }

    private Validate validate(String primer){
        var va = new Validate(primer);
        va.validateRun();
        return va;
    }

}
