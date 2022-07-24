package com.example.trabalho_final2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trabalho_final2.Helper.DatabaseHelper;

public class InsereActivity extends AppCompatActivity
{

    EditText inputNumero, inputNome, inputTelefone, inputIdade, inputEmail;
    Button botaoInsere, botaoVoltar;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere);

        inputNumero = findViewById(R.id.editTextNumber);
        inputNome = findViewById(R.id.editTextTextPersonName3);
        inputTelefone = findViewById(R.id.editTextPhone);
        inputIdade = findViewById(R.id.editTextNumber2);
        inputEmail = findViewById(R.id.editTextTextEmailAddress2);

        botaoInsere = findViewById(R.id.button7);
        botaoVoltar = findViewById(R.id.button8);

        myDB = new DatabaseHelper(this);
    }

    public void insereFormandoExec(View view)
    {
        String numero = inputNumero.getText().toString();
        String nome = inputNome.getText().toString();
        String telefone = inputTelefone.getText().toString();
        String idade = inputIdade.getText().toString();
        String email= inputEmail.getText().toString();

        if(numero.isEmpty() || nome.isEmpty() || telefone.isEmpty() || idade.isEmpty() || email.isEmpty())
        {
            ShowAlertMessage("ERRO", "PREENCHA OS CAMPOS TODOS");
        }
        else
        {
            boolean isNumeroValido = myDB.checkNumero(Integer.parseInt(numero));

            if(isNumeroValido == true)
            {
                boolean isInserted = myDB.insereFormando(Integer.parseInt(numero), nome, telefone, Integer.parseInt(idade), email);

                if(isInserted == true)
                {
                    Toast.makeText(this, "Formando inserido com SUCESSO", Toast.LENGTH_SHORT).show();
                    backToHomeActivity(view);
                }
                else
                    ShowAlertMessage("ERRO", "Erro na inserção deste formando");
            }
            else
                ShowAlertMessage("ERRO", "Esse numero ja foi escolhido, escolha outro");
        }
    }

    public void backToHomeActivity(View view)
    {
        finish();
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