package com.to_panelka.webnote.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.Timestamp;
import com.to_panelka.webnote.R;

public class PostViewHolder extends RecyclerView.ViewHolder{
  private View defaultView;

  public PostViewHolder(View view){
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
