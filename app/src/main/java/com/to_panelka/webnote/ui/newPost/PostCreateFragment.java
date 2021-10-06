package com.to_panelka.webnote.ui.newPost;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.to_panelka.webnote.R;

import java.util.HashMap;
import java.util.Objects;

public class PostCreateFragment extends Fragment {

  private String text, userid;
  private EditText et_text;
  private Button post;
  Timestamp time;
  private FirebaseAuth firebaseAuth;
  private FirebaseFirestore firestore;
  private FirebaseAuth auth;
  private String postid;

  public PostCreateFragment() {
    // Required empty public constructor
  }

  public static PostCreateFragment newInstance() {
    return new PostCreateFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_post_create, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    firebaseAuth = FirebaseAuth.getInstance();
    firestore = FirebaseFirestore.getInstance();

    et_text = view.findViewById(R.id.create_post_et_view);
    post = view.findViewById(R.id.create_post_btn);

    post.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        text = et_text.getText().toString();
        time = Timestamp.now();
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Users").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
          @Override
          public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            if (task.isSuccessful()) {

              DocumentSnapshot firebaseDoc = task.getResult();
              userid = firebaseDoc.getString("userid");
            }
          }
        });
        AddPost(text, time, userid);
      }
    });
  }
    private void AddPost(String text, Timestamp time, String userid) {
           /* HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("", postid);
            hashMap.put("", userid);
            hashMap.put("", text);
            hashMap.put("",time);*/



  }

}