package me.grocery.grocerylist;

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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<TextModel> textModels;

    public RecyclerViewAdapter(HomeFragment context, ArrayList<TextModel> textModels){
            this.context=context.getContext();
            this.textModels=textModels;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.recycler_view_row, parent,false);

        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.edittext.setText(textModels.get(position).getEditBox());
    }

    @Override
    public int getItemCount() {
        return textModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        EditText edittext;
        TextView textbox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            edittext= itemView.findViewById(R.id.edittextid);

        }
    }
}
