package me.grocery.grocerylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private final ArrayList<ItemModel> items;

    public ItemsAdapter(ArrayList<ItemModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel item = items.get(position);
        holder.itemName.setText(item.getItemName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemNameTextView);
        }
    }
}

