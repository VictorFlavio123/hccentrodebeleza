package victorflvioexamplecom.hccentrodebeleza.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import victorflvioexamplecom.hccentrodebeleza.model.Usuario;

/**
 * Created by Filipi Andrade on 30/10/2016.
 */

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
        mRealm.commitTransaction();
    }
}
