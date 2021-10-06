package com.to_panelka.webnote.ui.newPost;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.to_panelka.webnote.R;

public class PostCreateFragment extends Fragment {

  private PostCreateViewModel mViewModel;



  public static PostCreateFragment newInstance() {
    return new PostCreateFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_post_create, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(PostCreateViewModel.class);
    // TODO: Use the ViewModel
  }

}