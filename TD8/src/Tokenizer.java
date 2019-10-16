public class Tokenizer {
    static boolean isStart;
    static boolean isIntNum;
    static double num;
    static Calculator calc;
    static boolean debug;
    static int decimalDigits;
    static boolean isNeg;
    static boolean isMinUnary;
    static boolean isMemory;
    static boolean haveNumber;
    static int Memory;


    public Tokenizer(){
        calc = new Calculator();
        num = 0;
        isStart = true;
        isIntNum = true;
        debug = false;
        decimalDigits = 0;
        isNeg = false;
        isMinUnary = false;
        isMemory = false;
        haveNumber = false;
        Memory = 0;
    }

    public void readChar(char c){
        if (isMemory&&!Character.isDigit(c)){
            calc.commandReadMemory(Memory);
            isMemory = false;
        }
        if (!isMemory&&Character.isDigit(c)){
            haveNumber = true;
            int i = Character.getNumericValue(c);
            if (isIntNum){
                num = num*10 + i;
            }
            else{
                num = num + i*Math.pow(10,decimalDigits);
                decimalDigits--;
            }
            isStart = false;
        }
        else if (isMemory&&Character.isDigit(c)){
            int i = Character.getNumericValue(c);
            Memory = Memory*10+i;
      //      System.out.println("memory"+calc.toString());
        }
        else if (c=='$'){
            isMemory = true;

        }
        else if (c=='.') {

            isIntNum = false;
            decimalDigits = -1;
            isStart = false;
        }
        else if(c=='C'){
            calc.commandInit();
            num = 0;
            isStart = true;
            isIntNum = true;
            debug = false;
            decimalDigits = 0;
            isNeg = false;
            isMinUnary = false;
            isMemory = false;
            haveNumber  = false;
        }
        else if(c=='-'&&isStart){
            isMinUnary = true;
            isNeg = true;
            isStart = false;
        }
        else{
            switch (c){
                case '=':
                    calc.commandEqual();
                    haveNumber = false;
                    break;
                case '+':
                 //   System.out.println("PLUS");
                    calc.commandOperator(Operator.PLUS);
                    break;
                case '-':
                    calc.commandOperator(Operator.MINUS);
                    break;
                case '*':
                    calc.commandOperator(Operator.MULT);
                    break;
                case '/':
                    calc.commandOperator(Operator.DIV);
                    break;
                case '(':
                    calc.commandLPar();
                    break;
                case ')':
                    calc.commandRPar();
                    break;
            }
        }
    }

    void readString(String s) {
        char[] operator = {'+','-','*','/'};
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.charAt(i)=='('||((c=='*'||c=='/'||c=='-'||c=='+')&&(i>=1&&(s.charAt(i-1)=='-'||s.charAt(i-1)=='+'
            ||s.charAt(i-1)=='*'||s.charAt(i-1)=='/')))||c=='='){
                isStart = true;
            }
            if ((s.charAt(i)!='-'||!isStart)&&s.charAt(i)!='.'&&s.charAt(i)!='$'&&s.charAt(i)!='('&&!Character.isDigit(s.charAt(i))&&(i>=1&&s.charAt(i-1)!=')')) {
                if (isNeg) num = -1*num;
               if (haveNumber) calc.commandDouble(num);
                num = 0;
                decimalDigits = 0;
                isIntNum = true;
            }
            readChar(s.charAt(i));
        }
    }

}
