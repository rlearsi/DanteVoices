package com.rlearsi.app.dantevoices.events;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rlearsi.app.dantevoices.R;

class Holder extends RecyclerView.ViewHolder {

    ConstraintLayout box;
    TextView title_voice;
    //GradientDrawable gradientDrawable;
    Drawable backgroundDrawable;

    Holder(View itemView) {

        super(itemView);

        title_voice = itemView.findViewById(R.id.title_voice);
        box = itemView.findViewById(R.id.box);

        //gradientDrawable = (GradientDrawable) box.getBackground();
        backgroundDrawable = box.getBackground();


    }

}