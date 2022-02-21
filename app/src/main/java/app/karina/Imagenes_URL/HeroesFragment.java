package app.karina.Imagenes_URL;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.giogen.imagenesurl.R;

public class HeroesFragment extends Fragment {

    private RecyclerView heroRecyclerView;
    private AdaptadorHeroes adapter;
    private GridLayoutManager layoutManager;
    MainActivity mainActivity;
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof MainActivity){
            mainActivity = (MainActivity) context;
        }
    }
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HeroesFragment() {
    }

    public static HeroesFragment newInstance(String param1, String param2) {
        HeroesFragment fragment = new HeroesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_heroes, container, false);
        heroRecyclerView = v.findViewById(R.id.hero_recyclerView);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        adapter = new AdaptadorHeroes(getContext(), MainActivity.heroesList);
        heroRecyclerView.setLayoutManager(layoutManager);
        heroRecyclerView.setAdapter(adapter);
        return v;
    }
}