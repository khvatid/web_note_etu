package com.to_panelka.webnote.ui.profile;

import android.widget.EditText;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.to_panelka.webnote.R;
import java.util.Objects;

public class ProfileFragment extends Fragment {

  private ProfileViewModel mViewModel;
  FirebaseAuth auth;
  private RecyclerView PostsRecyclerView;

  public static ProfileFragment newInstance() {
    return new ProfileFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    initRecyclerView();
    return inflater.inflate(R.layout.fragment_profile, container, false);


  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
   // mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

    // TODO: Use the ViewModel
  }
  private void initRecyclerView() {
    PostsRecyclerView = PostsRecyclerView.findViewById(R.id.Users_posts);
  }
}