package com.to_panelka.webnote.ui.settingsProfile;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.to_panelka.webnote.AuthenticationActivity;
import com.to_panelka.webnote.MainActivity;
import com.to_panelka.webnote.R;

public class SettingsFragment extends Fragment {

  private SettingsViewModel mViewModel;

  EditText editTextMail;
  EditText editTextOldPass;
  EditText editTextNewPass;
  EditText editTextName;
  EditText editTextDescription;
  Button buttonSave;
  Button buttonLogOut;


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_settings, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
   editTextMail = view.findViewById(R.id.cd_et_email);
   editTextOldPass = view.findViewById(R.id.cd_et_old_password);
   editTextNewPass = view.findViewById(R.id.cd_et_new_password);
   editTextName = view.findViewById(R.id.cd_et_name);
   editTextDescription = view.findViewById(R.id.cd_et_description);
   buttonLogOut = view.findViewById(R.id.cd_btn_log_out);
   buttonSave = view.findViewById(R.id.cd_btn_save);

   mViewModel.getUser().observe(getViewLifecycleOwner(),userModel -> {
     editTextName.setText(userModel.getName());
     editTextMail.setText(userModel.getMail());
     editTextDescription.setText(userModel.getDescription());
   });

   buttonSave.setOnClickListener(new OnClickListener() {
     @Override
     public void onClick(View v) {
       if(editTextOldPass.getText().toString().isEmpty() &&
           editTextNewPass.getText().toString().isEmpty()){
         mViewModel.updatesUserInfo(editTextName.getText().toString(),
             editTextMail.getText().toString(),
             editTextDescription.getText().toString());
       }
     }
   });

   buttonLogOut.setOnClickListener(new OnClickListener() {
     @Override
     public void onClick(View v) {
       mViewModel.logOut();
       Intent intent = new Intent(getActivity(), AuthenticationActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);
     }
   });




  }
}