import edu.polytechnique.mjava.ast.VarDecl;
import edu.polytechnique.xvm.asm.opcodes.*;
import java.util.Optional;
import java.util.List;

public final class IReturn extends AbstractInstruction {
  public final Optional<AbstractExpr> result; // Value to return

  public IReturn() {
    this(Optional.empty());
  }

  public IReturn(Optional<AbstractExpr> result) {
    this.result = result;
  }

  @Override
  public void codegen(CodeGen cg) {
    final List<VarDecl> locals = cg.proc.getLocals();
    cg.pushInstruction(new PXR());
    for (VarDecl v: locals) cg.pushInstruction(new POP());
  }
}
