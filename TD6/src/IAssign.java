import edu.polytechnique.xvm.asm.opcodes.*;
import java.util.Optional;
import edu.polytechnique.mjava.ast.LValue;
import edu.polytechnique.mjava.ast.TType;
import edu.polytechnique.mjava.ast.TypedExpr;
//cette dernière prenne en charge l’écriture d’un champ d’un enregistrement


public final class IAssign extends AbstractInstruction {
    public AbstractExpr           rvalue; // right-value (expression)
    public final Optional<LValue<AbstractExpr>> lvalue;

    public IAssign(Optional<String> lvalue, AbstractExpr rvalue) {
        this.lvalue = lvalue.map((x) -> new LValue<AbstractExpr>(x));
        this.rvalue = rvalue;
    }

    public IAssign(LValue<AbstractExpr> lvalue, AbstractExpr rvalue) {
        this.lvalue = Optional.of(lvalue);
        this.rvalue = rvalue;
    }
  @Override
  public void codegen(CodeGen cg) {
      if(lvalue.isPresent()) {
          if (lvalue.get().target.isPresent()) {
              TypedExpr<AbstractExpr> a = lvalue.get().target.get();
              AbstractExpr expre = a.getExpr();
              expre.codegen(cg);
              TType b = a.getType().get();
              RecordsInfo type = cg.getType((b.asNamed().getName()));
              int offsets =  type.offsets.get(lvalue.get().name);
              rvalue.codegen(cg);
              cg.pushInstruction(new PUSH(offsets));
              cg.pushInstruction(new CWRITE());
          }
          else {
              rvalue.codegen(cg);
              int offsets = cg.getOffsets(lvalue.get().name);
              cg.pushInstruction(new WFR(offsets));
          }
      }
      else {
          rvalue.codegen(cg);
      }
      //throw new UnsupportedOperationException(); // FIXME
  }
}
