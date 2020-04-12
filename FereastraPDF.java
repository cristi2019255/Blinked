package com.example.blinked;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class FereastraPDF extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fereastra_vezi_pdf);
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        String fileName = Environment.getExternalStorageDirectory()+"/donare sange.pdf";
        pdfView.fromFile(new File(fileName)).load();
    }
}
