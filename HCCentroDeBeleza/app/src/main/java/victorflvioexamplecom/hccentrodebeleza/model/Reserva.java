package victorflvioexamplecom.hccentrodebeleza.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Reserva extends RealmObject {

    @PrimaryKey
    private long id;
    private String nomeServico;
    private String data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
