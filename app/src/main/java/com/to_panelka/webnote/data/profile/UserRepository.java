package com.to_panelka.webnote.data.profile;

import android.content.Context;
import java.util.ArrayList;

public class UserRepository {
private static UserRepository instance;
private ArrayList<UserData> arrayList = new ArrayList<>();
public static UserRepository getInstance(Context context){
  if(instance == null){
    instance = new UserRepository();
  }
  return instance;
}

}
