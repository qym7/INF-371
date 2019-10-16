import edu.polytechnique.xvm.asm.opcodes.*;

@SuppressWarnings("unused")
public final class EGet extends AbstractExpr {
  private final AbstractExpr target; // Record expression
  private final String       tgType; // Record type (name)
  private final String       field ; // Field name
//On lit un champ dans une expression de type enregistrement.
  public EGet(AbstractExpr target, String tgType, String field)
  {
    this.target = target;
    this.tgType = tgType;
    this.field = field;
  }

  @Override
  public void codegen(CodeGen cg) {
    RecordsInfo info = cg.getTypes().get(tgType);
    int off = info.offsets.get(field);
    target.codegen(cg);
    cg.pushInstruction(new PUSH(off));
    cg.pushInstruction(new CREAD());
  }
}
