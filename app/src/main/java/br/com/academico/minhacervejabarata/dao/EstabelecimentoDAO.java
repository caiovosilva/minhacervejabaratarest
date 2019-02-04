package br.com.academico.minhacervejabarata.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.academico.minhacervejabarata.beans.Estabelecimento;
import br.com.academico.minhacervejabarata.db.DatabaseHelper;

public class EstabelecimentoDAO {

    private static final String TABLE_SUPERMERCADO = "supermercado";
    private static final String TABLE_SUPERMERCADO_COLUNA_NOME = "nome";
    private static final String TABLE_SUPERMERCADO_COLUNA_ENDERECO = "endereco";
    private static final String KEY_ID = "id";

    private SQLiteDatabase db;

    public EstabelecimentoDAO(Context context){
        db = new DatabaseHelper(context).getWritableDatabase();
    }
    public Estabelecimento createEstabelecimento(Estabelecimento estabelecimento/*, long[] tag_ids*/) {

        ContentValues values = new ContentValues();
        values.put(TABLE_SUPERMERCADO_COLUNA_NOME, estabelecimento.getNome());
        values.put(TABLE_SUPERMERCADO_COLUNA_ENDERECO, estabelecimento.getEndereco());

        // insert row
        Long id = db.insert(TABLE_SUPERMERCADO, null, values);

        // assigning tags to todo
        /*for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }*/


        estabelecimento.setId(id.intValue());

        return estabelecimento;

    }

    public List<Estabelecimento> getAllSupermercado() {
        List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
        String selectQuery = "SELECT  * FROM " + TABLE_SUPERMERCADO;

        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Estabelecimento td = new Estabelecimento();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setNome((c.getString(c.getColumnIndex(TABLE_SUPERMERCADO_COLUNA_NOME))));
                td.setEndereco(c.getString(c.getColumnIndex(TABLE_SUPERMERCADO_COLUNA_ENDERECO)));

                // adding to estabelecimentos list
                estabelecimentos.add(td);
            } while (c.moveToNext());
        }

        return estabelecimentos;
    }
}

