package br.edu.icomp.plaintext;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("PlainText Password Manager v1.0")
                    .setNeutralButton("Ok", null).show();
            return true;
        }
        else if (item.getItemId() == R.id.setup) {
            Intent intentConfig = new Intent(this, PreferencesActivity.class);
            startActivity(intentConfig);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }


    public void entrarClicado (View view) {
        Intent intent = new Intent(this, ListActivity.class);
        EditText inputLogin = findViewById(R.id.editLogin);
        intent.putExtra("login", inputLogin.getText().toString());
        startActivity(intent);
    }
}