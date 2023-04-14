package com.rlearsi.app.dantevoices.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "voices.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_VOICES = "voices";

    private final String C_TABLE_VOICES = "CREATE TABLE IF NOT EXISTS " + TABLE_VOICES + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, file_name TEXT NOT NULL, color TEXT, isLoop INTEGER, played INTEGER, fav INTEGER, pid INTEGER, active INTEGER, datetime TEXT, " +
            "UNIQUE(file_name) ON CONFLICT IGNORE);";

    private final String C_IDX = "CREATE INDEX IF NOT EXISTS fav_idx ON " + TABLE_VOICES + "(ID,fav);";
    private final String C_IDX_2 = "CREATE INDEX IF NOT EXISTS played_idx ON " + TABLE_VOICES + "(ID,played);";

    DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {

        db.execSQL(C_TABLE_VOICES);
        db.execSQL(C_IDX);
        db.execSQL(C_IDX_2);

        try{

            insertVoice(db, "Acho que vou abrir o barco", "acho_que_vou_abrir_o_barco");
            insertVoice(db, "Ah mizeravi", "ah_mizeravi");
            insertVoice(db, "Ah mizeravi 2", "ah_mizeravi_2");
            insertVoice(db, "Ai cabo douradinho", "ai_cabo_douradinho");
            insertVoice(db, "Ai dan", "ai_dan");
            insertVoice(db, "Ai fatal aiaiai", "ai_fatal_aiaiai");
            insertVoice(db, "Ai minha mana", "ai_minha_mana");
            insertVoice(db, "Ai tata", "ai_tata");
            insertVoice(db, "Ai tata 2", "ai_tata_2");
            insertVoice(db, "Ai todis", "ai_todis");
            insertVoice(db, "Ai will", "ai_will");
            insertVoice(db, "Ai will 2", "ai_will_2");
            insertVoice(db, "Ai will 3", "ai_will_3");
            insertVoice(db, "Ano que vem passo o smoke", "ano_que_vem_passo_o_smoke");
            insertVoice(db, "Assistir teletumbies", "assistir_teletumbies");
            insertVoice(db, "Bersek amolou machado", "bersek_amolou_machado");
            insertVoice(db, "Boa noite 1", "boa_noite_1");
            insertVoice(db, "Caiu de novo", "caiu_de_novo");
            insertVoice(db, "Claudia capataz", "claudia_capataz");
            insertVoice(db, "Da mais xp pa eu", "da_mais_xp_pa_eu");
            insertVoice(db, "É mermo ne", "e_mermo_ne");
            insertVoice(db, "É o jogas", "e_o_jogas");
            insertVoice(db, "Empresta sua bota", "empresta_sua_bota");
            insertVoice(db, "Eu gosto assim", "eu_gosto_assim");
            insertVoice(db, "Fatal bobeou", "fatal_bobeou");
            insertVoice(db, "Fatal morreu pro cristal", "fatal_morreu_pro_cristal");
            insertVoice(db, "Fatal morreu pro cristal 2", "fatal_morreu_pro_cristal2");
            insertVoice(db, "Fatal vende o rabo", "fatal_vende_o_rabo");
            insertVoice(db, "Já pensou...", "ja_pensou");
            insertVoice(db, "Jogo na frente fatal joga atras", "jogo_na_frente_fatal_joga_atras");
            insertVoice(db, "Perdeu tempo ouvindo", "perdeu_tempo_ouvindo");
            insertVoice(db, "Pois não", "pois_nao");
            insertVoice(db, "Quebrar a cama", "quebrar_a_cama");
            insertVoice(db, "Smoke é docinho", "smoke_e_docinho");
            insertVoice(db, "Smoke é fudido mermo", "smoke_e_fudido_mermo_viu");
            insertVoice(db, "Smoke é fudido mesmo 2", "smoke_fudido_mesmo_2");
            insertVoice(db, "Sobra mais xp pa eu", "sobra_mais_xp_pa_eu");
            insertVoice(db, "Tadeu ta dando", "tadeu_ta_dando");
            insertVoice(db, "Tanko o smoke na vida", "tanko_o_smoke_na_vida");
            insertVoice(db, "Tira", "tira");
            insertVoice(db, "Tirar douradinho do fatal", "tirar_douradinho_do_fatal");
            insertVoice(db, "Verme levantou vôo", "verme_levantou_voo");
            insertVoice(db, "Vetelz miseravi", "vetelz_miseravi");
            insertVoice(db, "Você vai aparecer no top", "voce_vai_aparecer_no_top");
            insertVoice(db, "Vou ter que abrir esse barco", "vou_ter_que_abrir_esse_barco");

        } catch (Exception ignored) {}

    }

    public static void insertVoice(SQLiteDatabase db, String name, String file_name) {

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("file_name", file_name);
        values.put("color", "#7f00d9");
        values.put("isLoop", "0");
        values.put("played", 0);
        values.put("fav", 0);
        values.put("pid", 0);
        values.put("active", 1);
        values.put("datetime", String.valueOf(new Date()));

        db.insert(TABLE_VOICES, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // create new tables
        onCreate(db);

    }

}