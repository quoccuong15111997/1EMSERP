package com.firstems.erp.ui.product.barcode;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.api.model.response.product.ProgressStep;
import com.firstems.erp.common.Constant;
import com.firstems.erp.helper.animation.AnimationHelper;
import com.firstems.erp.ui.product.ProductActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class ScannerActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener{
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private Button switchFlashlightButton;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        AnimationHelper.getInstance().setAnimationRightToLeft(this);
        addControls();
        addEvents();
        BarcodeCallback barcodeCallback = new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if (result!=null){
                    Intent intent = new Intent(ScannerActivity.this, ProductActivity.class);
                    ProgressItem progressItem = new ProgressItem();
                    progressItem.setCmmdcode(result.getText().trim());
                    progressItem.setCmmddate("2020-10-12T00:00:00");
                    intent.putExtra(Constant.NAME_PUT_PROGRESS_PRODUCT, progressItem);
                    startActivity(intent);
                    finish();
                }
            }
        };
        barcodeScannerView = (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.setTorchListener(this);
        barcodeScannerView.decodeContinuous(barcodeCallback);

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.setShowMissingCameraPermissionDialog(true);

        switchFlashlightButton = findViewById(R.id.switch_flashlight);
        if (!hasFlash()) {
            switchFlashlightButton.setVisibility(View.GONE);
        }
    }

    private void addEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AnimationHelper.getInstance().setAnimationLeftToRight(ScannerActivity.this);
            }
        });
    }

    private void addControls() {
        imgBack = findViewById(R.id.ivBack);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AnimationHelper.getInstance().setAnimationLeftToRight(ScannerActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                System.out.println("BARCODE"+result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    /**
     * Check if the device's camera has a Flashlight.
     * @return true if there is Flashlight, otherwise false.
     */
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }


    @Override
    public void onTorchOn() {
        switchFlashlightButton.setText("Turn Off Flash");
    }

    @Override
    public void onTorchOff() {
        barcodeScannerView.setTorchOff();
        System.out.println("Turn On Flash");
    }
    public void switchFlashlight(View view) {
        if ("Turn On Flash".equals(switchFlashlightButton.getText())) {
            barcodeScannerView.setTorchOn();
        } else {
            barcodeScannerView.setTorchOff();
        }
    }
}