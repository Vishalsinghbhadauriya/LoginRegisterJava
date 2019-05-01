package com.example.loginregisterjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.loginregisterjava.adapters.CustomAdapter;
import com.example.loginregisterjava.modal.Category;
import com.example.loginregisterjava.modal.Example;
import com.example.loginregisterjava.network.GetDataService;
import com.example.loginregisterjava.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatagoryActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private List<Category> categoryList;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        init();
        onDataList();


    }
  private void   init(){
      progressBar = (ProgressBar)findViewById(R.id.progressbar);
      progressBar.setVisibility(View.VISIBLE);
      categoryList=new ArrayList<>();
      recyclerView = findViewById(R.id.recyclerviewone);
      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CatagoryActivity.this);
      recyclerView.setLayoutManager(layoutManager);
    }
    public void onDataList(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Example> call = service.getCategories();

        call.enqueue(new Callback<Example>(){

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                // progressDoalog.dismiss();
                progressBar.setVisibility(View.GONE);
                categoryList=response.body().getCategories();
                for (int i=0;i<categoryList.size();i++){
                    String id = categoryList.get(i).getId();
                    String name = categoryList.get(i).getName();
                    String icon = categoryList.get(i).getIcon();
                }

                generateDataList(categoryList);


            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                // progressDoalog.dismiss();
                progressBar.setVisibility(View.GONE);

                Toast.makeText(CatagoryActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList(List<Category> body) {
        adapter = new CustomAdapter(this, body);

        recyclerView.setAdapter(adapter);
    }
}
