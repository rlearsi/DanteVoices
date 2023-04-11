package com.rlearsi.app.dantevoices.events;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rlearsi.app.dantevoices.R;

class Holder extends RecyclerView.ViewHolder {

    ConstraintLayout box;
    TextView title_voice;
    //ImageView play_icon;

    Holder(View itemView) {

        super(itemView);

        title_voice = itemView.findViewById(R.id.title_voice);
        //play_icon = itemView.findViewById(R.id.play_icon);
        box = itemView.findViewById(R.id.box);

    }

}