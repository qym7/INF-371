public class Calculator {
    static java.util.Stack<Double> numbers;
    static java.util.LinkedList<Double> results;
    static java.util.Stack<Operator> operators;

    public Calculator(){
        numbers = new java.util.Stack<Double>();
        operators = new java.util.Stack<Operator>();
        results = new java.util.LinkedList<Double>();
    }

    public String toString(){
        String num  ="[", ope = "[";
        for (double CurNum : this.numbers) {
            num = num + CurNum+" ";
        }
        for (Operator CurOpe : this.operators) {
            ope = ope + CurOpe+" ";
        }
        num = num+"]";
        ope = ope+"]";
        return (num+'\n'+ope);
    }

    public void pushDouble(double d){
        this.numbers.push(d);
    }

    void pushOperator(Operator o){
        this.operators.push(o);
    }

//    public double getResult() throws Exception{
//        if (this.numbers == null) throw new Exception("RuntimeException");
//        double res = this.numbers.pop();
//        this.numbers.push(res);
//        return res;
//    }

    public double getResult(){
        try {
            double res = this.numbers.pop();
            this.numbers.push(res);
            return res;
        }
        catch(RuntimeException e){
     //       System.out.println("RuntimeException");
        }
        return 0;
    }

    public double getResultList(){
        try {
            return this.results.getLast();
        }
        catch(RuntimeException e){
 //           System.out.println("RuntimeException");
        }
        return 0;
    }

    public Operator getOperator(){
        try {
            Operator res = this.operators.pop();
            this.operators.push(res);
            return res;
        }
        catch(RuntimeException e){
            //System.out.println("RuntimeException");
        }
        return null;
    }

    public void executeBinOperator( Operator op ){
        double res = 0;
        double right = this.numbers.pop();
        if (this.numbers.size()!=0) {
            double left = this.numbers.pop();
            switch (op){
                case PLUS:
                    res  = right+left;
                    break;
                case MINUS:
                    res = left-right;
                    break;
                case DIV:
                    res = left/right;
                    break;
                case MULT:
                    res = right*left;
                    break;
            }
        }
        else res = right;
        this.numbers.push(res);
    }

    static private int precedence(Operator op){
        int i = -10;
        switch (op){
            case PLUS:
                i = -1;
                break;
            case MINUS:
                i = -1;
                break;
            case DIV:
                i = 1;
                break;
            case MULT:
                i = 1;
                break;
            case OPEN:
                i = -10;
        }
        return i;
    }

    public void commandOperator(Operator op){
        while (this.operators.size()>=1 && precedence(op)<=precedence(this.getOperator())) {
            executeBinOperator( this.operators.pop());
        }
        this.operators.push(op);
    }

    public void commandDouble(double d){
        this.numbers.push(d);
    }

    public void commandEqual(){
      //  System.out.println(this.toString());
        while(this.operators.size()>=1){
            executeBinOperator( this.operators.pop());
        }
        results.push(getResult());
    }

    public void commandLPar(){
        pushOperator(Operator.OPEN);
    }

    public void commandRPar(){
        while (this.getOperator()!=Operator.OPEN){
            executeBinOperator( this.operators.pop());
        }
        this.operators.pop();
    }

    public void commandInit(){
        numbers = new java.util.Stack<Double>();
        operators = new java.util.Stack<Operator>();
        results = new java.util.LinkedList<Double>();
    }

    public void commandReadMemory(int i){
        this.numbers.push(results.get(i-1));
    }

}
