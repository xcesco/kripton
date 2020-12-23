package sqlite.feature.optional.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Artist {
  public long id;
  public String title;
  public byte[] data;
}
