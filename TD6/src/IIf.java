import edu.polytechnique.xvm.asm.opcodes.*;

public final class IIf extends AbstractInstruction {
  public final AbstractExpr        condition; // condition (<> 0 => true)
  public final AbstractInstruction iftrue   ; // if "true  (<> 0)" branch
  public final AbstractInstruction iffalse  ; // if "false (== 0)" branch

  public IIf(AbstractExpr condition,
             AbstractInstruction iftrue,
             AbstractInstruction iffalse)
  {
    this.condition = condition;
    this.iftrue = iftrue;
    this.iffalse = iffalse;
  }

  @Override
  public void codegen(CodeGen cg) {
    String lbl2 = CodeGen.generateLabel();
    cg.pushLabel(lbl2);
    this.iffalse.codegen(cg);
    this.condition.codegen(cg);
    cg.pushInstruction(new GTZ(lbl2));
    this.iffalse.codegen(cg);
    this.iftrue.codegen(cg);
  }
}
