package com.to_panelka.webnote.ui.comments;

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
import com.to_panelka.webnote.model.CommentModel;
import com.to_panelka.webnote.model.PostModel;

public class CommentsViewModel extends ViewModel {


  private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
  private FirebaseAuth auth = FirebaseAuth.getInstance();
  private MutableLiveData<String> textComment;
  public LiveData<String> getTextComment(){
    if(textComment == null){
      textComment = new MutableLiveData<>();
    }
    return textComment;
  }
  public void setTextComment(String text)
  {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        textComment.postValue(text);
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }



  public void addComment(String idPosts)
  {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        firestore.runTransaction(new Function<Void>() {
          final DocumentReference docComment = firestore.collection("Posts").
              document(idPosts).collection("Comments").document();
          final DocumentReference docUser = firestore.collection("Users").
              document(auth.getCurrentUser().getUid());
          @Override
          public Void apply(Transaction transaction) throws FirebaseFirestoreException {
            DocumentSnapshot snapshotUser = transaction.get(docUser);
            DocumentSnapshot snapshotComment = transaction.get(docComment);
            transaction.set(docComment,new CommentModel());
            transaction.update(docComment,"idComment",snapshotComment.getId());
            transaction.update(docComment,"nameUser",snapshotUser.get("username"));
            transaction.update(docComment,"idUser", snapshotUser.get("userid"));
            transaction.update(docComment,"textComment", textComment.getValue());
            return null;
          }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
          @Override
          public void onSuccess(Void unused) {
            setTextComment("");
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