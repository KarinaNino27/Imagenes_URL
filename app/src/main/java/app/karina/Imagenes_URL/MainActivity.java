package app.karina.Imagenes_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.giogen.imagenesurl.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BNH";
    public static ArrayList<Hero> heroesList;
    RequestQueue requestQueue;
    private RecyclerView heroRecyclerView;
    private AdaptadorHeroes adapter;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obtenerHeroes();
    }

    public void obtenerHeroes(){
        requestQueue = Volley.newRequestQueue(this);
        String urlHeroes = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        JsonRequest jsonRequest = new JsonObjectRequest(
                urlHeroes,
                null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("heroes");
                        Type listType = new TypeToken<ArrayList<Hero>>(){}.getType();
                        Gson gson = new Gson();
                        heroesList = gson.fromJson(jsonArray.toString(), listType);

                        for(Hero hero: heroesList){
                            Log.d(TAG, "HEORES: nombre: " + hero.getName() + ", Imagen: " + hero.getImageurl());
                        }

                        heroRecyclerView = findViewById(R.id.rbHeroes);
                        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        adapter = new AdaptadorHeroes(getApplicationContext(), MainActivity.heroesList);
                        heroRecyclerView.setLayoutManager(layoutManager);
                        heroRecyclerView.setAdapter(adapter);

                        HeroesFragment selectorFragment = new HeroesFragment();

                    } catch (JSONException e) {
                        Log.d(TAG, "heroesRequests: " + e.getMessage());
                    }
                },
                error -> {
                    Log.d(TAG, "heroesRequests: Error: " + error);
                }
        );

        requestQueue.add(jsonRequest);
    }
}