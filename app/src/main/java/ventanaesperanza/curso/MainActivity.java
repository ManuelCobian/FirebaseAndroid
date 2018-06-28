package ventanaesperanza.curso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String PATH_START = "start";//la rama del json que indicaa como la tabla de la db
    private static final String MESSEGE = "messege";//start/messege es el que va llevar es como el
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvMessege = findViewById(R.id.tvMessege);

        FirebaseDatabase database = FirebaseDatabase.getInstance();//variable para aseder a nueestra db
        final DatabaseReference reference = database.getReference(PATH_START).child(MESSEGE);//asseder a la rama de la db

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvMessege.setText(dataSnapshot.getValue(String.class));
                //edMessege.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "ERROR AL CONSULTAR FIREBASE", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
