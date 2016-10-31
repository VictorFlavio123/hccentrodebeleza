package victorflvioexamplecom.hccentrodebeleza.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Filipi Andrade on 31/10/2016.
 */

public class SavedSharedPreferences {

    static final String PREF_NOME_USUARIO = "nome";
    static final String PREF_EMAIL_USUARIO = "email";
    static final String PREF_USUARIO_CADASTRADO = "cadastrado";
    static final String PREF_NOME_SERVICO = "nome";
    static final String PREF_SERVICO_ID = "id";

    static SharedPreferences getSharedPreferences(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c);
    }

    public static void setInfoUser(Context c, String nome, String email, boolean cadastrado) {
        SharedPreferences.Editor editor = getSharedPreferences(c).edit();
        editor.putString(PREF_NOME_USUARIO, nome);
        editor.putString(PREF_EMAIL_USUARIO, email);
        editor.putBoolean(PREF_USUARIO_CADASTRADO, cadastrado);
        editor.commit();
        editor.apply();
    }

    public static void setNomeServico(Context c, String nome) {
        SharedPreferences.Editor editor = getSharedPreferences(c).edit();
        editor.putString(PREF_NOME_SERVICO, nome);
        //editor.putLong(PREF_SERVICO_ID, id);
        editor.commit();
        editor.apply();
    }

    public static String getEmailUsuario(Context c) {
        return getSharedPreferences(c).getString(PREF_EMAIL_USUARIO, "");
    }

    public static String getNomeUsuario(Context c) {
        return getSharedPreferences(c).getString(PREF_NOME_USUARIO, "");
    }

    public static boolean getUsuarioCadastrado(Context c) {
        return getSharedPreferences(c).getBoolean(PREF_USUARIO_CADASTRADO, false);
    }

    public static String getNomeServico(Context c) {
        return getSharedPreferences(c).getString(PREF_NOME_SERVICO, "");
    }

    public static long getIdServico(Context c) {
        return getSharedPreferences(c).getLong(PREF_SERVICO_ID, 0);
    }
}
