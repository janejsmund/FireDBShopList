package com.janejsmund.firedbshoplist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by Janek on 03-Jan-18.
 */

public class GroceryList extends ArrayAdapter<Grocery> {

    private Activity context;
    private List<Grocery> groceries;
    DatabaseReference databaseReference;
    EditText edtName, edtAmount, edtPrice, edtAmountBought;


    public GroceryList(@NonNull Activity context, List<Grocery> groceries, DatabaseReference databaseReference, EditText edtName, EditText edtAmount, EditText edtPrice, EditText edtAmountBought) {

        super(context, R.layout.layout_grocery_list, groceries);

        this.context = context;
        this.groceries = groceries;
        this.databaseReference = databaseReference;

        this.edtName = edtName;
        this.edtAmount = edtAmount;
        this.edtPrice = edtPrice;
        this.edtAmountBought = edtAmountBought;
    }

    public View getView(int pos, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_grocery_list, null, true);

        TextView txtName = listViewItem.findViewById(R.id.txtName);
        TextView txtAmount = listViewItem.findViewById(R.id.txtAmount);
        TextView txtPrice = listViewItem.findViewById(R.id.txtPrice);
        TextView txtAmountBought = listViewItem.findViewById(R.id.txtAmountBought);

        Button btnDelete = listViewItem.findViewById(R.id.btnDelete);
        Button btnUpdate = listViewItem.findViewById(R.id.btnUpdate);

        final Grocery grocery = groceries.get(pos);

        txtName.setText(grocery.getName());
        txtAmount.setText(grocery.getAmount());
        txtPrice.setText(grocery.getPrice());
        txtAmountBought.setText(grocery.getAmountBought());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child(grocery.getId()).removeValue();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtName.setText(grocery.getName());
                edtAmount.setText(grocery.getAmount());
                edtPrice.setText(grocery.getPrice());
                edtAmountBought.setText(grocery.getAmountBought());
                MainActivity.groceryId = grocery.getId();
            }
        });

        return listViewItem;
    }

}
