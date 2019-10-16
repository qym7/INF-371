import java.util.List;
import java.util.*;
import edu.polytechnique.xvm.asm.opcodes.*;
import edu.polytechnique.mjava.ast.BinOp;


import edu.polytechnique.mjava.ast.Instruction;
        import edu.polytechnique.mjava.ast.TProcDef;
        import edu.polytechnique.mjava.ast.VarDecl;
        import edu.polytechnique.xvm.asm.opcodes.GSB;
        import edu.polytechnique.xvm.asm.opcodes.STOP;

public class ProgramCodeGen {
  public final CodeGen cg = new CodeGen();

  public static String labelOfProcName(String name) {
    return String.format("__%s", name);
  }

  public void codegen(TProcDef<AbstractExpr, AbstractInstruction> proc) {
    final List<VarDecl> args = proc.getArgs(); // Proc. arguments
    final List<VarDecl> locals = proc.getLocals(); // Proc. locals
    final AbstractInstruction is = proc.getBody(); // Proc. body
    final String name = labelOfProcName(proc.getName());
    cg.proc = proc;
    cg.pushLabel(name);
    int count = -locals.size();

    for(VarDecl arg: args) {
      this.cg.offsets.put(arg.getName(),count++);
    }

    count = 2;
    for (VarDecl local: locals){
      this.cg.offsets.put(local.getName(),count++);
    }

    is.codegen(cg);

    cg.pushInstruction(new PXR());
    cg.pushInstruction(new RET());
  }

  public void codegen(List<TProcDef<AbstractExpr, AbstractInstruction>> procs) {
    for (TProcDef<AbstractExpr, AbstractInstruction> proc : procs)
      this.codegen(proc);
  }

  public ProgramCodeGen() {
    this(null);
  }

  public ProgramCodeGen(Map<String,RecordsInfo> types) {
    this.cg.types = types;
    this.cg.pushInstruction(new GSB(labelOfProcName("main")));
    this.cg.pushInstruction(new STOP());
  }
}
