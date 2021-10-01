package com.to_panelka.webnote;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener{


  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;
  private EditText ETmail;
  private EditText ETpassword;
  private EditText ETlogin;
  private EditText ETfullName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_authentication);

    mAuth = FirebaseAuth.getInstance();
    mAuthListener = firebaseAuth -> {
      FirebaseUser user = firebaseAuth.getCurrentUser();
      if(user != null){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
      }
      else {

      }
    };
    ETlogin = findViewById(R.id.et_login) ;
    ETfullName = findViewById(R.id.et_fullName);
    ETmail = findViewById(R.id.et_email);
    ETpassword = findViewById(R.id.et_password);

    findViewById(R.id.btn_sign_in).setOnClickListener(this);
    findViewById(R.id.btn_registration).setOnClickListener(this);
      FirebaseUser user = mAuth.getCurrentUser();
    if(user != null){
      Intent intent = new Intent(this,MainActivity.class);
      startActivity(intent);
      //onDestroy();
    }
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.btn_registration:
        if(ETlogin.getVisibility() == View.VISIBLE)
          registration(ETmail.getText().toString(),ETpassword.getText().toString());
        else {
          ETlogin.setVisibility(View.VISIBLE);
          ETfullName.setVisibility(View.VISIBLE);
        }
        break;

      case R.id.btn_sign_in:
      if(ETlogin.getVisibility() == View.GONE)
        signIn(ETmail.getText().toString(),ETpassword.getText().toString());
      else{
        ETlogin.setVisibility(View.GONE);
        ETfullName.setVisibility(View.GONE);
      }
        break;
    }
  }

  public  void signIn (String email, String password)
  {
    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,
        task -> {
          if(task.isSuccessful()) {
            Toast.makeText(AuthenticationActivity.this, "Sign in Complite", Toast.LENGTH_SHORT)
                .show();
             Intent intent = new Intent(this, MainActivity.class);
              startActivity(intent);
          } else
            Toast.makeText(AuthenticationActivity.this,"Sign in Faild", Toast.LENGTH_SHORT).show();
        });
  }
  public  void registration(String email,String password)
  {
    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
        task -> {
          if(task.isSuccessful()) {
            Toast.makeText(AuthenticationActivity.this, "Log in Complite", Toast.LENGTH_SHORT)
                .show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
          } else
            Toast.makeText(AuthenticationActivity.this,"Log in Faild", Toast.LENGTH_SHORT).show();
        });
  }


}