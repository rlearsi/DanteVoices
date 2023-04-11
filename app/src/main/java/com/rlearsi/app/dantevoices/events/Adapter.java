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

    private List<ItemsEvents> events;
    private int type;
    private Context context;
    private long date;
    private String current_week;
    private int ciclo_menstrual = 0;
    private InterfaceUpdates interfaceUpdates;

    public Adapter(List<ItemsEvents> events, InterfaceUpdates interfaceUpdates, Context context) {
        this.events = events;
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

        final ItemsEvents topic = events.get(position);

        //Log.i(TAG, "CICLO: " + ciclo_menstrual);

        final String title = topic.getTitle();
        final int id = topic.getId();
        final String file_name = topic.getFileName();

        holder.title_voice.setText(title);

        //Formata "x Semanas"
        //String x_weeks = String.format(Locale.ENGLISH, "%s%s%s", "<b>", get_x_weeks, "</b>. ");

        //
        //holder.eventTitle.setTextColor(ContextCompat.getColor(context, R.color.colorHint));

        //holder.box.setOnClickListener(v -> interfaceUpdates.handleItems(id, title));

        holder.box.setBackground(ContextCompat.getDrawable(context, R.drawable.box_square));

        holder.box.setOnClickListener(v -> {

            //holder.play_icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pause_24));

            //holder.box.setAlpha(0.8f);
            holder.box.setBackground(ContextCompat.getDrawable(context, R.drawable.box_square_2));
            interfaceUpdates.handleItems(position, id, title, file_name);

        });

    }

    public void addTopic(ItemsEvents event) {

        events.add(getItemCount(), event);
        notifyItemInserted(getItemCount());

    }

    public void updateRow(ItemsEvents event) {

        int position = events.indexOf(event);
        events.set(position, event);

        //holder.box.setAlpha(0.5f);

        notifyItemChanged(position);

    }

    @Override
    public int getItemCount() {
        return events != null ? events.size() : 0;
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
