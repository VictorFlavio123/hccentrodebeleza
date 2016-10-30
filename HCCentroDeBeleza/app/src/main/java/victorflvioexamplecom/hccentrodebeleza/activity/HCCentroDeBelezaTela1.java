package victorflvioexamplecom.hccentrodebeleza.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import victorflvioexamplecom.hccentrodebeleza.R;

public class HCCentroDeBelezaTela1 extends AppCompatActivity {

    private boolean back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hccentro_de_beleza_tela1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               finish();

                if(!back) {
                    startActivity(new Intent(HCCentroDeBelezaTela1.this, HCCentroDeBelezaTela2.class));
                }
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        back = true;
        super.onBackPressed();
    }
}
