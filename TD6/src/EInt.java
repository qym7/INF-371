import edu.polytechnique.xvm.asm.opcodes.*;

public final class EInt extends AbstractExpr {
  public final int value; // literal value

  public EInt() {
    this(0);
  }

  public EInt(int value) {
    this.value = value;
  }

  @Override
  public void codegen(CodeGen cg){
    cg.pushInstruction(new PUSH(this.value));
  }
}

