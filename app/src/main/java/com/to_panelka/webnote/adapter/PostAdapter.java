package com.to_panelka.webnote.adapter;

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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private static final String TWITTER_RESPONSE_FORMAT="EEE MMM dd HH:mm:ss ZZZZZ yyyy"; // Thu Oct 26 07:31:08 +0000 2017
    private static final String MONTH_DAY_FORMAT = "MMM d"; // Oct 26

    private List<PostModel> PostList = new ArrayList<>();

    public void setItems(Collection<PostModel> posts) {
        PostList.addAll(posts);
        notifyDataSetChanged();
    }

    public void clearItems() {
        PostList.clear();
        notifyDataSetChanged();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView creationDateTextView;
        private TextView contentTextView;

        public PostViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.post_user_nickname);
            creationDateTextView = itemView.findViewById(R.id.post_user_time);
            contentTextView = itemView.findViewById(R.id.post_user_text);
        }
        public void bind(PostModel post) {
            nameTextView.setText(post.getUserId());
            contentTextView.setText(post.getText());

            String creationDateFormatted = getFormattedDate(post.getTime());
            creationDateTextView.setText(creationDateFormatted);
        }
        private String getFormattedDate(String rawDate) {
            SimpleDateFormat utcFormat = new SimpleDateFormat(TWITTER_RESPONSE_FORMAT, Locale.ROOT);
            SimpleDateFormat displayedFormat = new SimpleDateFormat(MONTH_DAY_FORMAT, Locale.getDefault());
            try {
                Date date = utcFormat.parse(rawDate);
                return displayedFormat.format(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(PostList.get(position));
    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }

}
