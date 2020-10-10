package com.playxcodes.abrircamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewFoto;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        //verifica se tem a permissão da camera se nao tiver ele solicita

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String [] {Manifest.permission.CAMERA},0);
        }


        imageViewFoto = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tirarFoto();

            }
        });

    }



    public void tirarFoto(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1); //precisamos identificar o requestcode que e o resultado da actibity


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //verifica se o resultado que chegou aqui e o resultado vindo da intent que eu solicitei
        if (requestCode ==1 && resultCode == RESULT_OK){ //verifica se deu tudo certo ou seja se a foto foi tirada
            Bundle extra = data.getExtras(); // recupera a imagem que veio da outra activity

            //exibir imagem para o usuario

            Bitmap imagem = (Bitmap) extra.get("data"); //passando a chave data

            imageViewFoto.setImageBitmap(imagem);


        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
