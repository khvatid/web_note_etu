package com.to_panelka.webnote.adapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.Timestamp;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.ui.comments.CommentsFragment;
import com.to_panelka.webnote.ui.feed.FeedFragment;
import com.to_panelka.webnote.ui.profile.ProfileFragment;

public class PostViewHolder extends RecyclerView.ViewHolder{
  private View defaultView;
  private String idPost;

  public PostViewHolder(View view){
    super(view);
    defaultView = view;
    Button button = view.findViewById(R.id.itm_post_btn_comment);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        Fragment fragment = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idPost",idPost);
        fragment.setArguments(bundle);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, fragment).addToBackStack("Comments").commit();
      }
    });
  }
  public void setDataPost(String idPost,String textPost, String idUser, Timestamp time)
  {
    this.idPost = idPost;
    TextView viewTextPost = defaultView.findViewById(R.id.post_user_text);
    viewTextPost.setText(textPost);
    TextView viewIdUser = defaultView.findViewById(R.id.post_user_nickname);
    viewIdUser.setText(idUser);
    TextView viewTime = defaultView.findViewById(R.id.post_user_time);
    viewTime.setText(time.toDate().toString());
  }
}
