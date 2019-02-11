package sf.alomari.wagstack;

//Name: SOHAIB ALOMARI
//Wag Assignment

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    public static RecyclerView_Adapter adapter;
    public static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new AsyncClass().execute();



        recyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);


        LinearLayoutManager layoutManager=new LinearLayoutManager(this);


        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerView_Adapter("");




    }

    public static void updateUI()
    {
        recyclerView.setAdapter(adapter);
    }

}
