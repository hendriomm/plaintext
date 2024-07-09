package br.edu.icomp.plaintext;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDAO {
    private Context context;
    private static final ArrayList<User> userList = new ArrayList<>();

    public UserDAO(Context context) {
        this.context = context;
    }

    ArrayList<User> getList() {
        return userList;
    }

    boolean add(User user) {
        user.setId(userList.size());
        userList.add(user);
        Toast.makeText(context, "Usuário salvo!", Toast.LENGTH_SHORT).show();
        return true;
    }

    boolean update(User user) {
        userList.set(user.getId(), user);
        Toast.makeText(context, "Usuário atualizado!", Toast.LENGTH_SHORT).show();
        return true;
    }

    public User get(int id) {
        return userList.get(id);
    }
}
