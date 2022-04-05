package edu.iuh.bai12_dbbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DataUser dataUser;
    Button btnAdd, btnRemove, btnCancel;
    EditText edtSearch;
    ListView lvName;
    ArrayList arrayList;
    ArrayAdapter adapter;
    ArrayList idList;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList();
        idList = new ArrayList();

        dataUser = new DataUser(this, "userdb.sqlite",
                null, 1);

//        dataUser.addUser(new User("Nguyen Hoai An"));
//        dataUser.addUser(new User("Dinh Diep Vuong"));
//        dataUser.addUser(new User("Du Quoc Dong"));
//        dataUser.addUser(new User("Cao Van Vien"));
//        dataUser.addUser(new User("Nguyen Van Thieu"));
//        dataUser.addUser(new User("Nguyen Khoa Nam"));
//        dataUser.addUser(new User("Ngo Quang Truong"));
//        dataUser.addUser(new User("Chu Manh Hoai"));
//        dataUser.addUser(new User("Lieu Phieu Phieu"));
//        dataUser.addUser(new User("Ngo Tat Cong"));


        edtSearch = findViewById(R.id.edt_name);
        btnAdd = findViewById(R.id.btn_add);
        btnRemove = findViewById(R.id.btn_remove);
        btnCancel = findViewById(R.id.btn_cancel);
        lvName = findViewById(R.id.lv_name);

//        arrayList = getAllList();
        getAllList();

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, arrayList);

        lvName.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUser.addUser(new User(edtSearch.getText().toString()));
                getAllList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Succesful", Toast.LENGTH_SHORT).show();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,
//                        idList.get(index) +" here", Toast.LENGTH_SHORT).show();

                dataUser.removeUser((int)idList.get(index));
                getAllList();
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this,
                        "Succesful", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
            }
        });
    }

    //itco
    private ArrayList getAllList(){
//        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.clear();
        idList.clear();

        for (Iterator iterator = dataUser.getAll().iterator(); iterator.hasNext(); ) {
            User user =  (User) iterator.next();

            arrayList.add(user.getName());
            idList.add(user.getId());
        }
        return arrayList;
    }
}