package com.to_panelka.webnote.ui.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.to_panelka.webnote.AuthenticationActivity;
import com.to_panelka.webnote.MainActivity;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.ui.newPost.PostCreateViewModel;

public class LoginFragment extends Fragment {


  Button gotSignupActivity;
  NavController navController;
  String email, password;
  EditText et_email, et_password;
  Button signInbtn;
  FirebaseAuth firebaseAuth;



  public LoginFragment() {
    // Required empty public constructor
  }



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_login, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    gotSignupActivity = view.findViewById(R.id.login_btn_registration);
    navController = Navigation.findNavController(view);
    firebaseAuth = FirebaseAuth.getInstance();

    et_email = view.findViewById(R.id.login_et_email);
    et_password = view.findViewById(R.id.login_et_password);
    signInbtn = view.findViewById(R.id.login_btn_sign_in);


    gotSignupActivity.setOnClickListener(
        v -> navController.navigate(R.id.action_loginFragment_to_registerFragment));

    signInbtn.setOnClickListener(v -> {
      email = et_email.getText().toString();
      password = et_password.getText().toString();


      if (email.isEmpty() || password.isEmpty()) {

        Toast.makeText(getContext(), "Sign in fail!", Toast.LENGTH_SHORT).show();

      }

      if (password.length() < 6) {

        et_password.setError("Password Length Must Be 6 or more Chars");


      }


      signInTheUser(email, password);
    });



  }

  private void signInTheUser(String email, String password) {

    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {

        if (task.isSuccessful()) {


          Toast.makeText(getContext(), "Signed In", Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(getActivity(),MainActivity.class);
          startActivity(intent);

        }

      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();

    if (firebaseAuth.getCurrentUser()!=null) {

      Intent intent = new Intent(getActivity(),MainActivity.class);
      startActivity(intent);


    }
  }
}