package rt.tea.com.fireblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.core.Context;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mrecycler;

    private DatabaseReference mDatabase;


    Firebase fire;
    Button b1, ex;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrecycler = (RecyclerView)findViewById(R.id.blog);
        mrecycler.setHasFixedSize(true);
        mrecycler.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        b1 = (Button) findViewById(R.id.da);
        ex = (Button) findViewById(R.id.get);
        et = (EditText) findViewById(R.id.text);

        fire = new Firebase("https://blog-8c531.firebaseio.com/");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aa = et.getText().toString();
                //Firebase chiild = fire.child("Name");
                AllinOne all = new AllinOne("Anshul", "9713712304");
                fire.push().setValue(all);
                // chiild.setValue(aa);

            }
        });

        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GetAll.class);
                startActivity(i);
            }
        });


    }

    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(


                Blog.class,
                R.layout.blog_layout,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                         viewHolder.setTitle(model.getTitle());
                         viewHolder.setDesc(model.getDescription());
                //viewHolder.setImage(getApplicationContext(), model.getImage());
            }
        };
mrecycler.setAdapter(firebaseRecyclerAdapter);
    }


    public class BlogViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);

          mView=itemView;
        }

        public void setTitle(String titles){

            TextView title = (TextView)itemView.findViewById(R.id.title);
            title.setText(titles);
        }


        public void setDesc(String des){

            TextView descc = (TextView)itemView.findViewById(R.id.describe);
            descc.setText(des);
        }

        public void setImage(Context ctt, String image){

            ImageView igm = (ImageView)findViewById(R.id.img);

            //Picasso.with(ctt).load(image).into(igm);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add) {

            Intent ii = new Intent(MainActivity.this, AddBlog.class);
            startActivity(ii);


        }
        return super.onOptionsItemSelected(item);
    }
}