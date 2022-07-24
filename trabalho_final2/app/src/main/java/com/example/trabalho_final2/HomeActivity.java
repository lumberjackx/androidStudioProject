package com.example.trabalho_final2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.trabalho_final2.Adapters.MyCustomAdapter;
import com.example.trabalho_final2.Helper.DatabaseHelper;
import com.example.trabalho_final2.Models.Formando;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
{
    Button botaoInsere, botaoLogOut;
    ListView listViewFormandos;

    MyCustomAdapter myAdapter;
    ArrayList<Formando> formandosArrayList = new ArrayList<>();

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        botaoInsere = findViewById(R.id.button5);
        botaoLogOut = findViewById(R.id.button6);

        listViewFormandos = findViewById(R.id.list_view);

        myDB = new DatabaseHelper(this);

        LoadListView();

        listViewFormandos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Formando formando = (Formando) listViewFormandos.getItemAtPosition(position);

                Intent intent = new Intent(HomeActivity.this, EditActivity.class);

                intent.putExtra("FormandosSelectedId", String.valueOf(formando.getId()));

                startActivity(intent);
            }
        });
    }

    public void openInsereActivity(View view)
    {
        Intent intent = new Intent(this, InsereActivity.class);

        startActivity(intent);
    }

    public void backToMainActivity(View view)
    {
        finish();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        LoadListView();
    }

    public void LoadListView()
    {
        formandosArrayList = myDB.getAllFormandos();

        myAdapter = new MyCustomAdapter(this, formandosArrayList);
        listViewFormandos.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();
    }
}