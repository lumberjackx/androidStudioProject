package com.example.trabalho_final2.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.trabalho_final2.Models.Formando;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "myDatabase.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "create table if not exists utilizadores (Id INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Email TEXT)";

        db.execSQL(query);

        String query2 = "create table if not exists formandos (Id INTEGER PRIMARY KEY AUTOINCREMENT, Numero INTEGER, Nome TEXT, Telefone TEXT, Idade INTEGER, Email TEXT)";

        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
    }

    public boolean checkUsername(String username)
    {
        SQLiteDatabase db = getReadableDatabase();

        String query = "select * from utilizadores where Username = ?";

        Cursor result = db.rawQuery(query, new String[] {username});

        if(result.getCount() == 0)
        {
            return false;
        }
        else
            return true;
    }

    public boolean checkPassword(String username, String password)
    {
        SQLiteDatabase db = getReadableDatabase();

        String query = "select * from utilizadores where Username = ? and Password = ?";

        Cursor result = db.rawQuery(query, new String[] {username, password});

        if(result.getCount() == 0)
        {
            return false;
        }
        else
            return true;
    }

    public boolean insereUtilizador(String username, String password, String email)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("Username", username);
        contentValues.put("Password", password);
        contentValues.put("Email", email);

        long result = db.insert("utilizadores", null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insereFormando(int numero, String nome, String telefone, int idade, String email)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("Numero", numero);
        contentValues.put("Nome", nome);
        contentValues.put("Telefone", telefone);
        contentValues.put("Idade", idade);
        contentValues.put("Email", email);

        long result = db.insert("formandos", null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Formando> getAllFormandos()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Formando> formandosArrayList = new ArrayList<>();

        String query = "select * from formandos";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            int Id = cursor.getInt(0);
            int numero = cursor.getInt(1);
            String nome = cursor.getString(2);
            String telefone = cursor.getString(3);
            int idade = cursor.getInt(4);
            String email = cursor.getString(5);

            Formando formando = new Formando(Id, numero, nome, telefone, idade, email);

            formandosArrayList.add(formando);
        }

        return formandosArrayList;
    }

    public Formando getFormandoById(int _Id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Formando formando = null;

        String query = "select * from formandos where Id = " + _Id;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() == 1)
        {
            cursor.moveToFirst();

            int numero = cursor.getInt(1);
            String nome = cursor.getString(2);
            String telefone = cursor.getString(3);
            int idade = cursor.getInt(4);
            String email = cursor.getString(5);

            formando = new Formando(_Id, numero, nome, telefone, idade, email);
        }

        return formando;
    }

    public boolean updateFormando(int id, int numero, String nome, String telefone, int idade, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("Numero", numero);
        contentValues.put("Nome", nome);
        contentValues.put("Telefone", telefone);
        contentValues.put("Idade", idade);
        contentValues.put("Email", email);

        long result = db.update("formandos", contentValues, "Id = ?", new String[] {Integer.toString(id)});

        if (result == -1)
            return false;
        else
            return true;
    }

    public int deleteFormando(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("formandos", "Id = ?", new String[] {Integer.toString(id)});
    }

    public boolean checkNumero(int numero)
    {
        ArrayList<Formando> listaFormandos = getAllFormandos();

        for(int i=0; i<listaFormandos.size(); i++)
        {
            Formando formando = listaFormandos.get(i);

            if(formando.getNumero() == numero)
            {
                return false;
            }
        }
        return true;
    }


}
