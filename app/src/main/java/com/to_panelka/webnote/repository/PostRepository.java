package com.to_panelka.webnote.repository;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.to_panelka.webnote.model.PostModel;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

  FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
  FirebaseFirestore firestore = FirebaseFirestore.getInstance();
  private ArrayList<PostModel> postModelList = new ArrayList<>();

  public void setPostModelList(ArrayList<PostModel> postModelList) {
    this.postModelList = postModelList;
  }

  public ArrayList<PostModel> getPostModelList() {
    return postModelList;
  }
}
