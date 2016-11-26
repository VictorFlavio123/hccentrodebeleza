package victorflvioexamplecom.hccentrodebeleza.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

import victorflvioexamplecom.hccentrodebeleza.R;
import victorflvioexamplecom.hccentrodebeleza.adapters.PrecosAdapter;
import victorflvioexamplecom.hccentrodebeleza.database.Database;
import victorflvioexamplecom.hccentrodebeleza.extras.SavedSharedPreferences;
import victorflvioexamplecom.hccentrodebeleza.interfaces.RecyclerViewOnClickListenerHack;
import victorflvioexamplecom.hccentrodebeleza.model.Servicos;

public class HCCentroDeBelezaTela2 extends AppCompatActivity implements RecyclerViewOnClickListenerHack {

    private Toolbar mToolbar;
    private RecyclerView rvListTela2;
    private ArrayList<Servicos> mServicos;
    private PrecosAdapter mPrecosAdapter;

    private Drawer mDrawer;
    private AccountHeader mAccountHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hccentro_de_beleza_tela2);

        //REALM
        Database.getInstance().setContext(this);

        //TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        mToolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(mToolbar);

        //Inicializando o recycler view
        rvListTela2 = (RecyclerView) findViewById(R.id.rvListTela2);

        mServicos = initList();

        mPrecosAdapter = new PrecosAdapter(this, mServicos);
        mPrecosAdapter.setRecyclerViewOnClickListenerHack(this);
        rvListTela2.setHasFixedSize(true);
        rvListTela2.setLayoutManager(new LinearLayoutManager(this));
        rvListTela2.setAdapter(mPrecosAdapter);

        //Intent
        Bundle bundle = getIntent().getExtras();

        String nomeUsuario = SavedSharedPreferences.getNomeUsuario(this);
        String emailUsuario = SavedSharedPreferences.getEmailUsuario(this);
        final boolean usuarioCadastrado = SavedSharedPreferences.getUsuarioCadastrado(this);
        final long id = SavedSharedPreferences.getIdUsuario(this);

        //Log.i("TAG", usuarioCadastrado + "");

        //Navigation drawer
        if (usuarioCadastrado) {
            mAccountHeader = new AccountHeaderBuilder()
                    .withActivity(this)
                    .withCompactStyle(false)
                    .withSavedInstance(savedInstanceState)
                    .withHeaderBackground(getResources().getDrawable(R.drawable.header))
                    .addProfiles(
                            new ProfileDrawerItem()
                                    .withName(nomeUsuario)
                                    .withEmail(emailUsuario)
                                    .withIcon(R.mipmap.ic_launcher)
                    )
                    .withSelectionListEnabledForSingleProfile(false)
                    .build();
            Log.i("TAG", "onCreate: " + nomeUsuario);
        } else {
            mAccountHeader = new AccountHeaderBuilder()
                    .withActivity(this)
                    .withCompactStyle(false)
                    .withSavedInstance(savedInstanceState)
                    .withHeaderBackground(getResources().getDrawable(R.drawable.header))
                    .addProfiles(
                            new ProfileDrawerItem()
                                    .withName(getString(R.string.app_name))
                                    .withIcon(R.mipmap.ic_launcher)
                    )
                    .withSelectionListEnabledForSingleProfile(false)
                    .build();
        }

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(-1)
                .withAccountHeader(mAccountHeader)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Nossos Serviços").withIcon(getResources().getDrawable(R.drawable.ic_briefcase_grey600_48dp)).withSelectable(false),
                        new PrimaryDrawerItem().withName("Fazer Cadastro").withIcon(getResources().getDrawable(R.drawable.ic_account_plus_grey600_48dp)).withSelectable(false),
                        new PrimaryDrawerItem().withName("Seus Horários").withIcon(getResources().getDrawable(R.drawable.ic_clock_grey600_48dp)).withSelectable(false),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Info").withIcon(getResources().getDrawable(R.drawable.ic_information_grey600_48dp)).withSelectable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch(position) {
                            case 1:
                                mDrawer.closeDrawer();
                                break;
                            case 2:
                                //IMPLICITA
                                startActivity(new Intent(HCCentroDeBelezaTela2.this, HCCentroDeBelezaTela3.class));
                                break;
                            case 3:
                                if (usuarioCadastrado) {
                                    if (!Database.getInstance().isEmptyReserva(id)) {
                                        startActivity(new Intent(HCCentroDeBelezaTela2.this, HCCentroDeBelezaTela5.class));
                                    } else {
                                        Toast.makeText(HCCentroDeBelezaTela2.this, "Esse usuario não possui reserva!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(HCCentroDeBelezaTela2.this, "Você não possui cadastro!", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 5:
                                startActivity(new Intent(HCCentroDeBelezaTela2.this, MapsActivity.class));
                                break;
                        }
                        return false;
                    }
                })
                .build();
    }

    private ArrayList<Servicos> initList() {
        Servicos servicos1 = new Servicos();
        servicos1.setNome("Corte feminino");
        servicos1.setPreco(50.00);
        Servicos servicos2 = new Servicos();
        servicos2.setNome("Corte masculino");
        servicos2.setPreco(20.00);
        Servicos servicos3 = new Servicos();
        servicos3.setNome("Escova");
        servicos3.setPreco(20.00);
        Servicos servicos4 = new Servicos();
        servicos4.setNome("Selagem");
        servicos4.setPreco(60.00);
        Servicos servicos5 = new Servicos();
        servicos5.setNome("Unhas do pé");
        servicos5.setPreco(16.00);
        Servicos servicos6 = new Servicos();
        servicos6.setNome("Unhas da mão");
        servicos6.setPreco(6.00);
        Servicos servicos7 = new Servicos();
        servicos7.setNome("Depilação de virilha");
        servicos7.setPreco(50.00);
        Servicos servicos8 = new Servicos();
        servicos8.setNome("Depilação de buço");
        servicos8.setPreco(30.00);
        Servicos servicos9 = new Servicos();
        servicos9.setNome("Depilação de axila");
        servicos9.setPreco(20.00);
        Servicos servicos10 = new Servicos();
        servicos10.setNome("Sobrancelha");
        servicos10.setPreco(15.00);
        Servicos servicos11 = new Servicos();
        servicos11.setNome("Maquiagem");
        servicos11.setPreco(20.00);
        Servicos servicos12 = new Servicos();
        servicos12.setNome("Penteado");
        servicos12.setPreco(70.00);

        mServicos = new ArrayList<>();
        mServicos.add(servicos1);
        mServicos.add(servicos2);
        mServicos.add(servicos3);
        mServicos.add(servicos4);
        mServicos.add(servicos5);
        mServicos.add(servicos6);
        mServicos.add(servicos7);
        mServicos.add(servicos8);
        mServicos.add(servicos9);
        mServicos.add(servicos10);
        mServicos.add(servicos11);
        mServicos.add(servicos12);

        return mServicos;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void onClickListener(View v, int position) {
        boolean usuarioCadastrado = SavedSharedPreferences.getUsuarioCadastrado(this);

        if(usuarioCadastrado) {
            //EXPLICITO
            SavedSharedPreferences.setNomeServico(this, mPrecosAdapter.returnNome(position));
            startActivity(new Intent(this, HCCentroDeBelezaTela4.class));
        } else {
            Toast.makeText(this, "Realize o cadastro antes de reservar um serviço!", Toast.LENGTH_SHORT).show();
        }
    }
}
