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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import me.grocery.grocerylist.RecyclerViewAdapter;
import me.grocery.grocerylist.TextModel;
import me.grocery.grocerylist.databinding.FragmentHomeBinding;

import me.grocery.grocerylist.R;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    ArrayList<String> categories = new ArrayList<String>();
    ArrayList<ArrayList<String>> items = new  ArrayList<ArrayList<String>>();

    private JSONObject data;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getContext() != null){
            Context context = getContext();
            StringBuilder jsonStringBuilder = new StringBuilder();
            try {
                // Step 1: Open and read the file
                FileInputStream fis = getContext().openFileInput("groceryItems.json"); // Replace getContext() with getActivity() if in a fragment
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonStringBuilder.append(line);
                }
                bufferedReader.close();

                // Step 2: Parse the content into a JSONObject
                JSONObject jsonObject = new JSONObject(jsonStringBuilder.toString());

                // Step 3: Iterate over the JSONObject
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    if (key.equalsIgnoreCase("advice"))
                        continue;//TODO: add advice logic
                    Object item = jsonObject.get(key);
                    categories.add(key);
                    if (item instanceof JSONArray) {
                        JSONArray value = (JSONArray) item;
                        ArrayList<String> temp = new ArrayList<String>();
                        for(int i = 0; i < value.length(); i++) {
                            Object element = value.get(i);

                            if (element instanceof JSONObject) {
                                JSONObject trueElement = (JSONObject) element;
                                Log.d("NOTSTRING IN JSON:", trueElement.toString());
                            } else if (element instanceof String) {
                                String trueElement = (String) element;

                                temp.add(trueElement);
                            }
                        }
                        items.add(temp);
                    } else {
                        //Perhaps throw exception
                    }



                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                // Handle the case where the file doesn't exist
            } catch (IOException e) {
                e.printStackTrace();
                // Handle I/O errors
            } catch (JSONException e) {
                e.printStackTrace();
                // Handle JSON parsing errors
            }

        }


        RecyclerView recyclerView= root.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<TextModel>textModels=new ArrayList<>();

        setUpTextModels(textModels);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,textModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        return root;
    }


    private void setUpTextModels(ArrayList<TextModel>textModels){

        for (int i = 0; i < items.size(); i++) {
            Log.d("Category:", categories.get(i));
            for(int j = 0; j < items.get(i).size(); j++)
            {
                Log.d("Item:", items.get(i).get(j));
                textModels.add(new TextModel(items.get(i).get(j)));
            }

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}