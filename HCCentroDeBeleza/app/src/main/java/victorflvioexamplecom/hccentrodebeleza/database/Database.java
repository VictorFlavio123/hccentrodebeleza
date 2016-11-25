package victorflvioexamplecom.hccentrodebeleza.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import victorflvioexamplecom.hccentrodebeleza.extras.SavedSharedPreferences;
import victorflvioexamplecom.hccentrodebeleza.model.Reserva;
import victorflvioexamplecom.hccentrodebeleza.model.Usuario;


public class Database {

    private static Database mInstance;

    private Realm mRealm;
    private RealmConfiguration mRealmConfiguration;

    private Context mContext;

    private Database(){}

    public static Database getInstance() {
        if (mInstance == null) {
            mInstance = new Database();
        }
        return mInstance;
    }

    public void setContext(Context context) {
        mInstance.mContext = context;
        mInstance.mRealmConfiguration = new RealmConfiguration.Builder(context)
                .name("hccentrodebeleza.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.compactRealm(mRealmConfiguration);
        mRealm = Realm.getInstance(mRealmConfiguration);
    }

    public void addUsuario(Usuario u) {
        mRealm.beginTransaction();
        Usuario usuario = mRealm.createObject(Usuario.class);
        long id = mRealm.where(Usuario.class).max("id").longValue() + 1;
        usuario.setId(id);
        usuario.setNome(u.getNome());
        usuario.setEmail(u.getEmail());
        usuario.setReservas(u.getReservas());
        SavedSharedPreferences.setInfoUser(mContext, u.getNome(), u.getEmail(), id, true);
        mRealm.commitTransaction();
    }

    public void makeReservation(Reserva r, long id) {
        mRealm.beginTransaction();
        Reserva reserva = mRealm.createObject(Reserva.class);
        long idReserva = mRealm.where(Reserva.class).max("id").longValue() + 1;
        reserva.setId(idReserva);
        reserva.setNomeServico(r.getNomeServico());
        reserva.setData(r.getData());
        reserva.setHorario(r.getHorario());

        Usuario usuario = mRealm.where(Usuario.class)
                .equalTo("id", id)
                .findFirst();
        usuario.getReservas().add(reserva);
        mRealm.commitTransaction();
    }

    public RealmList<Reserva> getReservas(long id) {
        Usuario usuario = mRealm.where(Usuario.class)
                .equalTo("id", id)
                .findFirst();

        return usuario.getReservas();
    }

    public void updateReserva(Reserva r, long id, int position, RealmList<Reserva> mReservas) {
        mRealm.beginTransaction();
        Reserva reserva = mRealm.where(Reserva.class)
                .equalTo("id", id)
                .findFirst();
        long idReserva = mReservas.get(position).getId();
        mReservas.remove(position);
        reserva.setId(idReserva);
        reserva.setNomeServico(r.getNomeServico());
        reserva.setHorario(r.getHorario());
        reserva.setData(r.getData());
        mReservas.add(position, reserva);
        mRealm.commitTransaction();
    }
}
