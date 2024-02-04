package me.grocery.grocerylist;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<ItemModel> items;

    private ItemsAdapterListener listener;
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_ADD_BUTTON = 1;
    private Context context;

    public ItemsAdapter(Context context,ArrayList<ItemModel> items, ItemsAdapterListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }
    public interface ItemsAdapterListener {
        void onSaveItems();
    }
    @Override
    public int getItemViewType(int position) {
        return (position == items.size()) ? VIEW_TYPE_ADD_BUTTON : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_item_button_layout, parent, false);
            return new AddItemViewHolder(view);
        }
    }
    private void saveItems() {
        if (listener != null) {
            listener.onSaveItems();
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            ItemModel item = items.get(position);
            ((ItemViewHolder) holder).itemName.setText(item.getItemName());

            ((ItemViewHolder) holder).itemRadio.setOnClickListener(v -> {
                // Remove item logic
                items.remove(position);
                saveItems();
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, items.size());

            });
            // Set click listener for editing
            holder.itemView.setOnClickListener(v -> {
                final EditText editText = new EditText(context);
                editText.setText(item.getItemName());
                editText.selectAll();

                new AlertDialog.Builder(context)
                        .setTitle("Edit Item")
                        .setView(editText)
                        .setPositiveButton("Save", (dialog, which) -> {
                            String newText = editText.getText().toString();
                            item.setItemName(newText);
                            notifyItemChanged(position);
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            });
            saveItems();
        } else {
            // Handle the Add button click event
            holder.itemView.setOnClickListener(v -> {
                final EditText editText = new EditText(context);
                new AlertDialog.Builder(context)
                        .setTitle("Add New Item")
                        .setView(editText)
                        .setPositiveButton("Add", (dialog, which) -> {
                            String itemName = editText.getText().toString();
                            if (!itemName.isEmpty()) {
                                items.add(new ItemModel(itemName));
                                notifyItemInserted(items.size() - 1);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            });
            saveItems();
        }
    }

    @Override
    public int getItemCount() {
        return items.size() + 1; // +1 for the Add button
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        RadioButton itemRadio;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemNameTextView);
            itemRadio = itemView.findViewById(R.id.itemRadioButton);
        }
    }

    static class AddItemViewHolder extends RecyclerView.ViewHolder {
        public AddItemViewHolder(View itemView) {
            super(itemView);
            // itemView is your add item button layout
        }
    }
}
