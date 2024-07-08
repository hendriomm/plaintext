package br.edu.icomp.plaintext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PasswordsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PasswordsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    static class PasswordsAdapter extends RecyclerView.Adapter<PasswordsViewHolder> {
        private final Context context;
        private ArrayList<Password> passwords;
        PasswordDAO passwordDAO;

        public PasswordsAdapter(Context context) {
            this.context = context;
            passwordDAO = new PasswordDAO(context);
            update();
        }

        public void update() { passwords = passwordDAO.getList(); }

        @NonNull
        public PasswordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ConstraintLayout v = (ConstraintLayout) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new PasswordsViewHolder(v, context);
        }

        public void onBindViewHolder(PasswordsViewHolder holder, int position) {
            holder.name.setText(passwords.get(position).getName());
            holder.login.setText(passwords.get(position).getLogin());
            holder.id = passwords.get(position).getId();
        }

        public int getItemCount() { return passwords.size(); }
    }

    static class PasswordsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Context context;
        public TextView login, name;
        public int id;

        public PasswordsViewHolder(ConstraintLayout v, Context context) {
            super(v);
            this.context = context;
            name = v.findViewById(R.id.itemName);
            login = v.findViewById(R.id.itemLogin);
            v.setOnClickListener(this);
        }
        public void onClick(View v) {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("passwordId", this.id);
            context.startActivity(intent);
        }
    }
    public void buttonAddClick(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }
}