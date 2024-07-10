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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class UserActivity extends AppCompatActivity {
    public RecyclerView userRecycler;
    private UserAdapter adapter;


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

        userRecycler = findViewById(R.id.userRecycler);
        userRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter(this);
        userRecycler.setAdapter(adapter);

        Executors.newSingleThreadExecutor().submit(() -> {
            while (adapter.users.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                updateUi();
            }
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
            holder.nome.setText(users.get(position).getNome());
            holder.email.setText(users.get(position).getEmail());
            holder.id = users.get(position).getId();

        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Context context;
        public TextView nome, email;

        public int id;

        public UsersViewHolder(ConstraintLayout v, Context context) {
            super(v);
            this.context = context;
            nome = v.findViewById(R.id.userNome);
            email = v.findViewById(R.id.userEmail);

            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Toast.makeText(context, ";)", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUi();

    }

    private void updateUi() {
        adapter.update();
        adapter.notifyDataSetChanged();
    }
}