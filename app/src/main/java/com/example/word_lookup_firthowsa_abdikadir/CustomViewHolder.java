package com.example.word_lookup_firthowsa_abdikadir;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
class CustomViewHolder extends RecyclerView.ViewHolder {

    private TextView translated;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        translated = itemView.findViewById(R.id.translated);
    }

    public TextView getTranslated() {
        return translated;
    }
}
