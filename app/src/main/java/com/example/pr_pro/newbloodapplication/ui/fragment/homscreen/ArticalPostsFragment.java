package com.example.pr_pro.newbloodapplication.ui.fragment.homscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.adapter.ArticalPostsAdapterRecycilarView;
import com.example.pr_pro.newbloodapplication.data.model.posts.Posts;
import com.example.pr_pro.newbloodapplication.data.model.posts.PostsDatum;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;
import com.example.pr_pro.newbloodapplication.helper.EndlessRecyclerOnScrol;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.error;
import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.verbose;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticalPostsFragment extends Fragment {

    private static final String API_TOKEN = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
    int max;
    @BindView(R.id.articalposts_recycler_view)
    RecyclerView articalpostsRecyclerView;
    Unbinder unbinder;



    private ModelApiServices modelApiServices;


    public ArticalPostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artical_posts, container, false);
        unbinder = ButterKnife.bind(this, view);
        modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);
        setPostRecyclerView();
        getPosts(1);

        return view;
    }


    public void getPosts(int page) {

        modelApiServices.getPosts(API_TOKEN, page).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                verbose("response raw: " + response.raw());
                List<PostsDatum> data = response.body().getData().getData();
                articalpostsRecyclerView.setAdapter(new ArticalPostsAdapterRecycilarView(data, getContext()));


            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                error("Posts OnFailoure" + t.getMessage());

            }
        });
    }
    @Deprecated
    private void setPostRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        articalpostsRecyclerView.setHasFixedSize(true);
        articalpostsRecyclerView.setLayoutManager(manager);

        articalpostsRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrol(manager) {

            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= max) {
                    max = current_page;
                    getPosts(current_page);

                }

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
