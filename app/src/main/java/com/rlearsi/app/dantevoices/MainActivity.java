package com.rlearsi.app.dantevoices;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;

import com.rlearsi.app.dantevoices.databinding.ActivityMainBinding;
import com.rlearsi.app.dantevoices.events.Adapter;
import com.rlearsi.app.dantevoices.events.ItemsEvents;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InterfaceUpdates {

    private static final String TAG = "xdantex";
    //private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding main;
    Activity activity;
    Context context;
    InterfaceUpdates interfaceUpdates;
    Adapter adapter;
    List<ItemsEvents> list = new ArrayList<>();
    private List<MediaPlayer> mediaPlayers = new ArrayList<>();
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

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        main.rvListVoices.setLayoutManager(layoutManager);

        list.add(new ItemsEvents(1, "ah_mizeravi"));
        list.add(new ItemsEvents(2, "ai_cabo_douradinho"));
        list.add(new ItemsEvents(3, "ai_fatal_aiaiai"));
        list.add(new ItemsEvents(4, "ai_tata"));
        list.add(new ItemsEvents(5, "ai_todis"));
        list.add(new ItemsEvents(6, "ai_will"));
        list.add(new ItemsEvents(7, "ai_will_2"));
        list.add(new ItemsEvents(8, "ai_will_3"));
        list.add(new ItemsEvents(9, "assistir_teletumbies"));
        list.add(new ItemsEvents(10, "caiu_de_novo"));
        list.add(new ItemsEvents(11, "claudia_capataz"));
        list.add(new ItemsEvents(12, "da_mais_xp_pa_eu"));
        list.add(new ItemsEvents(13, "e_mermo_ne"));
        list.add(new ItemsEvents(14, "e_o_jogas"));
        list.add(new ItemsEvents(15, "eu_gosto_assim"));
        list.add(new ItemsEvents(16, "fatal_bobebou"));
        list.add(new ItemsEvents(17, "fatal_morreu_pro_cristal"));
        list.add(new ItemsEvents(18, "fatal_morreu_pro_cristal2"));
        list.add(new ItemsEvents(19, "ja_pensou"));
        list.add(new ItemsEvents(20, "jogo_na_frente_fatal_joga_atras"));
        list.add(new ItemsEvents(21, "perdeu_tempo_ouvindo"));
        list.add(new ItemsEvents(22, "pois_nao"));
        list.add(new ItemsEvents(23, "smoke_e_docinho"));
        list.add(new ItemsEvents(24, "smoke_e_fudido_mermo_viu"));
        list.add(new ItemsEvents(25, "tanko_o_smoke_na_vida"));
        list.add(new ItemsEvents(26, "verme_levantou_voo"));
        list.add(new ItemsEvents(27, "voce_vai_aparecer_no_top"));

        new Thread(() -> {

            // Adiciona o adapter que irá anexar os objetos à lista.
            adapter = new Adapter(list, interfaceUpdates, context);

            runOnUiThread(() -> {

                main.rvListVoices.setAdapter(adapter);

            });

        }).start();

    }

    @Override
    public void handleItems(int position, int id, String title) {

        String path = "android.resource://" + getPackageName() + "/raw/" + title;

        Log.i(TAG, path + " - position: " + position);

        if (mediaPlayers.get(position) !=null) {

            if (mediaPlayers.get(position).isPlaying()) {
                mediaPlayers.get(position).stop();
                mediaPlayers.get(position).reset();
            }
        } else {

            MediaPlayer mediaPlayer = new MediaPlayer();

            mediaPlayers.add(mediaPlayer);
            //mediaPlayer[0];

            try {
                mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(path));
                mediaPlayer.prepare();

            /*mediaPlayer.setOnCompletionListener((MediaPlayer.OnCompletionListener) player -> {

                if (player.isPlaying()) {
                    player.pause();
                    player.seekTo(0);
                }
                //player.stop();
                //player.reset();

            });*/

            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayers.add(mediaPlayer);
            mediaPlayer.start();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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


}