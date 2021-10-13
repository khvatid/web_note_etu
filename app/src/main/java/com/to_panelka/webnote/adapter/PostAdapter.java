package com.to_panelka.webnote.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.Timestamp;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.model.PostModel;

import java.util.List;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private View defaultView;

        public ViewHolder(View view){
            super(view);
            defaultView = view;
        }
        public void setTextPost(String textPost, String idUser, Timestamp time)
        {

            TextView viewTextPost = defaultView.findViewById(R.id.post_user_text);
            viewTextPost.setText(textPost);
            TextView viewIdUser = defaultView.findViewById(R.id.post_user_nickname);
            viewIdUser.setText(idUser);
            TextView viewTime = defaultView.findViewById(R.id.post_user_time);
            viewTime.setText(time.toDate().toString());

        }

    }
}