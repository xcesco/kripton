package bind.emptystream;

import com.abubusoft.kripton.annotation.BindType;

import java.time.ZonedDateTime;

@BindType
public class Rifornimento {
    public ZonedDateTime getDataRifornimento() {
        return dataRifornimento;
    }

    public String getTarga() {
        return targa;
    }

    private final ZonedDateTime dataRifornimento;
    private final String targa;

    public Rifornimento(ZonedDateTime dataRifornimento, String targa) {
        this.dataRifornimento = dataRifornimento;
        this.targa = targa;
    }
}
