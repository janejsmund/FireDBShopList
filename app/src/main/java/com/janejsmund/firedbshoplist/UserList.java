package com.janejsmund.firedbshoplist;

import android.app.Activity;
import android.content.Context;
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
 * Created by Janek on 12-Jan-18.
 */

public class UserList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> users;
    DatabaseReference databaseReference;
    EditText edtName;


    public UserList(@NonNull Activity context, List<User> users,DatabaseReference databaseReference, EditText edtName) {

        super(context, R.layout.layout_user_list, users);

        this.context = context;
        this.users = users;
        this.databaseReference = databaseReference;
        this.edtName = edtName;
    }

    public View getView(int pos, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_user_list, null, true);

        TextView txtName = listViewItem.findViewById(R.id.txtName);
        Button btnDelete = listViewItem.findViewById(R.id.btnDelete);
        Button btnUpdate = listViewItem.findViewById(R.id.btnUpdate);

        final User user = users.get(pos);
        txtName.setText(user.getName());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child(user.getId()).removeValue();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtName.setText(user.getName());
                MainActivity.userId = user.getId();
            }
        });

        return listViewItem;
    }

}
