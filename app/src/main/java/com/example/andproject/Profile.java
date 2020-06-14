package com.example.andproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    TextView name;
    Button logOut;
    private Button button;
    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    ArrayList<String> item_id, item_name, item_date;
    AdapterRCV adapterRCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setTitle("Profile");

        logOut = findViewById(R.id.LogOut);
        name = findViewById(R.id.name);
        recyclerView = findViewById(R.id.recyclerViewList);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            name.setText(signInAccount.getDisplayName() + "'s refrigerator");
        }

        databaseHelper = new DatabaseHelper(Profile.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_date = new ArrayList<>();
        storeInArrays();

        adapterRCV = new AdapterRCV(Profile.this, item_id, item_name, item_date );
        recyclerView.setAdapter(adapterRCV);
        recyclerView.setLayoutManager(new LinearLayoutManager(Profile.this));


        button = findViewById(R.id.add_new_item);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenBarCodeScanner();
            }
        });





        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

    }

    public void OpenBarCodeScanner() {
        Intent intent = new Intent(this, BarCodeScanner.class);
        startActivity(intent);
    }

void storeInArrays(){
    Cursor cursor = databaseHelper.getAllItems();
    if(cursor.getCount() == 0){
        Toast.makeText(Profile.this, "There is no data", Toast.LENGTH_SHORT).show();
    }
    else{
        while (cursor.moveToNext()){
            item_id.add(cursor.getString(0));
            item_name.add(cursor.getString(1));
            item_date.add(cursor.getString(2));
        }
    }
}

ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
    {
        int position = viewHolder.getAdapterPosition();
        item_id.remove(viewHolder.getAdapterPosition());
        adapterRCV.notifyDataSetChanged();
        Toast.makeText(Profile.this, "item deleted id " + position, Toast.LENGTH_SHORT).show();
        databaseHelper.deleteObject(position);
        adapterRCV.notifyItemRemoved(position);
    }
    };

}
