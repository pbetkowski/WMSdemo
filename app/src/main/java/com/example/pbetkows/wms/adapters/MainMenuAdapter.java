package com.example.pbetkows.wms.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pbetkows.wms.R;
import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder> {

    private List<String> items;
    private Context context;

    public MainMenuAdapter(List<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MainMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_menu_item,
                viewGroup, false);
        return new MainMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuViewHolder mainMenuViewHolder, int i) {
        String item = items.get(i);
        mainMenuViewHolder.textView.setText(item);
        mainMenuViewHolder.imageView.setImageResource(R.drawable.settings_icon);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MainMenuViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public MainMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.mainMenuTextView);
            imageView = itemView.findViewById(R.id.mainMenuImageView);
        }
    }

}
