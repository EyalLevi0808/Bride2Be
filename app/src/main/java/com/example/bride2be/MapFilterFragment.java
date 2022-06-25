package com.example.bride2be;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.bride2be.adapters.ProductAdapter;
import com.example.bride2be.models.Model;
import com.example.bride2be.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFilterFragment extends Fragment {

    ArrayList<Product> productsArrayList;
    ProgressBar progressBar;
    int count = 0;
    Timer timer;
    String city;


    public MapFilterFragment() { }

    public static MapFilterFragment newInstance(String param1, String param2) {
        MapFilterFragment fragment = new MapFilterFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_filter, container, false);

        progressBar = view.findViewById(R.id.MapFilter_progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.MapFilterFragment_recycledView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ProductAdapter adapter = new ProductAdapter(getLayoutInflater());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            //the timer is run but the data is not view
            public void run() {
                count++;
                progressBar.setProgress(count);
                if(count == 100)
                {
                    timer.cancel();
                    Model.instance.getProductsByCity(city, new Model.GetProductsByCityListener() {
                        @Override
                        public void onComplete(List<Product> products) {
                            adapter.setData(products);
                            recyclerView.setAdapter(adapter);
                            productsArrayList = new ArrayList<Product>(products);
                        }
                    });
                }
            }
        };
        timer.schedule(timerTask,0,100);
        adapter.setOnClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String productId = productsArrayList.get(position).getId();
                NavDirections action = (NavDirections)ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(productId);
                Navigation.findNavController(view).navigate(action);
            }
        });
        return  view;
    }
}