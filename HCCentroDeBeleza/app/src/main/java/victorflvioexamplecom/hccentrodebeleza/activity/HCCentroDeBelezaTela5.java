package victorflvioexamplecom.hccentrodebeleza.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import io.realm.RealmList;
import victorflvioexamplecom.hccentrodebeleza.R;
import victorflvioexamplecom.hccentrodebeleza.adapters.ReservaAdapter;
import victorflvioexamplecom.hccentrodebeleza.database.Database;
import victorflvioexamplecom.hccentrodebeleza.extras.SavedSharedPreferences;
import victorflvioexamplecom.hccentrodebeleza.model.Reserva;

public class HCCentroDeBelezaTela5 extends AppCompatActivity {

    private Toolbar mToolbar;

    private RecyclerView rvListTela5;
    private RealmList<Reserva> mReservas;
    private ReservaAdapter mReservaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hccentro_de_beleza_tela5);

        //TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        mToolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inicializando o realm
        Database.getInstance().setContext(this);
        long id = SavedSharedPreferences.getIdUsuario(this);
        mReservas = Database.getInstance().getReservas(id);

        //Inicializando o recycler view
        rvListTela5 = (RecyclerView) findViewById(R.id.rvListTela5);

        mReservaAdapter = new ReservaAdapter(this, mReservas);
        rvListTela5.setHasFixedSize(true);
        rvListTela5.setLayoutManager(new LinearLayoutManager(this));
        rvListTela5.setAdapter(mReservaAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
