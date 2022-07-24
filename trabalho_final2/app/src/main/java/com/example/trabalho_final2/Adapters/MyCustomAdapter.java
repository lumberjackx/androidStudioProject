package com.example.trabalho_final2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalho_final2.Models.Formando;
import com.example.trabalho_final2.R;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Formando> formandosArrayList;

    public MyCustomAdapter(Context context, ArrayList<Formando> formandosArrayList)
    {
        this.context = context;
        this.formandosArrayList = formandosArrayList;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return this.formandosArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.formandosArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Formando formando = formandosArrayList.get(position);

        if(convertView == null)
        {
            convertView = this.layoutInflater.inflate(R.layout.formandoslistview, parent, false);
        }

        TextView textViewNumero = convertView.findViewById(R.id.textView);
        TextView textViewNome = convertView.findViewById(R.id.textView2);
        TextView textViewTelefone = convertView.findViewById(R.id.textView3);

        textViewNumero.setText(String.valueOf(formando.getNumero()));
        textViewNome.setText(formando.getNome());
        textViewTelefone.setText(formando.getTelefone());

        return convertView;
    }


}
