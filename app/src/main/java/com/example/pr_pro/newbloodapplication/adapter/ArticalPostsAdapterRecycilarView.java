package com.example.pr_pro.newbloodapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.data.model.posts.PostsDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticalPostsAdapterRecycilarView extends RecyclerView.Adapter<ArticalPostsAdapterRecycilarView.ViewHolder> {

    private List<PostsDatum> postsDatumList = new ArrayList<>();
    private Context context;
    boolean isFavorite;

    public ArticalPostsAdapterRecycilarView(List<PostsDatum> postsDatumList, Context context) {
        this.postsDatumList = postsDatumList;
        this.context = context;

    }


    @NonNull
    @Override
    public ArticalPostsAdapterRecycilarView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View postItemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.artical_posts_recycler_view_item, viewGroup, false);
        return new ArticalPostsAdapterRecycilarView.ViewHolder(postItemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ArticalPostsAdapterRecycilarView.ViewHolder holder, int position) {
        PostsDatum postsDatum = postsDatumList.get(position);
        holder.postArticleTextView.setText(postsDatum.getTitle());
        Glide.with(context)
                .load(postsDatum.getThumbnailFullPath())
                .into(holder.postArticalImageView);
        holder.favoritePostButton.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.ic_favorit));
    }


    @Override
    public int getItemCount() {
        return postsDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_article_text_view)
        TextView postArticleTextView;
        @BindView(R.id.favorite_post_button)
        ToggleButton favoritePostButton;
        @BindView(R.id.post_artical_image_view)
        ImageView postArticalImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
