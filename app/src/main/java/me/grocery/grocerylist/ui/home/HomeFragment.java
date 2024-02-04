package me.grocery.grocerylist.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.grocery.grocerylist.CategoriesAdapter;
import me.grocery.grocerylist.CategoryModel;
import me.grocery.grocerylist.ItemModel;
import me.grocery.grocerylist.R;
import me.grocery.grocerylist.SplashActivity;

public class HomeFragment extends Fragment {

    private RecyclerView categoriesRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        categoriesRecyclerView = root.findViewById(R.id.categoriesRecyclerView);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(horizontalLayoutManager);

        // Prepare mock data
        ArrayList<CategoryModel> categories = prepareData();

        // Set up the categories adapter
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), categories);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(categoriesRecyclerView);
        return root;
    }

    private ArrayList<CategoryModel> prepareData() {
        ArrayList<CategoryModel> categories = new ArrayList<>();

        try {
            String jsonData =
                    new String(Files.readAllBytes(Paths.get(getContext().getFilesDir() + "/groceryItems.json")));

            JSONObject jsonObject = new JSONObject(jsonData);
            Iterator<String> iterator = jsonObject.keys();

            while (iterator.hasNext()) {
                String key = iterator.next();

                if (!key.equals("advice")) {
                    JSONArray array = jsonObject.getJSONArray(key);
                    ArrayList<ItemModel> itemModelList = new ArrayList<>();

                    for (int i = 0; i < array.length(); i++) {
                        String food = array.getString(i);
                        itemModelList.add(new ItemModel(food.substring(0, 1).toUpperCase() + food.substring(1)));
                    }

                    categories.add(new CategoryModel(key.substring(0, 1).toUpperCase() + key.substring(1),
                            itemModelList));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }
}
