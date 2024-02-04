package me.grocery.grocerylist.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.grocery.grocerylist.CategoriesAdapter;
import me.grocery.grocerylist.CategoryModel;
import me.grocery.grocerylist.ItemModel;
import me.grocery.grocerylist.R;

public class HomeFragment extends Fragment {

    private RecyclerView categoriesRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        categoriesRecyclerView = root.findViewById(R.id.categoriesRecyclerView);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(horizontalLayoutManager);

        // Prepare mock data
        ArrayList<CategoryModel> categories = prepareMockData();

        // Set up the categories adapter
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), categories);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        return root;
    }

    private ArrayList<CategoryModel> prepareMockData() {
        ArrayList<CategoryModel> categories = new ArrayList<>();

        // Mock categories
        String[] categoryNames = {"Fruits", "Vegetables", "Dairy"};

        // Mock items for each category
        ArrayList<ItemModel> fruits = new ArrayList<>();
        fruits.add(new ItemModel("Apple"));
        fruits.add(new ItemModel("Banana"));
        fruits.add(new ItemModel("Cherry"));

        ArrayList<ItemModel> vegetables = new ArrayList<>();
        vegetables.add(new ItemModel("Carrot"));
        vegetables.add(new ItemModel("Lettuce"));
        vegetables.add(new ItemModel("Pepper"));

        ArrayList<ItemModel> dairy = new ArrayList<>();
        dairy.add(new ItemModel("Milk"));
        dairy.add(new ItemModel("Cheese"));
        dairy.add(new ItemModel("Yogurt"));

        // Adding categories
        categories.add(new CategoryModel(categoryNames[0], fruits));
        categories.add(new CategoryModel(categoryNames[1], vegetables));
        categories.add(new CategoryModel(categoryNames[2], dairy));

        return categories;
    }
}
