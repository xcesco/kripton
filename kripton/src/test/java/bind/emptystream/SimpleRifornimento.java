package bind.emptystream;

import com.abubusoft.kripton.annotation.BindType;

import java.time.ZonedDateTime;

@BindType
public class SimpleRifornimento {

    public ZonedDateTime getDataRifornimento() {
        return dataRifornimento;
    }

    public SimpleRifornimento setDataRifornimento(ZonedDateTime dataRifornimento) {
        this.dataRifornimento = dataRifornimento;
        return this;
    }

    public String getTarga() {
        return targa;
    }

    public SimpleRifornimento setTarga(String targa) {
        this.targa = targa;
        return this;
    }

    private ZonedDateTime dataRifornimento;
    private String targa;

}
