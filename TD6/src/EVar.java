import edu.polytechnique.xvm.asm.opcodes.*;

public final class EVar extends AbstractExpr {
  public final String name; // variable name

  public EVar(String name) {
    this.name = name;
  }


  @Override
  public void codegen(CodeGen cg) {
    int o = cg.getOffset(this.name);
    cg.pushInstruction(new RFR(o));
  }
}
