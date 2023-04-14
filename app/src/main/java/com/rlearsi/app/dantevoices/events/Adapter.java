package com.rlearsi.app.dantevoices.events;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.rlearsi.app.dantevoices.InterfaceUpdates;
import com.rlearsi.app.dantevoices.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Holder> {

    private List<Items> events;
    private int type;
    private Context context;
    private long date;
    private String current_week;
    private int ciclo_menstrual = 0;
    private InterfaceUpdates interfaceUpdates;

    public Adapter(List<Items> events, InterfaceUpdates interfaceUpdates, Context context) {
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

        final Items topic = events.get(position);

        //Log.i(TAG, "CICLO: " + ciclo_menstrual);

        final String name = topic.getName();
        final int id = topic.getId();
        final String file_name = topic.getFileName();
        final String color = topic.getColor();
        final boolean isLoop = topic.getLoop();

        holder.title_voice.setText(name);

        //set default color
        int contextColor = ContextCompat.getColor(context, R.color.black_002);
        //holder.box.setBackground(ContextCompat.getDrawable(context, R.drawable.box_square));

        if (holder.backgroundDrawable instanceof LayerDrawable) {

            LayerDrawable layerDrawable = (LayerDrawable) holder.backgroundDrawable;
            GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.gradient_button);
            gradientDrawable.setColors(new int[]{contextColor, contextColor});

        } /*else {

            holder.gradientDrawable.setColor(contextColor);
            holder.box.setBackground(holder.gradientDrawable);

        }*/

        holder.box.setOnClickListener(v -> {

            //holder.box.setAlpha(0.8f);

            if (holder.backgroundDrawable instanceof LayerDrawable) {

                LayerDrawable layerDrawable = (LayerDrawable) holder.backgroundDrawable;
                GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.gradient_button);
                gradientDrawable.setColors(new int[]{Color.parseColor(color), Color.parseColor(color)});

            } /*else {

                holder.gradientDrawable.setColor(Color.parseColor(color));
                holder.box.setBackground(holder.gradientDrawable);

            }*/

            interfaceUpdates.handleItems(position, id, name, file_name, color, isLoop);

        });

    }

    public void addTopic(Items event) {

        events.add(getItemCount(), event);
        notifyItemInserted(getItemCount());

    }

    public void updateRow(Items event) {

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
