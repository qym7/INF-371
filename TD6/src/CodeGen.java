import java.util.*;
import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.mjava.ast.TType;
import edu.polytechnique.xvm.asm.*;
import edu.polytechnique.xvm.asm.interfaces.*;
import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.mjava.ast.VarDecl;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Vector;


public final class CodeGen {
  private Vector<AsmInstruction> instructions;
  private Map<String, Integer>   labels;
  public Map<String, Integer>   offsets;
  static int order = 0, lettre = 0;
  public TProcDef<AbstractExpr, AbstractInstruction>  proc;
  public Map<String, RecordsInfo> types;

  public CodeGen() {
    this.instructions = new Vector<AsmInstruction>();
    this.labels = new HashMap<String, Integer>();
    this.offsets = new HashMap<String, Integer>();
    this.types = new HashMap<String, RecordsInfo>();
  }

  public Map<String, RecordsInfo> getTypes(){
    return this.types;
  }

  public CodeGen(Map<String, RecordsInfo> types) {
    this();
    this.types = types;
  }

  public List<VarDecl> getArgs(){
    return proc.getArgs();
  }

  public List<VarDecl> getLocals(){
    return proc.getLocals();
  }

  public static String generateLabel() {
    String labelc = (char)(97+lettre)+order+"";
    if (order<9999) order++;
    else if (lettre<25) {
      lettre++;
      order = 0;
    }
    return labelc;
  }

  public void pushLabel(String label) {
    int index = this.instructions.size()+1;
    this.labels.put(label,index);
  }

  public int getOffset(String s){
    return this.offsets.get(s);
  }

  public RecordsInfo getType(String name){ return this.types.get(name); }

  public void pushInstruction(AsmInstruction asm) {
    this.instructions.add(asm);
  }

  public void pushLocalVariable(String name, int offset) {
      offsets.put(name, offset);
  }

  public int getOffsets(String name){
    return this.offsets.get(name);
  }
  
  public void clearLocals() {
    this.offsets.clear();
  }
  
  @Override
  public String toString() {
    return Printer.programToString(this.instructions, this.labels);
  }

}
