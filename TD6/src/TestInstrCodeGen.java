import java.util.stream.*;

import edu.polytechnique.mjava.parser.*;
import edu.polytechnique.mjava.toplevel.*;
import edu.polytechnique.mjava.toplevel.MJavaTop.*;
import edu.polytechnique.mjava.typing.*;
import edu.polytechnique.mjava.typing.exn.*;
import edu.polytechnique.xvm.asm.opcodes.*;

public class TestInstrCodeGen {
  // Modify this instruction at will
  public final static String DEFAULT =
    "< x = 2; y = 3; >" + // Local variables declaration
    "if (x < y) x = x + 1; while (x < y) { x = x + 1; y = y - 1; }";
  
  public static void main(String[] args)
      throws MJavaParseError, MJavaTypingError
  {
    // Get input expression
    String input = args.length == 0 ? DEFAULT : String.join(" ", args);
    
    // Parse & type input instruction
    InstrWithCtxt<AbstractExpr, AbstractInstruction> is;
    is = ParseAndType.parseAndTypeInstr(input);

    // Create code generator
    CodeGen cg = new CodeGen();

    // Push local variables initialization
    // (in order of declaration from left-to-right)
    int offset = 0;
    
    for (EVarInit vinit : is.getCtxt()) {
      cg.pushLocalVariable(vinit.getName(), offset++);
      cg.pushInstruction(new PUSH(vinit.getValue()));
    }

    // Generate code for instruction `i'
    is.getInstruction().codegen(cg);;
    cg.pushInstruction(new STOP());

    // Display generated XVM code
    System.out.print(cg.toString());
  }
}
