package com.to_panelka.webnote.ui.profile;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.to_panelka.webnote.MainActivity;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.adapter.PostAdapter;

import com.to_panelka.webnote.model.PostModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ProfileFragment extends Fragment {

  private ProfileViewModel mViewModel;
  private FirebaseAuth auth;
  private FirebaseFirestore firestore;
  private TextView userName;
  private TextView userDescription;
  private Button createPostButton;
  NavController navController;

  private ArrayList<PostModel> postModels = new ArrayList<PostModel>();

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_profile, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    userName = view.findViewById(R.id.profile_user_name);
    userDescription = view.findViewById(R.id.profile_user_description);
    createPostButton = view.findViewById(R.id.profile_btn_create_post);
    RecyclerView recyclerView = (RecyclerView) view.findViewById((R.id.profile_post_container));
    PostAdapter adapter = new PostAdapter(getActivity(), postModels);
    recyclerView.setAdapter(adapter);


    navController = Navigation.findNavController(view);
    createPostButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
         navController.navigate(R.id.action_navigation_profile_to_navigation_new_post);
      }
    });
    auth = FirebaseAuth.getInstance();
    firestore = FirebaseFirestore.getInstance();
    firestore.collection("Users").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
      @Override
      public void onComplete(@NonNull Task<DocumentSnapshot> task) {

        if (task.isSuccessful()) {

          DocumentSnapshot firebaseDoc = task.getResult();
          userName.setText(firebaseDoc.getString("username"));
          userDescription.setText(firebaseDoc.getString("description"));
        }
      }
    });



    /*firestore.collection("Posts").whereEqualTo("idUser", auth.getCurrentUser()).get().addOnCompleteListener(
        new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if(task.isSuccessful())
            {
              for (QueryDocumentSnapshot document: task.getResult()) {
                postModels.add(new PostModel(document.getId(),document.get("idUser").toString(),document.get("textPost").toString(),document.get("timePublish").toString()));
              }
            }
          }
        });*/


  }




}