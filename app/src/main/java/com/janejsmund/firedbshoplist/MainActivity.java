package com.janejsmund.firedbshoplist;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    Button btnSave;
    EditText edtName, edtAmount, edtPrice, edtAmountBought;
    DatabaseReference databaseReference;
    ListView listViewGroceries;
    List<Grocery> groceries;
    protected static String groceryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groceries = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("groceries");

        btnSave = findViewById(R.id.btnSave);

        edtName = findViewById(R.id.edtName);
        edtAmount = findViewById(R.id.edtAmount);
        edtPrice = findViewById(R.id.edtPrice);
        edtAmountBought = findViewById(R.id.edtAmountBought);

        listViewGroceries = findViewById(R.id.listViewGroceries);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString();
                String amount = edtAmount.getText().toString();
                String price = edtPrice.getText().toString();
                String amountBought = edtAmountBought.getText().toString();

                if (TextUtils.isEmpty(groceryId)) {

                    String id = databaseReference.push().getKey();
                    Grocery grocery = new Grocery(id, name, amount, price, amountBought);
                    databaseReference.child(id).setValue(grocery);

                    Toast.makeText(MainActivity.this, "Grocery item created successfully", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child(groceryId).child("name").setValue(name);

                    Toast.makeText(MainActivity.this, "Grocery item updated successfully", Toast.LENGTH_SHORT).show();
                }

                edtName.setHint("Nazwa");
                edtAmount.setHint("Ilość");
                edtPrice.setHint("Cena");
                edtAmountBought.setHint("Kupiono sztuk");

                edtName.setText("");
                edtAmount.setText("");
                edtPrice.setText("");
                edtAmountBought.setText("");

            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                groceries.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Grocery grocery = postSnapshot.getValue(Grocery.class);
                    groceries.add(grocery);
                }

                GroceryList groceryAdapter = new GroceryList(MainActivity.this, groceries, databaseReference, edtName, edtAmount, edtPrice, edtAmountBought);
                listViewGroceries.setAdapter(groceryAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
