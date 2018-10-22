//package com.example.pbetkows.wms.utils;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.pbetkows.wms.MainActivity;
//import com.example.pbetkows.wms.R;
//import com.google.zxing.Result;
//
//import me.dm7.barcodescanner.zxing.ZXingScannerView;
//
//public class DataWedge extends AppCompatActivity {
//
//    Button scanButton;
//    EditText resultText;
//    String barcode;
//    private ZXingScannerView scannerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.data_wedge_activity);
//
//        resultText = findViewById(R.id.resultEditText);
//        scanButton = findViewById(R.id.dataWedgeButton);
//
//    }
//
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        scannerView.stopCamera();
//    }
//
//    public void scan(View view)
//    {
//        scannerView = new ZXingScannerView(this);
//        scannerView.setResultHandler(new ScannerHandler());
//
//        setContentView(scannerView);
//        scannerView.startCamera();
//    }
//
//    class ScannerHandler implements ZXingScannerView.ResultHandler {
//        @Override
//        public void handleResult(Result result) {
//
//            barcode = result.getText();
//            Toast.makeText(DataWedge.this, barcode, Toast.LENGTH_LONG).show();  //wyświetla poprawnie
//            setContentView(R.layout.data_wedge_activity);
//            resultText = findViewById(R.id.resultEditText);
//            resultText.setText(barcode);
//            scannerView.stopCamera();
//        }
//    }
//}
