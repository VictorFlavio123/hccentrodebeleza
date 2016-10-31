package victorflvioexamplecom.hccentrodebeleza.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.RealmList;
import victorflvioexamplecom.hccentrodebeleza.R;
import victorflvioexamplecom.hccentrodebeleza.database.Database;
import victorflvioexamplecom.hccentrodebeleza.extras.SavedSharedPreferences;
import victorflvioexamplecom.hccentrodebeleza.model.Reserva;
import victorflvioexamplecom.hccentrodebeleza.model.Usuario;

public class HCCentroDeBelezaTela3 extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    private EditText edtNome, edtEmail;
    private Button btnSalvarCadastro;

    private String mNome, mEmail;
    private RealmList<Reserva> mReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hccentro_de_beleza_tela3);

        //TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(getResources().getString(R.string.cadastro));
        mToolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inicializando o realm
        Database.getInstance().setContext(this);

        //Inicializando o widgets
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

        btnSalvarCadastro = (Button) findViewById(R.id.btnSalvarCadastro);
        btnSalvarCadastro.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        mNome = edtNome.getText().toString();
        mEmail = edtEmail.getText().toString();

        try {
            if (mNome.equals("") || mEmail.equals("")) {
                Toast.makeText(this, "Campos vazios!", Toast.LENGTH_SHORT).show();
            } else {
                mReservas = new RealmList<>();
                Usuario usuario = new Usuario();
                usuario.setNome(mNome);
                usuario.setEmail(mEmail);
                usuario.setReservas(mReservas);
                Database.getInstance().addUsuario(usuario);

                Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();

                SavedSharedPreferences.setInfoUser(this, mNome, mEmail, true);

                startActivity(new Intent(this, HCCentroDeBelezaTela2.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .putExtra("nomeUsuario", mNome)
                        .putExtra("emailUsuario", mEmail)
                        .putExtra("cadastrado", true));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}