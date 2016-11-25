package victorflvioexamplecom.hccentrodebeleza.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.RealmList;
import victorflvioexamplecom.hccentrodebeleza.R;
import victorflvioexamplecom.hccentrodebeleza.database.Database;
import victorflvioexamplecom.hccentrodebeleza.extras.SavedSharedPreferences;
import victorflvioexamplecom.hccentrodebeleza.model.Reserva;

public class HCCentroDeBelezaTela4 extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    private EditText edtNomeServico, edtDataReservada, edtHoraReservada;
    private Button btnSalvarReserva;

    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimePickerDialog;
    private SimpleDateFormat mSimpleDateFormat;

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

        //Inicializando o realm
        Database.getInstance().setContext(this);

        mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        edtNomeServico = (EditText) findViewById(R.id.edtNomeServico);
        edtDataReservada = (EditText) findViewById(R.id.edtDataReservada);
        edtHoraReservada = (EditText) findViewById(R.id.edtHoraReservada);

        Bundle bundle = getIntent().getExtras();
        boolean editar = false;
        if (bundle != null) {
            editar = bundle.getBoolean("editar");
        }
        if (editar) {
            edtNomeServico.setText(SavedSharedPreferences.getNomeServico(this));
            edtDataReservada.setText(SavedSharedPreferences.getDataServico(this));
            edtDataReservada.setSelected(false);
            this.setDataTimeField();
            edtHoraReservada.setText(SavedSharedPreferences.getHoraServico(this));
            edtHoraReservada.setSelected(false);
            this.setTimeField();
        } else {
            edtNomeServico.setText(SavedSharedPreferences.getNomeServico(this));
            edtDataReservada.setSelected(false);
            this.setDataTimeField();
            edtHoraReservada.setSelected(false);
            this.setTimeField();
        }

        btnSalvarReserva = (Button) findViewById(R.id.btnSalvarReserva);
        btnSalvarReserva.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.edtDataReservada:
                mDatePickerDialog.show();
                break;
            case R.id.edtHoraReservada:
                mTimePickerDialog.show();
                break;
            case R.id.btnSalvarReserva:
                Bundle bundle = getIntent().getExtras();
                boolean editar = false;
                int position = 0;
                if (bundle != null) {
                    editar = bundle.getBoolean("editar");
                    position = bundle.getInt("position");
                }

                if (edtDataReservada.getText().length() == 0 || edtHoraReservada.getText().length() == 0) {
                    Toast.makeText(this, "Campos vazios!", Toast.LENGTH_SHORT).show();
                } else {
                    Reserva reserva = new Reserva();
                    reserva.setNomeServico(edtNomeServico.getText().toString());
                    reserva.setData(edtDataReservada.getText().toString());
                    reserva.setHorario(edtHoraReservada.getText().toString());

                    long id = SavedSharedPreferences.getIdUsuario(this);

                    RealmList<Reserva> mReservas = Database.getInstance().getReservas(id);

                    if (editar) {
                        Database.getInstance().updateReserva(reserva, mReservas.get(position).getId(), position, mReservas);
                    } else {
                        Database.getInstance().makeReservation(reserva, id);
                    }
                    finish();
                }
                break;
        }
    }

    private void setTimeField() {
        edtHoraReservada.setOnClickListener(this);
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        mTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if (selectedMinute <= 0 || selectedMinute < 10) {
                    edtHoraReservada.setText(selectedHour + ":" + "0" + selectedMinute);
                } else {
                    edtHoraReservada.setText(selectedHour + ":" + selectedMinute);
                }
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
    }

    private void setDataTimeField() {
        edtDataReservada.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edtDataReservada.setText(mSimpleDateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }
}
