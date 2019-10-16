import edu.polytechnique.xvm.asm.opcodes.*;

public final class EBool extends AbstractExpr {
  public final boolean value; // Literal value

  public EBool() {
    this(false);
  }

  public EBool(boolean value) {
    this.value = value;
  }

  @Override
  public void codegen(CodeGen cg) {
    int i;
    if (this.value) i = 1;
    else i=0;
    cg.pushInstruction(new PUSH(i));
  }
}
