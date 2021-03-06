package br.com.academico.minhacervejabarata;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.com.academico.minhacervejabarata.beans.Marca;
import br.com.academico.minhacervejabarata.db.DatabaseDjangoREST;
import br.com.academico.minhacervejabarata.db.DatabaseSqlite;
import br.com.academico.minhacervejabarata.db.IDatabase;
import br.com.academico.minhacervejabarata.listItens.MarcaAdapter;

public class MarcaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MarcaAdapter adapter;
    private IDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca);
        db = DatabaseDjangoREST.getInstance(this);
        configurarRecycler();

    }

    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.

        List<Marca> marcasList = db.getAllMarca();
        if(marcasList==null || marcasList.size()<1)
            findViewById(R.id.note_list_progress).setVisibility(View.VISIBLE);
        adapter = new MarcaAdapter(marcasList, this);
        db.setMarcaAdapter(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    public void btnAddMarca(View view) {
        Intent intent = new Intent(this, AddMarcaActivity.class);
        startActivity(intent);
    }


    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    public void disableProgressBar(){
        findViewById(R.id.note_list_progress).setVisibility(View.INVISIBLE);
    }
}
