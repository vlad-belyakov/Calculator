package Vledd.com;

public class Validate {
    private final String primer;
    String textError;
    boolean existError = true;

    public Validate(String primer) {
        this.primer = primer;
    }
    public void validateRun(){
        Compariable c = new Compariable();
        if(primer.isEmpty())
            this.textError = "Введите хоть что-то";
        else if(containsChar(primer,'(') != containsChar(primer, ')')) {
            this.textError = "Кол-во открытых скобок не соотвествует кол-ву закрытых";
        }
        else if(primer.endsWith("+") || primer.endsWith("-") || primer.endsWith("*") || primer.endsWith("/") || primer.endsWith("^")){
            this.textError = "Выражение имеет лишний оператор";
        }
        else {
            this.existError = false;
        }
    }

    private boolean checkInts(String primer) {
        return (containsChar(primer, '0')
                + containsChar(primer, '1')
                + containsChar(primer, '2')
                + containsChar(primer, '3')
                + containsChar(primer, '4')
                + containsChar(primer, '5')
                + containsChar(primer, '6')
                + containsChar(primer, '7')
                + containsChar(primer, '8')
                + containsChar(primer, '9') == 1);
    }

    private int containsChar(String a, char symb){
        int count = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) == symb){
                count++;
            }
        }
        return count;
    }
}
