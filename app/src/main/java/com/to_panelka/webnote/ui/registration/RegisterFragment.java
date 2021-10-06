package com.to_panelka.webnote.ui.registration;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.ui.newPost.PostCreateViewModel;
import java.util.HashMap;

public class RegisterFragment extends Fragment {



  NavController navController;
  String name, email, password, description;
  EditText et_email, et_password, et_name, et_description;
  Button signUp;
  FirebaseAuth firebaseAuth;
  FirebaseFirestore firestore;
  String userid;


  public RegisterFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_register, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    firebaseAuth = FirebaseAuth.getInstance();
    firestore = FirebaseFirestore.getInstance();

    navController = Navigation.findNavController(view);
    et_email = view.findViewById(R.id.reg_et_email);
    et_name  = view.findViewById(R.id.reg_et_name);
    et_password = view.findViewById(R.id.reg_et_password);
    et_description = view.findViewById(R.id.reg_et_description);
    signUp = view.findViewById(R.id.reg_btn_sign_in);



    signUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        name = et_name.getText().toString();
        email = et_email.getText().toString();
        password = et_password.getText().toString();
        description = et_description.getText().toString();


        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

          Toast.makeText(getContext(), "Fields are required", Toast.LENGTH_SHORT).show();
        }

        if (name.isEmpty()) {

          et_name.setError("Enter a name");
        }

        if (password.length() < 6) {

          et_password.setError("Password Length Must Be 6 or more Chars");
        }

        if (email.isEmpty()) {

          et_email.setError("Enter email");

        }
        if (description.isEmpty()){
          description = " ";
        }

        signUp(name, email, password,description);

      }
    });



  }

  private void signUp(String name, String email, String password,String description) {

    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {

        if (task.isSuccessful()) {


          userid = firebaseAuth.getCurrentUser().getUid();


          HashMap<String, Object> hashMap = new HashMap<>();

          hashMap.put("userid", userid);
          hashMap.put("username", name);
          hashMap.put("description",description);


          firestore.collection("Users").document(userid).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
          });

          Toast.makeText(getContext(), "Signed Up", Toast.LENGTH_SHORT).show();
          navController.navigate(R.id.action_registerFragment_to_loginFragment);

        }

      }
    });




  }

}