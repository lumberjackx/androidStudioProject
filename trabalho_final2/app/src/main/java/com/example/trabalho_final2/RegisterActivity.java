package com.example.trabalho_final2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalho_final2.Helper.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity
{

    EditText inputUsername, inputPassword, inputEmail;
    Button botaoRegister, botaoCancel;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputUsername = findViewById(R.id.editTextTextPersonName2);
        inputPassword = findViewById(R.id.editTextTextPassword2);
        inputEmail = findViewById(R.id.editTextTextEmailAddress);

        botaoRegister = findViewById(R.id.button3);
        botaoCancel = findViewById(R.id.button4);

        myDB = new DatabaseHelper(this);
    }

    public void backToMainActivity(View view)
    {
        finish();
    }

    public void insereUtilizadorExec(View view)
    {
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();
        String email = inputEmail.getText().toString();


        if(username.isEmpty() || password.isEmpty() || email.isEmpty())
        {
            ShowAlertMessage("ERRO", "PREENCHA OS CAMPOS TODOS");
        }
        else
        {
            boolean isInserted = myDB.insereUtilizador(username, password, email);

            if(isInserted == true)
            {
                Toast.makeText(this, "Utilizador inserido com SUCESSO", Toast.LENGTH_SHORT).show();
                backToMainActivity(view);
            }
            else
                ShowAlertMessage("ERRO", "Erro na inserçao deste utilizador");
        }
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