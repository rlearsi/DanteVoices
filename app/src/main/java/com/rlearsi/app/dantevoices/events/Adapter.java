package com.rlearsi.app.dantevoices.events;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.rlearsi.app.dantevoices.InterfaceUpdates;
import com.rlearsi.app.dantevoices.R;

import java.io.IOException;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Holder> {

    private List<ItemsEvents> topics;
    private int type;
    private Context context;
    private long date;
    private String current_week;
    private int ciclo_menstrual = 0;
    private InterfaceUpdates interfaceUpdates;

    public Adapter(List<ItemsEvents> topics, InterfaceUpdates interfaceUpdates, Context context) {
        this.topics = topics;
        this.context = context;
        this.interfaceUpdates = interfaceUpdates;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int view = R.layout.list_voices;

        return new Holder(LayoutInflater.from(parent.getContext())
                .inflate(view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {

        final ItemsEvents topic = topics.get(position);

        //Log.i(TAG, "CICLO: " + ciclo_menstrual);

        final String title = topic.getTitle();
        final int id = topic.getId();

        holder.title_voice.setText(title);

        //Formata "x Semanas"
        //String x_weeks = String.format(Locale.ENGLISH, "%s%s%s", "<b>", get_x_weeks, "</b>. ");

        //holder.box.setBackground(ContextCompat.getDrawable(context, R.drawable.box_square));
        //holder.eventTitle.setTextColor(ContextCompat.getColor(context, R.color.colorHint));

        //holder.box.setOnClickListener(v -> interfaceUpdates.handleItems(id, title));

        holder.box.setOnClickListener(v -> {

            holder.play_icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pause_24));

            interfaceUpdates.handleItems(position, id, title);

            /*String path = "android.resource://" + context.getPackageName() + "/raw/" + title;

            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
            try {
                mediaPlayer.setDataSource(context.getApplicationContext(), Uri.parse(path));
                mediaPlayer.prepare();
                //mediaPlayer.setOnSeekCompleteListener((MediaPlayer.OnSeekCompleteListener) player -> {});

                mediaPlayer.setOnCompletionListener((MediaPlayer.OnCompletionListener) player -> {
                    //player.stop();
                    holder.play_icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_play_circle_outline_24));
                    // next audio file
                });

                mediaPlayer.start();

            } catch (IOException e) {
                e.printStackTrace();
            }*/

        });

    }

    @Override
    public int getItemCount() {
        return topics != null ? topics.size() : 0;
    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

}
