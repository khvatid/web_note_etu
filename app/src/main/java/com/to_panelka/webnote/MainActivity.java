package com.to_panelka.webnote;

import android.app.Activity;
import android.content.Intent;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

  private BottomNavigationView bottomNavigationView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    bottomNavigationView = findViewById(R.id.bottom_nav_view);
    //Отвечает за верхний бар
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_feed,R.id.navigation_new_post,R.id.navigation_profile).build();
    //Костыль для использования NavController
    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
    NavController navController = navHostFragment.getNavController();
    //Верхний бар
    NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
    //переключение фрагментов
    NavigationUI.setupWithNavController(bottomNavigationView,navController);

  }


}