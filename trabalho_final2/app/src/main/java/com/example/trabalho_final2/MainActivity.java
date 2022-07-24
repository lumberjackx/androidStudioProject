package com.example.trabalho_final2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.trabalho_final2.Helper.DatabaseHelper;

public class MainActivity extends AppCompatActivity
{

    EditText inputUsername, inputPassword;
    Button botaoLogin, botaoRegister;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = findViewById(R.id.editTextTextPersonName);
        inputPassword = findViewById(R.id.editTextTextPassword);
        botaoLogin = findViewById(R.id.button);
        botaoRegister = findViewById(R.id.button2);

        myDB = new DatabaseHelper(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        inputUsername.getText().clear();
        inputPassword.getText().clear();
    }

    public void loginButton(View view)
    {
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty())
        {
            ShowAlertMessage("ERRO", "PREENCHA OS CAMPOS TODOS");
        }
        else
        {
            boolean usernameExists = myDB.checkUsername(username);

            if(usernameExists == true)
            {
                boolean passwordMatches = myDB.checkPassword(username, password);

                if(passwordMatches == true)
                {
                    openHomeActivity(view);
                }
                else
                {
                    ShowAlertMessage("ERRO", "Password Incorreta");
                }
            }
            else
            {
                ShowAlertMessage("ERRO", "Username não encontrado na base de dados");
            }
        }
    }

    public void openRegisterActivity(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);

        startActivity(intent);

    }

    public void openHomeActivity(View view)
    {
        Intent intent = new Intent(this, HomeActivity.class);

        startActivity(intent);
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