package com.example.trabalho_final2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalho_final2.Helper.DatabaseHelper;
import com.example.trabalho_final2.Models.Formando;

public class EditActivity extends AppCompatActivity
{
    EditText inputNumero, inputNome, inputTelefone, inputIdade, inputEmail;
    Button botaoUpdate, botaoDelete, botaoVoltar, botaoVer;
    int databaseTableitemId;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        inputNumero = findViewById(R.id.editTextNumber3);
        inputNome = findViewById(R.id.editTextTextPersonName4);
        inputTelefone = findViewById(R.id.editTextPhone2);
        inputIdade = findViewById(R.id.editTextNumber4);
        inputEmail = findViewById(R.id.editTextTextEmailAddress3);

        botaoUpdate = findViewById(R.id.button9);
        botaoDelete = findViewById(R.id.button10);
        botaoVoltar = findViewById(R.id.button11);
        botaoVer = findViewById(R.id.button12);

        myDB = new DatabaseHelper(this);

        Intent intent = getIntent();

        databaseTableitemId = Integer.parseInt(intent.getStringExtra("FormandosSelectedId"));

        Formando formando = myDB.getFormandoById(databaseTableitemId);

        inputNumero.setText(String.valueOf(formando.getNumero()));
        inputNome.setText(formando.getNome());
        inputTelefone.setText(formando.getTelefone());
        inputIdade.setText(String.valueOf(formando.getIdade()));
        inputEmail.setText(formando.getEmail());
    }

    public void backToHomeActivity(View view)
    {
        finish();
    }

    public void updateFormandoExec(View view)
    {
        String numero = inputNumero.getText().toString();
        String nome = inputNome.getText().toString();
        String telefone = inputTelefone.getText().toString();
        String idade = inputIdade.getText().toString();
        String email = inputEmail.getText().toString();

        if(numero.isEmpty() || nome.isEmpty() || telefone.isEmpty() || idade.isEmpty() || email.isEmpty())
        {
            ShowAlertMessage("ERRO", "PREENCHA OS CAMPOS TODOS");
        }
        else
        {
            boolean isUpdated = myDB.updateFormando(databaseTableitemId, Integer.parseInt(numero), nome, telefone, Integer.parseInt(idade), email);

            if(isUpdated == true)
            {
                backToHomeActivity(view);
                Toast.makeText(this, "Formando Actualizado com SUCESSO", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ShowAlertMessage("ERRO", "Erro na atualizaçao deste formando");
            }
        }
    }

    public void deletaFormandoExec(View view)
    {
        int isDeleted = myDB.deleteFormando(databaseTableitemId);

        if(isDeleted > 0)
        {
            backToHomeActivity(view);
            Toast.makeText(this, "Formando eliminado com SUCESSO", Toast.LENGTH_SHORT).show();
        }
        else
            ShowAlertMessage("ERRO", "Erro na eliminação deste formando");
    }

    public void verFormandoExec(View view)
    {
        Formando formando = myDB.getFormandoById(databaseTableitemId);

        StringBuffer buffer = new StringBuffer();

        buffer.append("Numero: " + formando.getNumero() + "\n");
        buffer.append("Nome: " + formando.getNome() + "\n");
        buffer.append("Telefone: " + formando.getTelefone() + "\n");
        buffer.append("Idade: " + formando.getIdade() + "\n");
        buffer.append("Email: " + formando.getEmail() + "\n");

        ShowAlertMessage("Formando", buffer.toString());
    }

    private void ShowAlertMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }


}