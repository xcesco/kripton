package bind.git49;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean1 {
   public String name;
   
   public Bean2 bean2;
   
  // public Bean3 bean3;
   
   @BindAdapter(adapter=Bean3Adapter.class)
   public Bean3 adaptedBean3;
}
