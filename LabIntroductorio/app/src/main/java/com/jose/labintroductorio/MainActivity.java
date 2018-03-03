package com.jose.labintroductorio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableAll;
import io.reactivex.internal.operators.observable.ObservableReplay;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    FloatingActionButton fab;
    ArrayList<Amigos> amigosL= new ArrayList<Amigos>();

    //database
    private CompositeDisposable compositeDisposable;
    private AmigosRepository amigosRepository;
    private ArrayAdapter<Amigos> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Init
        compositeDisposable=new CompositeDisposable();



        //se crea un objeto de tipo listView referenciado al que esta en la GUI
        listView=findViewById(R.id.lista);
        //inicializa el fab
        fab=findViewById(R.id.fab);
        //Se crea un arrayAdapter
         final ArrayAdapter<Amigos> adapter=new ArrayAdapter<Amigos>(this,android.R.layout.simple_list_item_1,amigosL );
        registerForContextMenu(listView);
        //le decimos que adaptador vamos a usar en la listView, este adaptador se encarga de acomodar toda la DATA en la listView
        listView.setAdapter(adapter);

        //database
        AmigosDatabase amigosDatabase=AmigosDatabase.getmInstance(this);//creamos la base de datos
        amigosRepository=AmigosRepository.getmInstance(AmigosLocalDatasource.getInstance(amigosDatabase.amigosDAO()));

        //cargar todos los datos de la base de datos
        loadData();



        //Event
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //agregar objeto

                Disposable disposable= io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                  public void subscribe(ObservableEmitter<Object> e) throws Exception
                  {
                      //creacion de objetos de tipo amigos
                      Amigos oscar=new Amigos("Oscar Juarez",19, "Compu","Rojo","Corinto");
                      amigosRepository.insertAmigos(oscar);
                      Amigos javier=new Amigos("Javier Carpio",18, "Compu","Rojo","Azul");
                      amigosRepository.insertAmigos(javier);
                      final Amigos rodrigo=new Amigos("Rodrigro Zea",18, "Compu","Rojo","Verde");
                      amigosRepository.insertAmigos(rodrigo);
                      final Amigos pablo=new Amigos("Pablo Pelaez",19, "Admin","Rojo","Rosado");
                      amigosRepository.insertAmigos(pablo);

                      loadData();
                      e.onComplete();

                  }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer() {
                            @Override
                            public void accept(Object o) throws Exception {
                                Toast.makeText(MainActivity.this, "Amigo agregado!", Toast.LENGTH_SHORT).show();

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();


                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                loadData(); //Refresca los datos

                            }
                        });

            }
        });





        //como saber a cual le da click y asi mandar la data hasta el otro activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // se crea el intet y le decimos de que activity vamos a sacar la informacion y para donde va a ir (activity2)
                Intent in= new Intent(MainActivity.this,Activity2.class);
                //se utiliza .putExtra para mandar la data, poniendo el id de primero y posteriormente el valor
                in.putExtra("nombre",adapter.getItem(position).getNombre());
                in.putExtra("edad",adapter.getItem(position).getEdad());
                in.putExtra("carrera",adapter.getItem(position).getCarrera());


                //ejecutar intent creado anteriormente
                startActivityForResult(in,1);

            }
        });


    }

    private void loadData()
    {
        //use RXJAva
        Disposable disposable=amigosRepository.getAllAmigos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Amigos>>() {
                    @Override
                    public void accept(List<Amigos> amigos) throws Exception {
                        onGetAllAmigosSuccess(amigos);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
        compositeDisposable.add(disposable);


    }

    private void onGetAllAmigosSuccess(List<Amigos> amigos) {
        amigosL.clear();
        amigosL.addAll(amigos);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu,menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.menu_clear:
                deleteAllUsers();
                break;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllUsers()
    {
        Disposable disposable= io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception
            {
                amigosRepository.deleteAllAmigos();
                e.onComplete();

            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        loadData(); //Refresca los datos

                    }
                });
    }
}
