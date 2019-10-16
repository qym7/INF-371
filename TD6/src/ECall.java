import edu.polytechnique.xvm.asm.opcodes.*;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public final class ECall extends AbstractExpr {
  public final String               name; // procedure name
  public final Vector<AbstractExpr> args; // arguments

  public ECall(String name, Vector<AbstractExpr> args) {
    this.name = name;
    this.args = args;
  }

  public static String labelOfProcName(String name) {
    return String.format("P%s", name);
  }

  @Override
  public void codegen(CodeGen cg) {
    for (AbstractExpr a: this.args) a.codegen(cg);
    cg.pushInstruction(new GSB(ECall.labelOfProcName(name)));
  }
}
