package com.to_panelka.webnote.ui.settingsProfile;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.Transaction.Function;
import com.to_panelka.webnote.model.UserModel;
import java.util.Objects;

public class SettingsViewModel extends ViewModel {
  private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
  private FirebaseAuth auth = FirebaseAuth.getInstance();
  private MutableLiveData<UserModel> userModelMutableLiveData;
  public LiveData<UserModel> getUser(){
    if(userModelMutableLiveData == null){
      userModelMutableLiveData = new MutableLiveData<UserModel>();
      firestore.collection("Users").
          document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).get().
          addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
              if (task.isSuccessful()) {
                DocumentSnapshot firebaseDoc = task.getResult();
                UserModel user = new UserModel(firebaseDoc.getId().toString(),
                    firebaseDoc.getString("username").toString(),
                    firebaseDoc.getString("email").toString(),
                    firebaseDoc.getString("description").toString());
                userModelMutableLiveData.postValue(user);
              }
            }
          });
    }
    return userModelMutableLiveData;

  }
  public void updatesUserInfo (String username, String email, String description){
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final DocumentReference doc = firestore.collection("Users").
            document(Objects.requireNonNull(auth.getCurrentUser()).getUid());
        firestore.runTransaction(new Function<Void>() {
          @Override
          public Void apply(Transaction transaction) throws FirebaseFirestoreException{
            transaction.update(doc,"username",username);
            transaction.update(doc,"email", email);
            auth.getCurrentUser().updateEmail(email);
            transaction.update(doc,"description",description);
            return null;
          }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
          @Override
          public void onSuccess(Void unused) {
            Log.d("DATABASE_USER","Transaction success");
          }
        }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
            Log.d("DATABASE_USER","Transaction faild");
          }
        });
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }

  public void logOut()
  {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        auth.signOut();
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }



}