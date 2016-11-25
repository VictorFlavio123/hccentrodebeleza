package victorflvioexamplecom.hccentrodebeleza.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Usuario extends RealmObject {

    @PrimaryKey
    private long id;
    private String nome;
    private String email;
    private RealmList<Reserva> reservas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RealmList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(RealmList<Reserva> reservas) {
        this.reservas = reservas;
    }
}
