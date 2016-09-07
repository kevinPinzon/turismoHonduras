package com.example.alumnos.recyclerview;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Place_Helper dbHelper;
    ArrayList<Place> places;
    ArrayList<Place> missingPlaces;
    private PlaceAdapter placeAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        places = new ArrayList<>();
        missingPlaces = new ArrayList<>();

        dbHelper = Place_Helper.newInstance(this);
        places = dbHelper.getAllPlace();
        placeAdapter = new PlaceAdapter(places, this);

        setToolbar();
        setNavigationDrawer();
        preparePlaces();
        setRecycler();
        setFab();
    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Places");
        setSupportActionBar(toolbar);
    }

    public void setNavigationDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void preparePlaces() {
        places.add(new Place(1,"http://www.newyorker.com/wp-content/uploads/2015/12/Veix-Goodbye-New-York-Color-1200.jpg",
                "New York",
                "Nueva York es la ciudad más poblada del estado homónimo y de los Estados Unidos de América, y la segunda mayor concentración urbana del continente americano, después de la Ciudad de México."));
        places.add(new Place(2,"http://lanoticia.hn/wp-content/uploads/2014/07/valle.jpg",
                "Valle de Angeles",
                "La primera faceta de la historia de Valle de Ángeles, se remonta a los años de la colonia española en Centro América, cuando poco a poco se fue poblando la comunidad, como resultado de la importante explotación minera, "));
        places.add(new Place(3,"http://q-ec.bstatic.com/images/hotel/840x460/552/55266645.jpg",
                "Roatan",
                "En general las Islas de la Bahía se refieren al archipiélago comprendido por las islas de Roatán, Útila, Guanaja, y numerosos islotes y cayos, que estaban habitadas en principio por los mayas, después por los payas, en el siglo XVI llegaron los conquistadores españoles quienes trajeron esclavos africanos como parte de la tripulación."));


        missingPlaces.add(new Place(4,"http://www.honduras.com/wp-content/uploads/2012/05/san-lorenzo-honduras-carretera-panamericanal.jpg",
                "San Lorenzo",
                "San Lorenzo es una ciudad relativamente joven, ya que a pesar de que los españoles fundaron la aldea de San Lorenzo en 1522, no fue sino hasta el 1 de enero de 1912 cuando se crea el municipio de San Lorenzo."));
        missingPlaces.add(new Place(5,"https://s-media-cache-ak0.pinimg.com/736x/4e/cc/04/4ecc041f02837e2df9c3ffcf225cfc55.jpg",
                "Santa Lucia",
                "El municipio colinda al norte, sur y oeste con el municipio de Distrito Central, y al este con el municipio de Valle de Ángeles.2 La cabecera Municipal está situada en la cima de la montaña de su nombre, a 1180 metros sobre el nivel del mar y a sólo 11 km de la capital del país, Tegucigalpa."));

    }

    public void setFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_add.class);
                startActivity(intent);

//                if (missingPlaces.size() > 0) {
//                    places.add(0, missingPlaces.remove(0));
//                    placeAdapter.notifyItemInserted(0);
//                    recyclerView.smoothScrollToPosition(0);
//                }

            }
        });

    }

    public void setRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setItemAnimator(new SlideInLeftAnimator(new LinearInterpolator()));

        placeAdapter = new PlaceAdapter(places, this);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                places.remove(viewHolder.getAdapterPosition());
                placeAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                ;
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(recyclerView);
        placeAdapter.setClickListener(new PlaceAdapter.ClickListener() {
            @Override
            //Paso 6
            public void onItemClick(int position, View v) {
                places.remove(position);
                placeAdapter.notifyItemRemoved(position);
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(placeAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_arboles:

                Toast.makeText(MainActivity.this, "Arboles fue seleccionado",
                        Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_playas:
                Toast.makeText(MainActivity.this, "Playas fue seleccionado",
                        Toast.LENGTH_LONG).show();
            break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        places.clear();
        places.addAll(dbHelper.getAllPlace());
        placeAdapter.notifyDataSetChanged();
    }
}
