package com.example.adwindow.adwindow_admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adwindow.adwindow_admin.model.Location;
import com.example.adwindow.adwindow_admin.model.Screen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int FILE_CHOOSE_CODE = 199;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    Uri filePath;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addLocButton = findViewById(R.id.addLocButton);
        Button chooseFile = findViewById(R.id.chooseScreenLocImage);
        Button uploadFile = findViewById(R.id.uploadScreenLocImage);
        Button addScreen = findViewById(R.id.addScreenButton);
        addLocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText lName = findViewById(R.id.locText);
                Location location = new Location(lName.getText().toString());
                databaseReference.child("Cities").child(lName.getText().toString()).setValue(location)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"City Added",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });
        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.screenTitle);
                EditText screenCity = findViewById(R.id.screenCity);
                if(!editText.getText().toString().equals("") && !screenCity.getText().toString().equals("")) {
                    uploadFile(editText.getText().toString(),screenCity.getText().toString());
                }
            }
        });
        addScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText screenCity = findViewById(R.id.screenCity);
                EditText screenTitle = findViewById(R.id.screenTitle);
                EditText screenAddress = findViewById(R.id.screenAddress);
                EditText footfall = findViewById(R.id.Footfall);
                EditText screenpricing = findViewById(R.id.spricing);
                EditText sNumScreens = findViewById(R.id.sNumScreens);
                EditText screenLocImageUrl = findViewById(R.id.screeLocImageUrl);

                Screen screen = new Screen(screenCity.getText().toString(),screenTitle.getText().toString(),screenAddress.getText().toString(),
                        screenLocImageUrl.getText().toString(),footfall.getText().toString(),screenpricing.getText().toString(),Integer.parseInt(sNumScreens.getText().toString()));

                Map<String,Object> multiUpdates = new HashMap<>();
                String key = databaseReference.child("Cities").child(screenCity.getText().toString()).child("screenLocationTitles").push().getKey();
                multiUpdates.put("/Screens/"+screenCity.getText().toString()+"/"+screenTitle.getText().toString(),screen);
                multiUpdates.put("/Cities/"+screenCity.getText().toString()+"/screenLocationTitles/"+key, screenTitle.getText().toString());
                databaseReference.updateChildren(multiUpdates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Screen Added to Database",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void chooseFile()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try{
            startActivityForResult(Intent.createChooser(intent, "Select Content"), FILE_CHOOSE_CODE);
        }
        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(MainActivity.this,"Please install a file manager",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == FILE_CHOOSE_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                if (data != null && data.getData()!=null) {
                    filePath = data.getData();
                    Toast.makeText(MainActivity.this, filePath.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void uploadFile(String screenTitle, String city)
    {
        if(filePath!=null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            final StorageReference locationImageRef = storageReference.child("ScreenLocationImage/"+city+"/"+screenTitle+"."+getFileExtension(filePath));
            locationImageRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    locationImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            EditText editText = findViewById(R.id.screeLocImageUrl);
                            editText.setText(uri.toString());
                            Toast.makeText(MainActivity.this,uri.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                    progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                }
            });
        }
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}

