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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import victorflvioexamplecom.hccentrodebeleza.R;
import victorflvioexamplecom.hccentrodebeleza.extras.SavedSharedPreferences;

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

        mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        edtNomeServico = (EditText) findViewById(R.id.edtNomeServico);
        edtNomeServico.setText(SavedSharedPreferences.getNomeServico(this));

        edtDataReservada = (EditText) findViewById(R.id.edtDataReservada);
        edtDataReservada.setSelected(false);
        this.setDataTimeField();

        edtHoraReservada = (EditText) findViewById(R.id.edtHoraReservada);
        edtDataReservada.setSelected(false);
        this.setTimeField();

        btnSalvarReserva = (Button) findViewById(R.id.btnSalvarReserva);
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
        }
    }

    private void setTimeField() {
        edtHoraReservada.setOnClickListener(this);
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:ss", Locale.US);
        mTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar newTime = Calendar.getInstance(Locale.US);
                newTime.set(Calendar.HOUR_OF_DAY, i);
                newTime.set(Calendar.MINUTE, i1);
                edtHoraReservada.setText(simpleDateFormat.format(newTime.getTime()));
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
