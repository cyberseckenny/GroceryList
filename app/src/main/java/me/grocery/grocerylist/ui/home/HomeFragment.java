package me.grocery.grocerylist.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.grocery.grocerylist.RecyclerViewAdapter;
import me.grocery.grocerylist.TextModel;
import me.grocery.grocerylist.databinding.FragmentHomeBinding;

import me.grocery.grocerylist.R;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView= root.findViewById(R.id.recyclerView);

        ArrayList<TextModel>textModels=new ArrayList<>();

        setUpTextModels(textModels);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,textModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        return root;
    }


    private void setUpTextModels(ArrayList<TextModel>textModels){
        String[] textBoxWord=getResources().getStringArray(R.array.myStringArray);

        for(int i=0;i<textBoxWord.length;i++){
            textModels.add(new TextModel(textBoxWord[i]));
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}