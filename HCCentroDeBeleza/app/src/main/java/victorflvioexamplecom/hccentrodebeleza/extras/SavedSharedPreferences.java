package victorflvioexamplecom.hccentrodebeleza.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SavedSharedPreferences {

    static final String PREF_NOME_USUARIO = "nome";
    static final String PREF_ID_USUARIO = "id_usuario";
    static final String PREF_EMAIL_USUARIO = "email";
    static final String PREF_USUARIO_CADASTRADO = "cadastrado";
    static final String PREF_NOME_SERVICO = "nome_servico";
    static final String PREF_HORA_SERVICO = "hora_servico";
    static final String PREF_DATA_SERVICO = "data_servico";
    static final String PREF_EDITAR_SERVICO = "editar_servico";
    static final String PREF_SERVICO_ID = "id";

    static SharedPreferences getSharedPreferences(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c);
    }

    public static void setInfoUser(Context c, String nome, String email, long id, boolean cadastrado) {
        SharedPreferences.Editor editor = getSharedPreferences(c).edit();
        editor.putString(PREF_NOME_USUARIO, nome);
        editor.putLong(PREF_ID_USUARIO, id);
        editor.putString(PREF_EMAIL_USUARIO, email);
        editor.putBoolean(PREF_USUARIO_CADASTRADO, cadastrado);
        editor.commit();
        editor.apply();
    }

    public static void setNomeServico(Context c, String servico) {
        SharedPreferences.Editor editor = getSharedPreferences(c).edit();
        editor.putString(PREF_NOME_SERVICO, servico);
        editor.commit();
        editor.apply();
    }

    public static void setNomeServico(Context c, String servico, String data, String hora) {
        SharedPreferences.Editor editor = getSharedPreferences(c).edit();
        editor.putString(PREF_NOME_SERVICO, servico);
        editor.putString(PREF_DATA_SERVICO, data);
        editor.putString(PREF_HORA_SERVICO, hora);
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

    public static String getDataServico(Context c) {
        return getSharedPreferences(c).getString(PREF_DATA_SERVICO, "");
    }

    public static String getHoraServico(Context c) {
        return getSharedPreferences(c).getString(PREF_HORA_SERVICO, "");
    }

    public static long getIdServico(Context c) {
        return getSharedPreferences(c).getLong(PREF_SERVICO_ID, 0);
    }

    public static long getIdUsuario(Context c) {
        return getSharedPreferences(c).getLong(PREF_ID_USUARIO, 0);
    }

    public static boolean getEditarServico(Context c) {
        return getSharedPreferences(c).getBoolean(PREF_EDITAR_SERVICO, false);
    }
}
