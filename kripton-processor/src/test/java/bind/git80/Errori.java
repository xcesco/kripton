package bind.git80;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Errori {
  public Errori(String xmlns, String descrizione, String codice) {
    this.xmlns = xmlns;
    this.descrizione = descrizione;
    this.codice = codice;
  }

  private final String xmlns;

  public String getDescrizione() {
    return descrizione;
  }

  private final String descrizione;

  public String getXmlns() {
    return xmlns;
  }

  public String getCodice() {
    return codice;
  }

  private final String codice;
}
