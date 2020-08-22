package ru.kostya.implementsdrawerlayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    ImageView profileImageView;

    EditText urlEd;
    ImageView picassoImage;
    Button loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         profileImageView = findViewById(R.id.profile_image);

         urlEd = findViewById(R.id.urlEd);
         picassoImage = findViewById(R.id.picasso_image);
         loadBtn = findViewById(R.id.loadBtn);


        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlImage = urlEd.getText().toString();
                if (!TextUtils.isEmpty(urlImage)){
                    //uses - permisson INTERNET - ОБЯЗАТЛЬНО!
                    Picasso.with(MainActivity.this)
                            .load("https://www.nastol.com.ua/download.php?img=201502/2560x1600/nastol.com.ua-128108.jpg")
                            .into(picassoImage);
                } else {
                    Toast.makeText(MainActivity.this, "Введите url картинки", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                //Указываем,что можно выбрать только фото
                //getIntent.setType("file/*"); - выбор файла
                getIntent.setType("image/*");
                startActivityForResult(getIntent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null){
                //data.getData() - возвращает uri
                Uri uri = data.getData();
                //Устанавливаем фото с помощью uri - setImageURI(Uri uri)
                profileImageView.setImageURI(uri);
            }
        }
    }
}