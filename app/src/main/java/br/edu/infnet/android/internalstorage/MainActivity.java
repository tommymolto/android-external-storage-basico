package br.edu.infnet.android.internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pegando nosso componente de listview
        listview = (ListView) findViewById(R.id.lstLista);

    }
    private static final String FILE_NAME = "contatos.data";

    /* verifica se existe o arquivo, e pega seu conteudo*/
    public  List<Contato> getExternalStorage(Context ct){
        List<Contato> ctlista = null;
        ObjectInputStream ois = null;
        try{
            File f = new File(ct.getExternalCacheDir(), FILE_NAME);
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            ctlista = (List<Contato>) ois.readObject();
            ois.close();
            fis.close();
        }catch (Exception e){

        }
        return  ctlista;
    }

    /* verifica se existe o arquivo, e grava seu conteudo*/
    public  boolean  saveExternalStorage(List<Contato> ctlista,
                                                    Context ct){
        boolean salvo = true;
        ObjectOutputStream ois = null;
        try{
            File f = new File(ct.getExternalCacheDir(), FILE_NAME);
            FileOutputStream fis = new FileOutputStream(f);
            ois = new ObjectOutputStream(fis);
            ois.writeObject(ctlista);
            ois.close();
            fis.close();
        }catch (Exception e){
            salvo = false;
        }
        return  salvo;
    }

    /*salva contato novo no storage*/
    public void salvaContato(View view){

        /*pegando os campos*/
        EditText nome = (EditText) findViewById(R.id.txtNome);
        EditText tel = (EditText) findViewById(R.id.txtTelefone);

        /*criando o contato */
        Contato ct = new Contato();
        ct.setNome(nome.getText().toString());
        ct.setTelefone(tel.getText().toString());

        /*pega lista do storage
        se nao tem, cria uma rray em branco*/

        List<Contato> lista = this.getExternalStorage(this);
        if(lista == null){
            lista = new ArrayList<>();
        }
        /* adiciona no array*/
        lista.add(ct);

        if(this.saveExternalStorage(lista,this)){
            /*se salvou, exibe*/
            ArrayAdapter<Contato> adaptador = new ArrayAdapter<Contato>(
                    this,
                    android.R.layout.simple_list_item_1,
                    lista);
            listview.setAdapter(adaptador);
        }
    
    }
}
