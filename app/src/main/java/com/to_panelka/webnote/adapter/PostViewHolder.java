package com.to_panelka.webnote.adapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
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

    Button delete_btn = view.findViewById(R.id.itm_post_btn_delete);
    delete_btn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Runnable runnable = new Runnable() {
          @Override
          public void run() {
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            firestore.collection("Posts").document(idPost)
                .collection("Comments").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                  @Override
                  public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                      for (QueryDocumentSnapshot document: task.getResult()) {
                        firestore.collection("Posts").document(idPost)
                            .collection("Comments").document(document.getId()).delete();
                      }
                    }
                  }
                });
            firestore.collection("Posts").document(idPost).delete();
          }
        };
        Thread thread = new Thread(runnable);
        thread.start();
      }
    });
  }


  public void setDataPost(String idPost,String textPost, String nameUser, Timestamp time, Boolean isUser)
  {
    if(isUser){
      Button delete = defaultView.findViewById(R.id.itm_post_btn_delete);
      delete.setVisibility(View.VISIBLE);
    }
    this.idPost = idPost;
    TextView viewTextPost = defaultView.findViewById(R.id.post_user_text);
    viewTextPost.setText(textPost);
    TextView viewIdUser = defaultView.findViewById(R.id.post_user_nickname);
    viewIdUser.setText(nameUser);
    TextView viewTime = defaultView.findViewById(R.id.post_user_time);
    viewTime.setText(time.toDate().toString());
  }
}
