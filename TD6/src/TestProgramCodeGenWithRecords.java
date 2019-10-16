import java.io.IOException;

import edu.polytechnique.mjava.ast.TProgram;
import edu.polytechnique.mjava.ast.TTypeDef;
import edu.polytechnique.mjava.ast.factory.Factory;
import edu.polytechnique.mjava.parser.MJavaParseError;
import edu.polytechnique.mjava.toplevel.MJavaTop;
import edu.polytechnique.mjava.typing.exn.MJavaTypingError;

public final class TestProgramCodeGenWithRecords {
  public final static String PATH = "records.wil";


  private static final Factory<AbstractExpr, AbstractInstruction> factory
    = Factory.ofPackage(
        AbstractExpr.class, null, AbstractInstruction.class, null);

  public static void main(String[] args) throws IOException {
    try {
      TProgram<AbstractExpr, AbstractInstruction> prg =
          MJavaTop.parseAndTypeXProgramFromFile(PATH, factory);
      for (TTypeDef tf: prg.records) {

      }
      ProgramCodeGen cg = new ProgramCodeGen();
      cg.codegen(prg.procs);
      System.out.print(cg.cg);
    } catch (MJavaParseError | MJavaTypingError e) {
      System.err.println(e.getMessage());
    }
  }
}
