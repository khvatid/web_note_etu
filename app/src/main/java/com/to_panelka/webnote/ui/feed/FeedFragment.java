package com.to_panelka.webnote.ui.feed;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.adapter.PostAdapter;
import com.to_panelka.webnote.adapter.PostAdapter.ViewHolder;
import com.to_panelka.webnote.model.PostModel;
import java.util.ArrayList;

public class FeedFragment extends Fragment {

  private FeedViewModel mViewModel;
  private RecyclerView recyclerView;
  private ArrayList<PostModel> postModels = new ArrayList<>();
  FirestoreRecyclerAdapter<PostModel, ViewHolder> adapter;


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_feed, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
    super.onViewCreated(view, savedInstanceState);
    RecyclerView recyclerView = (RecyclerView) view.findViewById((R.id.feed_posts_recycler_view));
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    Query query = firestore.collection("Posts").orderBy("timePublish",Direction.DESCENDING);
    FirestoreRecyclerOptions<PostModel> options = new FirestoreRecyclerOptions.Builder<PostModel>()
        .setQuery(query,PostModel.class).build();

      adapter = new FirestoreRecyclerAdapter<PostModel, ViewHolder>(options) {
      @Override
      protected void onBindViewHolder(@NonNull ViewHolder holder, int position,
          @NonNull PostModel model) {
        holder.setTextPost(model.getTextPost(),model.getIdUser(),model.getTimePublish());
      }

      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
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
    super.onStop();
    adapter.stopListening();
  }
}

