package example.admin.com.myroomdb.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import example.admin.com.myroomdb.R;
import example.admin.com.myroomdb.adapter.RecyclerViewAdapter;
import example.admin.com.myroomdb.db.User;
import example.admin.com.myroomdb.needle.Needle;
import example.admin.com.myroomdb.needle.UiRelatedTask;
import example.admin.com.myroomdb.services.UserServiceImpl;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et_name);
        button = findViewById(R.id.btn_submit);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(s)) {
                    saveToDB(s);
                } else {
                    Toast.makeText(MainActivity.this, "Field is blank", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveToDB(final String s_data) {
        final UserServiceImpl userService = new UserServiceImpl(MainActivity.this);

        Needle.onBackgroundThread().execute(new UiRelatedTask<List<User>>() {
            @Override
            protected List<User> doWork() {
                int i = userService.getAll().size();
                User user = new User(i + 1, s_data);
                Log.d("check_size_db_before", ": " + userService.getAll().size());
                userService.insert(user);
                Log.d("check_size_db_after", ": " + userService.getAll().size());
                return userService.getAll();
            }

            @Override
            protected void thenDoUiRelatedWork(List<User> users) {

                recyclerViewAdapter.clearData();
                recyclerViewAdapter.setUserLinkedList(users);
                recyclerView.smoothScrollToPosition(users.size());
            }
        });

        /*class UserLoginTask extends AsyncTask<Void, Void, List<User>> {
            UserServiceImpl userService = new UserServiceImpl(MainActivity.this);

            @Override
            protected List<User> doInBackground(Void... params) {


                int i = userService.getAll().size();
                User user = new User(i + 1, s_data);
                Log.d("check_size_db_before", ": " + userService.getAll().size());
                userService.insert(user);
                Log.d("check_size_db_after", ": " + userService.getAll().size());
                return userService.getAll();
            }

            @Override
            protected void onPostExecute(final List<User> users) {

                recyclerViewAdapter.clearData();
                recyclerViewAdapter.setUserLinkedList(users);
                recyclerView.smoothScrollToPosition(users.size());

            }

        }
        UserLoginTask userLoginTask = new UserLoginTask();
        userLoginTask.execute();*/
    }
}
