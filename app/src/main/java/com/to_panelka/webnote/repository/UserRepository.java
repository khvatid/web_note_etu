package com.to_panelka.webnote.repository;

import androidx.annotation.Nullable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.to_panelka.webnote.model.UserModel;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

  FirebaseFirestore firestore = FirebaseFirestore.getInstance();
  OnUserAvaialbleInFireStore interfaceofuser;
  String userid;
  List<UserModel> userModelList = new ArrayList<>();
  FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

  public UserRepository(
      OnUserAvaialbleInFireStore interfaceofuser) {
    this.interfaceofuser = interfaceofuser;
  }


  public void getUserInFireStore(){

    userid = firebaseAuth.getCurrentUser().getUid();

    firestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
      @Override
      public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
        userModelList.clear();

        assert value != null;
        for (DocumentSnapshot ds:
            value.getDocuments()) {
          UserModel userModel = ds.toObject(UserModel.class);
          assert userModel !=null;
          if (!userid.equals(userModel.getId())){
            userModelList.add(userModel);
            interfaceofuser.ShowListOfUser(userModelList);
          }
        }
      }
    });
  }


  public interface OnUserAvaialbleInFireStore {
    void ShowListOfUser(List<UserModel> userModelList);
  }
}



