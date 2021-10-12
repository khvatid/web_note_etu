package com.to_panelka.webnote.ui.newPost;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.to_panelka.webnote.R;

import com.to_panelka.webnote.model.PostModel;
import java.util.HashMap;
import java.util.Objects;

public class PostCreateFragment extends Fragment {

  private String userid;
  private EditText et_text;
  private Button btnPost;
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
    firestore = FirebaseFirestore.getInstance();

    et_text = view.findViewById(R.id.create_post_et_view);
    btnPost = view.findViewById(R.id.create_post_btn);

    btnPost.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        AddPost(et_text.getText().toString());

      }
    });
  }
    private void AddPost(String text) {

      auth = FirebaseAuth.getInstance();
      firestore = FirebaseFirestore.getInstance();
      userid = Objects.requireNonNull(auth.getCurrentUser()).getUid();
      Timestamp time = Timestamp.now();

      HashMap<String, Object> hashMap = new HashMap<>();
      hashMap.put("idPost", userid);
      hashMap.put("idUser", userid);
      hashMap.put("textPost", text);
      hashMap.put("timePublish",time);

      HashMap<String, Object> mapComment = new HashMap<>();
      mapComment.put("userid", new String("testUser"));
      mapComment.put("commentText",new String("testText"));
      hashMap.put("comments", mapComment);
      firestore.collection("Posts").add(hashMap).addOnSuccessListener(
        new OnSuccessListener<DocumentReference>() {
        @Override
        public void onSuccess(DocumentReference documentReference) {
          documentReference.update("idPost",documentReference.getId());
          Toast.makeText(getContext(), "id" + documentReference.getId(), Toast.LENGTH_SHORT).show();
        }
      });
      et_text.setText("");
    }

}