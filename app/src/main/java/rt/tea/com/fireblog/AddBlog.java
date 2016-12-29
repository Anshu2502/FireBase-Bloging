package rt.tea.com.fireblog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by acer on 12/18/2016.
 */

public class AddBlog extends Activity {

    ImageButton ib;
    Button submit;
    EditText project_title, project_describtion;
    public static final int Success = 1;
    private Uri image;
    private StorageReference mstorage;
    private ProgressDialog mpregress;
    private DatabaseReference mdatabase;


    public void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.add_blog);
        mstorage = FirebaseStorage.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Blog");


        ib = (ImageButton)findViewById(R.id.imagee);
        project_title = (EditText)findViewById(R.id.title);
        project_describtion = (EditText)findViewById(R.id.describe);
        submit = (Button)findViewById(R.id.sub);
        mpregress = new ProgressDialog(this);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent getimage = new Intent(Intent.ACTION_GET_CONTENT);
                getimage.setType("*/*");
                startActivityForResult(getimage,Success);


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });



    }


    public void startPosting(){


        final String titl = project_title.getText().toString().trim();
        final String describb = project_describtion.getText().toString().trim();

        if(!TextUtils.isEmpty(titl) && !TextUtils.isEmpty(describb) && image!=null){

            mpregress.setMessage("Posting Blog....");
            mpregress.show();
         StorageReference filepath = mstorage.child("Blog_Image").child(image.getLastPathSegment());
            filepath.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri down = taskSnapshot.getDownloadUrl();
                    DatabaseReference deata = mdatabase.push();
                    deata.child("Title").setValue(titl);
                    deata.child("Describtion").setValue(describb);
                    deata.child("Image").setValue(down.toString());

                mpregress.hide();
                    startActivity(new Intent(AddBlog.this,MainActivity.class));
                    finish();
                }
            });

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Success && resultCode== RESULT_OK){
            image =  data.getData();
            ib.setImageURI(image);
        }


    }
}
