
package bind.git80;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Message {

  private final Errori errori;

  public Message(Errori errori, int codiceRitorno) {
    this.errori = errori;

    this.codiceRitorno = codiceRitorno;
  }

  public Errori getErrori() {
    return errori;
  }

  public int getCodiceRitorno() {
    return codiceRitorno;
  }

  private final int codiceRitorno;
}
