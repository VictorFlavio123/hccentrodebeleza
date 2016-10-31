package victorflvioexamplecom.hccentrodebeleza.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Filipi Andrade on 31/10/2016.
 */

public class Reserva extends RealmObject {

    @PrimaryKey
    private long id;
    private String nomeServico;
    private String horario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
