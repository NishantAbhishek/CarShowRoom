package com.example.carshowroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carshowroom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;

public class PostCars extends AppCompatActivity {
    Uri imageUri;
    StorageReference storageReference;
    EditText description,price;
    ImageView close,image_added;
    TextView post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_cars);

        description = findViewById(R.id.description);
        close = findViewById(R.id.close);
        image_added = findViewById(R.id.image_added);
        post = findViewById(R.id.post);
        price = findViewById(R.id.price);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostCars.this,MainActivity.class));
                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        storageReference = FirebaseStorage.getInstance().getReference().child("CarsPosted");
        CropImage.activity().setAspectRatio(1,1).start(PostCars.this);
    }

    private void uploadImage(){
        final ProgressDialog progressDialog = new ProgressDialog(PostCars.this);
        progressDialog.setMessage("Uploading......");
        progressDialog.show();

        if(imageUri != null){
            final StorageReference referencefile = storageReference.child(System.currentTimeMillis()+"."+imageUri.getLastPathSegment());
            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CarsPosted");

            referencefile.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    referencefile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());

                            HashMap<String,Object> hashMap = new HashMap<>();
                            hashMap.put("postimage",String.valueOf(uri));
                            hashMap.put("description",description.getText().toString());
                            hashMap.put("price",price.getText().toString());
                            reference.child(currentDateTime).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        progressDialog.dismiss();
                                        startActivity(new Intent(PostCars.this,MainActivity.class));
                                        Toast.makeText(PostCars.this,"Your car is added", LENGTH_SHORT).show();
                                    }else{
                                        progressDialog.dismiss();
                                        Toast.makeText(PostCars.this,"Failure", LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    });

                }
            });

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            image_added.setImageURI(imageUri);
        }else {
            Toast.makeText(PostCars.this,"There is some problem", LENGTH_SHORT).show();
            startActivity(new Intent(PostCars.this,MainActivity.class));
            finish();
        }
    }


}
