public class test {
    public static void main(String[] args){
//        Calculator c = new Calculator();
//        c.commandDouble(2);
//        c.commandOperator(Operator.MULT);
//        c.commandDouble(3);
//        c.commandOperator(Operator.PLUS);
//        c.commandDouble(5);
//        c.commandEqual();
//        double r = c.getResultList();
//        System.out.println(r);
//        System.out.println((double)Character.getNumericValue('1'));
        Tokenizer t = new Tokenizer();
        t.readString("888-1=");
        System.out.println(t.calc.getResult());
    }
}
