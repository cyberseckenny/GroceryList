package me.grocery.grocerylist.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.grocery.grocerylist.RecyclerViewAdapter;
import me.grocery.grocerylist.TextModel;
import me.grocery.grocerylist.databinding.FragmentHomeBinding;

import me.grocery.grocerylist.R;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    
    public boolean SaveState= false;

    private JSONObject data;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getContext() != null){
            Context context = getContext();
            try {
                FileInputStream fis=context.openFileInput("groceryItems");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


        RecyclerView recyclerView= root.findViewById(R.id.recyclerView);

        ArrayList<TextModel>textModels=new ArrayList<>();

        setUpTextModels(textModels);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,textModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        return root;
    }


    private void setUpTextModels(ArrayList<TextModel>textModels){

        if (!SaveState) {
            String[] textBoxWord = getResources().getStringArray(R.array.myStringArray);
            Log.d("hello","hi");
            for (int i = 0; i < textBoxWord.length; i++) {
                textModels.add(new TextModel(textBoxWord[i]));
            }
            SaveState= true;
        }else{
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            String savedArrayString = preferences.getString("savedArrayKey", "[]");

            List<String> savedArray = new ArrayList<>(Arrays.asList(new Gson().fromJson(savedArrayString, String[].class)));
            for (int i = 0; i < savedArray.size(); i++) {
                textModels.add(new TextModel(savedArray.get(i)));
            }

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}