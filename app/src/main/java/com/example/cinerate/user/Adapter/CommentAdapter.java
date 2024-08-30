package com.example.cinerate.user.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cinerate.R;
import com.example.cinerate.models.Comment;
import com.example.cinerate.models.Genre;
import com.example.cinerate.models.User;
import com.example.cinerate.user.HomePageActivity;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<Comment> commentList;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.commentContent.setText(comment.getContent());

        User u = HomePageActivity.userDAO.getUserId(comment.getUser_id());
        holder.commentAuthor.setText(u.getUsername());

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView commentContent, commentAuthor;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            commentContent = itemView.findViewById(R.id.comment_content);
            commentAuthor = itemView.findViewById(R.id.comment_author);
        }
    }
}

