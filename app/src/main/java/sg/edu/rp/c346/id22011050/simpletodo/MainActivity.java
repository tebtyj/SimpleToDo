package sg.edu.rp.c346.id22011050.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    EditText etTask;
    Button btnAdd;
    Button btnClear;
    ListView lvTasks;
    Button btnDelete;
    ArrayList<String> alTask;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        lvTasks = findViewById(R.id.listViewTasks);
        spinner = findViewById(R.id.spinner);



        alTask = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTask);
        lvTasks.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = etTask.getText().toString();
                alTask.add(taskName);
                etTask.setText("");
                adapter.notifyDataSetChanged();

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alTask.clear();
                adapter.notifyDataSetChanged();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int pos = Integer.parseInt(etTask.getText().toString());
            String taskName = etTask.getText().toString();
            if (alTask.isEmpty()){
                Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_LONG).show();
            }else if(pos+1>alTask.size()) {
                Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_LONG).show();
            }else{
                    alTask.remove(pos);
                    adapter.notifyDataSetChanged();
                }

            }

        });
    }
}