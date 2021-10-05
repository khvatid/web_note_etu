package com.to_panelka.webnote;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener{


  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;
  private EditText ETmail;
  private EditText ETpassword;

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
    //ETmail = findViewById(R.id.et_email);
    //ETpassword = findViewById(R.id.et_password);

    //findViewById(R.id.btn_sign_in).setOnClickListener(this);
    //findViewById(R.id.btn_registration).setOnClickListener(this);
      FirebaseUser user = mAuth.getCurrentUser();
    if(user != null){
      Intent intent = new Intent(this,MainActivity.class);
      intent.putExtra(mAuth.getCurrentUser().getUid(),user.getUid());
      //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
      //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    }
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.btn_sign_in)
    {
      signIn(ETmail.getText().toString(),ETpassword.getText().toString());
    }
    else if (v.getId() == R.id.btn_registration)
    {
      registration(ETmail.getText().toString(),ETpassword.getText().toString());
    }
  }

  public  void signIn (String email, String password)
  {
    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,
        task -> {
          if(task.isSuccessful()) {
            Toast.makeText(AuthenticationActivity.this, "Sign in Complite", Toast.LENGTH_SHORT)
                .show();

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

          } else
            Toast.makeText(AuthenticationActivity.this,"Log in Faild", Toast.LENGTH_SHORT).show();
        });
  }


}