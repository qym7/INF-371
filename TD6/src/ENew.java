import edu.polytechnique.mjava.ast.TTypeDef;
import edu.polytechnique.xvm.asm.opcodes.*;

@SuppressWarnings("unused")
public final class ENew extends AbstractExpr {
  private String name; // Record type (name)

  public ENew(String name) {
    this.name = name;
  }

  @Override
  public void codegen(CodeGen cg) {
    RecordsInfo r = cg.getTypes().get(this.name);
    cg.pushInstruction(new ALLOC());
  }
}
