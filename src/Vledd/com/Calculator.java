package Vledd.com;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    public ArrayList<String> list;
    ArrayList<String> operatorz = new ArrayList<>()
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
    ArrayList<String> operatorp = new ArrayList<>()
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
    //Добавление пробелов в выражние для его дальнейшего раздробления в массив для работы
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
    //Ошибки
    Validate validate(String primer){
        var va = new Validate(primer);
        va.validateRun();
        return va;
    }
    //Удаление пустот в выражении
    private void deletingVoids() {
        for(int i = 0; i < this.list.size(); i++){
            if(this.list.get(i).equals("")){
                this.list.remove(i);
                i--;
            }
        }
    }
    //Работа с минусами
    private void workingOnMinuses() {
        for (int i = 0; (i < this.list.size() - 1) && (this.list.size() > 1); i++) {
            if ((i == 0) && (this.list.get(i).equals("-"))) {
                this.list.set(i + 1, convertFromIntToSt(-1 * Integer.parseInt(this.list.get(i + 1))));
                this.list.remove(i);
            }
            else if((comparisonOperators(this.list.get(i), operatorp)) &&(this.list.get(i + 1).equals("-"))){
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

    }
    //Есть ли определенные символы в строке
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

    //Вычисления
    //Вычисление умножения
    private double multiplication(double a,double b){
        var res = a * b;
        System.out.println( a + " * " + b + " = " + res);
        return res;
    }
    //Вычисление деления
    private double dividing(double a,double b){
        var res = a / b;
        System.out.println( a + " / " + b + " = " + res);
        return res;
    }
    //Вычисление разности
    private double subtraction(double a,double b){
        var res = a - b;
        System.out.println( a + " - " + b + " = " + res);
        return res;
    }
    //Вычисление суммы
    private double summ(double a,double b){
        var res = a + b;
        System.out.println( a + " + " + b + " = " + res);
        return res;
    }
    //Вычисление степени
    private double level(double a, double b){
        var res = Math.pow(a, b);
        System.out.println( a + "^" + b + " = " + res);
        return res;
    }
    //Вычисление корня
    private double root(double a, double b){
        var res = Math.pow(a, 1/b);
        System.out.println( b + "√" + a + " = " + res);
        return res;
    }
    //Тригонометрические функции
    //Вычисление синуса
    private double sinus(double b){
        var res =  convertFromFloatToDub(convertFromDubToFloat(Math.sin(Math.toRadians(b))));
        System.out.println("sin" + b + " = " + res);
        return res;
    }
    //Вычисление косинуса
    private double cosinus(double b){
        var res =  convertFromFloatToDub(convertFromDubToFloat(Math.cos(Math.toRadians(b))));
        System.out.println("cos" + b + " = " + res);
        return res;
    }
    //Вычисление тангенса
    private double tangens(double b){
        var res =  convertFromFloatToDub(convertFromDubToFloat(Math.tan(Math.toRadians(b))));
        System.out.println("tg" + b + " = " + res);
        return res;
    }
    //Вычисление котангенса
    private double cotangens(double b){
        var res =  convertFromFloatToDub(convertFromDubToFloat(1 / Math.tan(Math.toRadians(b))));
        System.out.println("ctg" + b + " = " + res);
        return res;
    }

    //Отправка "мини" выражений на вычисления по операторам
    private double operator(double a, String c, double b) {
        switch (c) {
            case "*":
                return multiplication(a, b);
            case "/":
                return dividing(a, b);
            case "+":
                return summ(a, b);
            case "-":
                return subtraction(a, b);
            case "^":
                return level(a, b);
            case "root":
                return root(a, b);
            case "sin":
                return sinus(b);
            case "cos":
                return cosinus(b);
            case "tg":
                return tangens(b);
            case "ctg":
                return cotangens(b);
            default:
                return 0;
        }
    }

    //Конвертации
    //Конвертация числа из строки в тип int
    private int converFromStToInt(String ch){
        return Integer.parseInt(ch);
    }
    //Конвертация числа из типа int в строку
    private String convertFromIntToSt(int ch){
        return String.valueOf(ch);
    }
    //Конвертация числа из строкив тип double
    private double convertFromStToDub(String ch) {
        return Double.parseDouble(ch);
    }
    //Конвертация числа из типа double в cтроку
    private String convertFromDubToSt(double ch) {
        return String.valueOf(ch);
    }
    //Конвертация числа из типа double в тип float
    private float convertFromDubToFloat(double ch){
        var s = String.valueOf(ch);
        return  Float.parseFloat(s);
    }
    //Конвертация числа из типа float в тип double
    private double convertFromFloatToDub(float ch){
        var s = String.valueOf(ch);
        return Double.parseDouble(s);
    }

    //Проверка выражения на операторы
    private boolean checkOperators(String a, ArrayList<String> list){
        return list.contains(a);
    }
    //Отправка "мини" выражений, исходя из операторов
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


    //Отправка на различные проверки и действия
    public Calculator calc(ArrayList<String> list) {
        while (list.size() > 1) {
            Result res = new Result("0", 0);
            if (existAbs(list)) {
                var cut = getCuttedAbsList(list);
                while (cut.list.size() > 1) {
                    if(existSkobka(cut.list)) {
                        calc(cut.list);
                    }
                    else {
                        res = this.run(cut.list);
                        changeResult(res, cut.list);
                    }
                }
                changeResultAbs(res, cut, list);
            } else if (existSkobka(list)) {
                var cut = getCuttedList(list);
                while (cut.list.size() > 1) {
                    res = this.run(cut.list);
                    changeResult(res, cut.list);
                }
                changeResultBr(res, cut, list);
            } else {
                res = this.run(list);
                changeResult(res, list);
            }
        }
        return this;
    }



    //Проверка на скобки в выражении
    private boolean existSkobka(ArrayList<String> list) {
        return list.contains("(") && list.contains(")");
    }
    //Проверка на модули в выражении
    private boolean existAbs(ArrayList<String> list){
        return list.contains("[") && list.contains("]");
    }
    //Проверка на тригонометрические функции
    private boolean existTrig(ArrayList<String> list){
        return list.contains("sin") || list.contains("cos") || list.contains("tg") || list.contains("ctg");
    }

    //Нахождение индекса вхождения скобок в строку
    private Brackets brackets(ArrayList<String> list) {
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
    //Нахождение индекса входа модулей в строку
    private Brackets absBrackets(ArrayList<String> list) {
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

    //"мини" выражение - выражение в одно действие
    //Вырезание "мини" выражения для подсчета в скобках
    private Brackets getCuttedList(ArrayList<String> list){
        var nlist = new ArrayList<String>();
        var br = brackets(list);
        for(int i = br.opbrackets + 1; i < br.clbrackets; i++) {
            nlist.add(list.get(i));
        }
        br.list = nlist;
        return br;
    }
    //Вырезание "мини" выражения для подсчета в модуле
    private Brackets getCuttedAbsList(ArrayList<String> list){
        var nlist = new ArrayList<String>();
        var br = absBrackets(list);
        for(int i = br.opbrackets + 1; i < br.clbrackets ; i++) {
            nlist.add(list.get(i));
        }
        br.list = nlist;
        return br;
    }

    //Замена "мини" выражения на ответ
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
    //Замена "мини" выражения на ответ в скобках
    private void changeResultBr(Result res, Brackets br, ArrayList<String> list) {
        list.set(br.opbrackets, res.Chislo);
        if (br.clbrackets >= br.opbrackets + 1) {
            list.subList(br.opbrackets+1, br.clbrackets+1).clear();
        }
    }
    //Замена "мини" выражения на ответ в модуле
    private void changeResultAbs(Result res, Brackets br, ArrayList<String> list) {
        var i = res.Chislo.replace("-", "");
        list.set(br.opbrackets, i);
        if (br.clbrackets >= br.opbrackets + 1) {
            list.subList(br.opbrackets+1, br.clbrackets+1).clear();
        }
    }

    //Вывод ответа
    public String getResult(){
            return this.list.get(0).replace(".0", "");
    }
}
