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

import androidx.lifecycle.ViewModelProvider;
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
  private PostCreateViewModel mViewModel;
  public PostCreateFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_post_create, container, false);
  }

  @Override
  public void onStop() {
    mViewModel.setPostText(et_text.getText().toString());
    super.onStop();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(PostCreateViewModel.class);
    et_text = view.findViewById(R.id.create_post_et_view);
    btnPost = view.findViewById(R.id.create_post_btn);

    mViewModel.getString().observe(getViewLifecycleOwner(), String ->{
      et_text.setText(String);
    });

    btnPost.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mViewModel.setPostText(et_text.getText().toString());
        mViewModel.addPost();
      }
    });
  }


}