package br.edu.icomp.plaintext;

import static android.os.Build.VERSION_CODES.S;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserDAO {
    static final String TAG = "UserDAO";
    private final Context context;
    private static final ArrayList<User> userList = new ArrayList<>();
    private final RequestQueue queue;

    public UserDAO(Context context) {

        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    ArrayList<User> getList() {
        if (userList.isEmpty()) {
            for (int index = 0; index < 10; index++) {
                jsonParse(index);
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

    public void jsonParse(int id) {
        String myUrl = "https://randomuser.me/api/?inc=name,email";
        Log.d(TAG, "jsonParse");
        JsonObjectRequest request = new JsonObjectRequest(myUrl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse");
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
                            boolean s = add(new User(id, nome, email));
                            Log.d(TAG, "onResponse: " + nome + " - " + email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG, "onErrorResponse: " + volleyError.toString());
            }
        });
        queue.add(request);
    }
}
