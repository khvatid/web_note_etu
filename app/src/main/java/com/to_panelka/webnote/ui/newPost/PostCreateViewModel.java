package com.to_panelka.webnote.ui.newPost;

import android.content.res.Resources.Theme;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.Transaction.Function;
import com.to_panelka.webnote.model.PostModel;
import com.to_panelka.webnote.model.UserModel;
import java.util.Objects;

public class PostCreateViewModel extends ViewModel {

  private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
  private FirebaseAuth auth = FirebaseAuth.getInstance();

  private MutableLiveData<String> postText;

  public LiveData<String> getString(){
    if(postText == null)
    {
      postText = new MutableLiveData<>();
    }
    return postText;
  }

  public void setPostText(String text){
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        postText.postValue(text);
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }

  public void addPost()
  {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        firestore.runTransaction(new Function<Void>() {
          Timestamp timestamp = Timestamp.now();
          final DocumentReference docPost = firestore.collection("Posts").document();
          final DocumentReference docUser = firestore.collection("Users").document(auth.getCurrentUser().getUid());
          @Override
          public Void apply(Transaction transaction) throws FirebaseFirestoreException {
            DocumentSnapshot snapshotUser = transaction.get(docUser);
            DocumentSnapshot snapshotPost = transaction.get(docPost);
            transaction.set(docPost,new PostModel());
            transaction.update(docPost,"idPost",snapshotPost.getId());
            transaction.update(docPost,"nameUser",snapshotUser.get("username"));
            transaction.update(docPost,"idUser", snapshotUser.get("userid"));
            transaction.update(docPost,"textPost",postText.getValue());
            transaction.update(docPost,"timePublish",timestamp);
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



}