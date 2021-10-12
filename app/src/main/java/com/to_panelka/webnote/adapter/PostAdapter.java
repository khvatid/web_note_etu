package com.to_panelka.webnote.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.model.PostModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<PostModel> posts;

    public PostAdapter (Context context, List<PostModel> posts)
    {
        this.posts = posts;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder holder, int position) {
        PostModel postModel = posts.get(position);
        holder.idUser.setText(postModel.getUserId());
        holder.textPost.setText(postModel.getText());
        holder.time.setText(postModel.getTime());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView idUser;
        final TextView textPost;
        final TextView time;

        ViewHolder(View view){
            super(view);
            idUser = (TextView)view.findViewById(R.id.post_user_nickname);
            textPost = (TextView)view.findViewById(R.id.post_user_text);
            time = (TextView)view.findViewById(R.id.post_user_time);
        }
    }
}