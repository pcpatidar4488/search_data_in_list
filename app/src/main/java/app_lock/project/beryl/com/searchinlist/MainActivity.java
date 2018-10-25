package app_lock.project.beryl.com.searchinlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditText mSearch;
    MyAdapter myAdapter;
    List<Detail> detailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mSearch = findViewById(R.id.search);

        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        detailList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Detail detail = new Detail();
            detail.setName("Name " + i);
            detail.setMobile("Mobile " + i + i);
            detailList.add(detail);
        }
        myAdapter = new MyAdapter(this, detailList);
        mRecyclerView.setAdapter(myAdapter);


        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MyAdapter.mFilteredList=detailList;
                MyAdapter.detailList=detailList;
//                myAdapter.notifyDataSetChanged();
                myAdapter.getFilter().filter(s.toString());
            }
        });


    }
}
