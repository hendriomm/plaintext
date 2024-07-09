package br.edu.icomp.plaintext;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    public RecyclerView userRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    static class UserAdapter extends RecyclerView.Adapter<UsersViewHolder> {
        private final Context context;
        private ArrayList<User> users;
        UserDAO userDAO;

        public UserAdapter(Context context) {
            this.context = context;
            userDAO = new UserDAO(context);

            update();
        }

        public void update() {
            users = userDAO.getList();
        }


        @NonNull
        @Override
        public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ConstraintLayout v = (ConstraintLayout) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.user_item, parent, false);
            return new UsersViewHolder(v, context);
        }

        @Override
        public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
            
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Context context;
        public TextView nome, email;
        public ImageView image;
        public int id;

        public UsersViewHolder(ConstraintLayout v, Context context) {
            super(v);
            this.context = context;
            nome = v.findViewById(R.id.userNome);
            email = v.findViewById(R.id.userEmail);
            image = v.findViewById(R.id.userImage);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "+++", Toast.LENGTH_SHORT).show();
        }
    }
}