package me.grocery.grocerylist;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.grocery.grocerylist.ui.home.HomeFragment;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<TextModel> textModels;

    public RecyclerViewAdapter(HomeFragment context, ArrayList<TextModel> textModels){
            this.context=context.getContext();
            this.textModels=textModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_squircle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        holder.textView.setText(textModels.get(position).getEditBox());
    }

    @Override
    public int getItemCount() {
        return textModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewItem);

            textView.setOnClickListener(v -> {
                // Open AlertDialog with EditText to edit text
                Context context = v.getContext();
                EditText editText = new EditText(context);
                editText.setText(textView.getText());

                new AlertDialog.Builder(context)
                        .setTitle("Edit Item")
                        .setView(editText)
                        .setPositiveButton("Save", (dialog, which) -> {
                            textView.setText(editText.getText().toString());
                            // Here, you should also update the data model behind the adapter
                            // so that the changes are not lost on scroll.
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            });
        }
    }
}
