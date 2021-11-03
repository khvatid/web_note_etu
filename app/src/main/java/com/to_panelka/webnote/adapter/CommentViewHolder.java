package com.to_panelka.webnote.adapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.Timestamp;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.ui.comments.CommentsFragment;

public class CommentViewHolder extends RecyclerView.ViewHolder{
  private View defaultView;

  public CommentViewHolder(View view){
    super(view);
    defaultView = view;
  }
  public void setDataComment(String usernameComment, String textComment)
  {
    TextView usernameTextView = defaultView.findViewById(R.id.cmnt_tv_username);
    usernameTextView.setText(usernameComment);
    TextView commentTextView = defaultView.findViewById(R.id.cmnt_tv_text);
    commentTextView.setText(textComment);
  }
}
