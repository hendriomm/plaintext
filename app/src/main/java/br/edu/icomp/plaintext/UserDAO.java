package br.edu.icomp.plaintext;

import static android.os.Build.VERSION_CODES.S;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserDAO {
    private Context context;
    private static final ArrayList<User> userList = new ArrayList<>();

    public UserDAO(Context context) {
        this.context = context;
    }

    ArrayList<User> getList() {
        if (userList.isEmpty()) {
            for (int i = 0; i < 30; i++) {
                jsonParse();
            }
        }
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

    public void jsonParse() {
        String myUrl = "https://randomuser.me/api/?inc=name,email";
        JsonObjectRequest request = new JsonObjectRequest(myUrl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray;
                        JSONObject jObject, jNome;
                        String nome = null;
                        String email = null;
                        try {
                            jsonArray = response.getJSONArray("results");
                            jObject = jsonArray.getJSONObject(0);
                            jNome = (jObject.getJSONObject("name"));
                            nome = jNome.getString("first") + " " + jNome.getString("last");
                            email = jObject.getString("email");
                            boolean s = add(new User(0, nome, email));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("response", "onErrorResponse: " + volleyError.toString());
            }
        });

    }
}
