package victorflvioexamplecom.hccentrodebeleza.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import victorflvioexamplecom.hccentrodebeleza.R;
import victorflvioexamplecom.hccentrodebeleza.extras.SavedSharedPreferences;

public class HCCentroDeBelezaTela4 extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    private EditText edtNomeServico, edtDataReservada, edtHoraReservada;
    private Button btnSalvarReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hccentro_de_beleza_tela4);

        //TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(getResources().getString(R.string.reserva));
        mToolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNomeServico = (EditText) findViewById(R.id.edtNomeServico);

        edtNomeServico.setText(SavedSharedPreferences.getNomeServico(this));

        edtDataReservada = (EditText) findViewById(R.id.edtDataReservada);
        edtHoraReservada = (EditText) findViewById(R.id.edtHoraReservada);

        btnSalvarReserva = (Button) findViewById(R.id.btnSalvarReserva);
    }

    @Override
    public void onClick(View view) {

    }
}
