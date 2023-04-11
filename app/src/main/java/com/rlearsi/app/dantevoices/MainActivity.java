package com.rlearsi.app.dantevoices;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;

import com.rlearsi.app.dantevoices.databinding.ActivityMainBinding;
import com.rlearsi.app.dantevoices.events.Adapter;
import com.rlearsi.app.dantevoices.events.ItemsEvents;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements InterfaceUpdates {

    private static final String TAG = "xdantex";
    //private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding main;
    Activity activity;
    Context context;
    InterfaceUpdates interfaceUpdates;
    Adapter adapter;
    List<ItemsEvents> list = new ArrayList<>();
    //private List<MediaPlayer> mediaPlayers = new ArrayList<>();
    private HashMap<Integer, MediaPlayer> mediaPlayers = new HashMap<>();
    MediaPlayer mediaPlayer;
    //private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(main.getRoot());

        activity = this;
        context = this;
        interfaceUpdates = this;

        setSupportActionBar(main.toolbar);

        //mediaPlayer = new MediaPlayer();
        /*mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );*/

        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        /*main.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());*/

        contentMain();


    }

    public void contentMain() {

        GridLayoutManager layoutManager = new GridLayoutManager(context, 4);
        main.rvListVoices.setLayoutManager(layoutManager);

        list.add(new ItemsEvents(1, "Ah Mizeravi!","ah_mizeravi"));
        list.add(new ItemsEvents(2, "Cabô douradinho!", "ai_cabo_douradinho"));
        list.add(new ItemsEvents(3, "ai Fatal aiaiai!", "ai_fatal_aiaiai"));
        list.add(new ItemsEvents(4, "Ai tata!", "ai_tata"));
        list.add(new ItemsEvents(5, "Ai todis!", "ai_todis"));
        list.add(new ItemsEvents(6, "Ai Will!", "ai_will"));
        list.add(new ItemsEvents(7, "Ai Will 2!", "ai_will_2"));
        list.add(new ItemsEvents(8, "Ai Will 3!", "ai_will_3"));
        list.add(new ItemsEvents(9, "Assistir Teletumbies!", "assistir_teletumbies"));
        list.add(new ItemsEvents(10, "Caiu de novo", "caiu_de_novo"));
        list.add(new ItemsEvents(11, "Claudia capataz", "claudia_capataz"));
        list.add(new ItemsEvents(12, "Da mais xp pa eu", "da_mais_xp_pa_eu"));
        list.add(new ItemsEvents(13, "É mermo né", "e_mermo_ne"));
        list.add(new ItemsEvents(14, "É o jogas", "e_o_jogas"));
        list.add(new ItemsEvents(15, "Eu gosto assim", "eu_gosto_assim"));
        list.add(new ItemsEvents(16, "Fatal bobebou", "fatal_bobebou"));
        list.add(new ItemsEvents(17, "Fatal morreu pro cristal", "fatal_morreu_pro_cristal"));
        list.add(new ItemsEvents(18, "Fatal morreu pro cristal 2", "fatal_morreu_pro_cristal2"));
        list.add(new ItemsEvents(19, "Já pensou", "ja_pensou"));
        list.add(new ItemsEvents(20, "Jogo na frente Fatal joga atras", "jogo_na_frente_fatal_joga_atras"));
        list.add(new ItemsEvents(21, "Perdeu tempo ouvindo", "perdeu_tempo_ouvindo"));
        list.add(new ItemsEvents(22, "Pois não", "pois_nao"));
        list.add(new ItemsEvents(23, "Smoke é docinho", "smoke_e_docinho"));
        list.add(new ItemsEvents(24, "Smoke é fudido mermo viu", "smoke_e_fudido_mermo_viu"));
        list.add(new ItemsEvents(25, "Tanko o Smoke na vida", "tanko_o_smoke_na_vida"));
        list.add(new ItemsEvents(26, "Verme levantou voo", "verme_levantou_voo"));
        list.add(new ItemsEvents(27, "Você vai aparecer no top", "voce_vai_aparecer_no_top"));

        new Thread(() -> {

            // Adiciona o adapter que irá anexar os objetos à lista.
            adapter = new Adapter(list, interfaceUpdates, context);

            runOnUiThread(() -> {

                main.rvListVoices.setAdapter(adapter);

            });

        }).start();

    }

    @Override
    public void handleItems(int position, int id, String title, String file_name) {

        String path = "android.resource://" + getPackageName() + "/raw/" + file_name;

        //Log.i(TAG, path + " - position: " + position);

        if (mediaPlayers.containsKey(id)) {

            //if (mediaPlayers.get(id) != null) {

            try {

                if (Objects.requireNonNull(mediaPlayers.get(id)).isPlaying()) {

                    Objects.requireNonNull(mediaPlayers.get(id)).stop();
                    Objects.requireNonNull(mediaPlayers.get(id)).reset();

                }

            } catch (Exception ignored) {

            }

        }

        mediaPlayer = new MediaPlayer();

        this.mediaPlayers.put(id, mediaPlayer);

        try {

            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(path));
            mediaPlayer.prepare();

            mediaPlayer.setOnCompletionListener((MediaPlayer.OnCompletionListener) player -> {

                player.stop();
                player.reset();

                adapter.updateRow(new ItemsEvents(id, title, file_name));

            });

        } catch (IOException e) {

            e.printStackTrace();

        }

        mediaPlayer.start();

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/

    /*@Override
    public void onBackPressed() {

        if (isHome) {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            toast(getString(R.string.press_again_to_exit));

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);

        } else {

            showHome();

        }

    }*/

    public void destroy(HashMap<Integer, MediaPlayer> mp) {

        for (Map.Entry<Integer, MediaPlayer> entry : mp.entrySet()) {

            Integer key = entry.getKey();

            Objects.requireNonNull(mp.get(key)).release();

            Log.i(TAG, "o MediaPlayer " + key + " foi liberado");

        }

    }

    @Override
    public void onStop() {

        destroy(mediaPlayers);
        super.onStop();
    }

    @Override
    public void onDestroy() {

        destroy(mediaPlayers);
        super.onDestroy();
    }

}