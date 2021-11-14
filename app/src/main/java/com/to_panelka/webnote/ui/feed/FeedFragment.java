package com.to_panelka.webnote.ui.feed;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.adapter.PostViewHolder;
import com.to_panelka.webnote.model.PostModel;

public class FeedFragment extends Fragment {

  private FeedViewModel mViewModel;
  FirestoreRecyclerAdapter<PostModel, PostViewHolder> adapter;


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_feed, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
    super.onViewCreated(view, savedInstanceState);
    NavController navController = Navigation.findNavController(view);
    RecyclerView recyclerView = (RecyclerView) view.findViewById((R.id.feed_posts_recycler_view));
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    Query query = firestore.collection("Posts").orderBy("timePublish",Direction.DESCENDING);
    FirestoreRecyclerOptions<PostModel> options = new FirestoreRecyclerOptions.Builder<PostModel>()
        .setQuery(query,PostModel.class).build();

      adapter = new FirestoreRecyclerAdapter<PostModel, PostViewHolder>(options) {
      @Override
      protected void onBindViewHolder(@NonNull PostViewHolder holder, int position,
          @NonNull PostModel model) {
        if(model.getIdUser().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
          holder.setDataPost(model.getIdPost(),model.getTextPost(),model.getNameUser(),model.getTimePublish(), true);
        else
          holder.setDataPost(model.getIdPost(),model.getTextPost(),model.getNameUser(),model.getTimePublish(), false);
      }

      @NonNull
      @Override
      public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
      }
    };
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void onStart() {
    super.onStart();
    adapter.startListening();
  }

  @Override
  public void onStop() {
    adapter.stopListening();
    super.onStop();

  }
}

