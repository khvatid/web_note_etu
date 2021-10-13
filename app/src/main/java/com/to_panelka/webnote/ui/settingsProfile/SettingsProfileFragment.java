package com.to_panelka.webnote.ui.settingsProfile;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.to_panelka.webnote.R;

public class SettingsProfileFragment extends Fragment {

  private SettingsProfileViewModel mViewModel;

  public static SettingsProfileFragment newInstance() {
    return new SettingsProfileFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_settings_profile, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(SettingsProfileViewModel.class);
    // TODO: Use the ViewModel
  }

}