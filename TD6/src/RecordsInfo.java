import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.polytechnique.mjava.ast.TType;
import edu.polytechnique.mjava.ast.TTypeDef;
import org.antlr.v4.runtime.misc.Pair;

public class RecordsInfo {
  public TTypeDef             definition;
  public int                  size;
  public Map<String, Integer> offsets;

  public RecordsInfo(TTypeDef definition, int size, Map<String, Integer> offsets)
  {
    this.definition = definition;
    this.size = size;
    this.offsets = offsets;
  }

    public static void visit1(Map<String, TTypeDef> mtypes,
                              Map<String, RecordsInfo> infos,
                              TTypeDef ty)
    {
        // Compute the informations (size & field offsets) of `ty`
        // and store it in `infos`. The map `mtypes` contains all
        // the records declarations.
        if (infos.containsKey(ty.getName())) return;

        RecordsInfo tyrecordinfo = new RecordsInfo(ty,0,new HashMap<String,Integer>());

        List<Pair<TType, String>> champ = ty.getFields();

        int newsize = 0;

        if (ty.getParent().isPresent()) {
            TTypeDef typarent = mtypes.get(ty.getParent().get().getName());
            RecordsInfo parentinfo = infos.get(ty.getParent().get().getName());
            if (parentinfo == null) visit1(mtypes,infos,typarent);
            newsize = parentinfo.size;
            tyrecordinfo.offsets.putAll(parentinfo.offsets);
        }

        for (Pair<TType, String> champs:champ) {
            tyrecordinfo.offsets.put(champs.b,newsize++);
        }
        tyrecordinfo.size = newsize;

        infos.put(ty.getName(), tyrecordinfo);
        //throw new UnsupportedOperationException(); // FIXME
    }

  public static Map<String, RecordsInfo> visit(List<TTypeDef> types) {
    Map<String, TTypeDef> mtypes
        = new HashMap<String, TTypeDef>();
    Map<String, RecordsInfo> result
        = new HashMap<String, RecordsInfo>();

    for (TTypeDef def : types)
      mtypes.put(def.getName(), def);
    for (TTypeDef def : types)
      visit1(mtypes, result, def);
    return result;
  }
}
