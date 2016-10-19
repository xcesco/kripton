package com.example01.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 908099 on 09/06/2015.
 */
public class DataModel {
    private static final DataModel instance=new DataModel();

    private DataModel()
    {
        aziende=new ArrayList<>();
        dipartimenti=new ArrayList<>();
        ultimoAggiornamento=new Date();
    }

    public List<Azienda> getAziende() {
        return aziende;
    }

    private List<Azienda> aziende;

    private List<Dipartimento> dipartimenti;

    public Date getUltimoAggiornamento() {
        return ultimoAggiornamento;
    }

    private Date ultimoAggiornamento;

    public static final DataModel  getInstance()
    {
        return instance;
    }

    public void update(TempiAttesa value)
    {
        aziende.clear();
        aziende.addAll(value.aziende);

        dipartimenti.clear();
        for (Azienda itemA:value.aziende)
        {
            for (ProntoSoccorso itemB: itemA.prontoSoccorsi)
            {
                dipartimenti.addAll(itemB.dipartimenti);
            }
        }

        ultimoAggiornamento=value.dataAggiornamento;
    }

    public List<Dipartimento> getDipartimenti()
    {
        return dipartimenti;
    }
}
