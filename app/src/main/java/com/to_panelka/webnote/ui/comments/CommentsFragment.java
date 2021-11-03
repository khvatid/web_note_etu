package com.to_panelka.webnote.ui.comments;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.to_panelka.webnote.R;
import com.to_panelka.webnote.adapter.CommentViewHolder;
import com.to_panelka.webnote.adapter.PostViewHolder;
import com.to_panelka.webnote.model.CommentModel;
import com.to_panelka.webnote.model.PostModel;
import java.util.Dictionary;

public class CommentsFragment extends Fragment {

  private CommentsViewModel mViewModel;
  FirestoreRecyclerAdapter<CommentModel,CommentViewHolder> adapter;


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_comments, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(CommentsViewModel.class);
    Bundle bundle = this.getArguments();
    String idPost = bundle.getString("idPost");
    EditText text = view.findViewById(R.id.comments_et);
    Button sendButton = view.findViewById(R.id.comments_btn_send);
    RecyclerView recyclerView = (RecyclerView) view.findViewById((R.id.comments_rv));
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    Query query = firestore.collection("Posts").document(idPost).collection("Comments");

    FirestoreRecyclerOptions<CommentModel> options = new FirestoreRecyclerOptions.Builder<CommentModel>()
        .setQuery(query,CommentModel.class).build();

    adapter = new FirestoreRecyclerAdapter<CommentModel, CommentViewHolder>(options) {
      @Override
      protected void onBindViewHolder(@NonNull CommentViewHolder holder, int position,
          @NonNull CommentModel model) {
        holder.setDataComment(model.getNameUser(),model.getTextComment());
      }

      @NonNull
      @Override
      public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
      }
    };
    recyclerView.setAdapter(adapter);

    mViewModel.getTextComment().observe(getViewLifecycleOwner(), String->{
      text.setText(String);
    });
    sendButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if(!text.getText().toString().isEmpty())
        {
          mViewModel.setTextComment(text.getText().toString());
          mViewModel.addComment(idPost);
        }
      }
    });



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