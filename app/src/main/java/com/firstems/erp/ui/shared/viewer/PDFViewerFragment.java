package com.firstems.erp.ui.shared.viewer;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.firstems.erp.R;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.PDFViewerFragmentBinding;
import com.firstems.erp.helper.file.GetFileHelper;
import com.firstems.erp.model.FileIncludeModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;

public class PDFViewerFragment extends CommonFragment {
    
    private PDFViewerViewModel mViewModel;
    private PDFViewerFragmentBinding binding;
    private TextView txtTitle;
    private ProgressDialog progressDoalog;
    private File fileLoad= null;
    private FileIncludeModel fileIncludeModel;
    private boolean flagDownload = false;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.p_d_f_viewer_fragment, container, false);
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void addEvents() {
        binding.include23.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
    
    private void addControls() {
        txtTitle = binding.include23.findViewById(R.id.txtTitle);
        Intent intent = getActivity().getIntent();
        fileIncludeModel = (FileIncludeModel) intent.getSerializableExtra(Constant.NAME_PUT_PDF_MODEL_FILE);
        if (fileIncludeModel!=null){
            progressDoalog = new ProgressDialog(getContext());
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Đang tải....");
            progressDoalog.setTitle(fileIncludeModel.getFileName());
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDoalog.setCancelable(false);
            progressDoalog.show();
            Handler handle = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progressDoalog.incrementProgressBy(1);
                }
            };
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (progressDoalog.getProgress() <= progressDoalog
                                .getMax()) {
                            Thread.sleep(30);
                            handle.sendMessage(handle.obtainMessage());
                            if (progressDoalog.getProgress() == progressDoalog.getMax()) {
                                progressDoalog.dismiss();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        GetFileHelper.getInstance().doGetFile("http://api-dev.firstems.com" + fileIncludeModel.getLink(),
                SharedPreferencesManager.getInstance().getPrefToken(),
                String.valueOf(fileIncludeModel.getFileName()+System.currentTimeMillis()),
                fileIncludeModel.getFileType()
                ,getContext(), new GetFileHelper.GetPdfFileCallback() {
                    @Override
                    public void onGetFile(File file) {
                        // open_File(file);
                        progressDoalog.dismiss();
                        startOpen(file);
                    }
                });
        
        txtTitle.setText(fileIncludeModel.getFileName());
    }
    
    private void startOpen(File file) {
        switch (fileIncludeModel.getFileType().toLowerCase()){
            case "pdf" : {openPDF(file); break;}
            case "xlsx" : {openExcelFile(file,"xlsx"); break;}
            case "xls" : {openExcelFile(file,"xls"); break;}
            case "docx" : {openExcelFile(file,"docx"); break;}
            case "doc" : {openExcelFile(file,"doc"); break;}
            default: {noFileSupport(); break;}
        }
    }
    
    private void noFileSupport() {
        showErrorDialog("KHÔNG THỰC HIỆN ĐƯỢC","File không hỗ trợ");
    }
    
    private void openWordFile(File file, String ext) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(file.getAbsolutePath()), "application/vnd.ms-excel");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        try {
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            showDialogNonSoft();
        }
    }
    
    private void openExcelFile(File file,String ext) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW);
        myIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri fileUri = FileProvider.getUriForFile(getContext(), getActivity().getApplicationContext().getPackageName() + ".provider", file);
        String mime= null;
        try {
            mime = URLConnection.guessContentTypeFromStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(mime==null) mime=URLConnection.guessContentTypeFromName(file.getName());
        myIntent.setDataAndType(fileUri, mime);
        
        try {
            startActivity(myIntent);
            getActivity().finish();
        }
        catch (ActivityNotFoundException e) {
            showDialogNonSoft();
        }
    }
    
    private void openPDF(File file) {
        binding.pdfView.fromFile(file)
                .enableAnnotationRendering(true)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                    
                    }
                })
                .scrollHandle(new DefaultScrollHandle(getContext()))
                .spacing(10) // in dp
                .onPageError(new OnPageErrorListener() {
                    @Override
                    public void onPageError(int page, Throwable t) {
                    
                    }
                })
                .load();
        binding.pdfView.useBestQuality(true);
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PDFViewerViewModel.class);
    }
    public void open_File(File file) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        Uri apkURI = FileProvider.getUriForFile(getContext(),getActivity().getPackageName(),file);
        
        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        String mimeType=myMime.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(apkURI.toString()));
        intent.setDataAndType(apkURI, mimeType);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }
    private void showDialogNonSoft() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_about);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        // ((TextView) dialog.findViewById(R.id.tv_version)).setText("Version " + BuildConfig.VERSION_NAME);
        
        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        
        dialog.findViewById(R.id.bt_getcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAction(getActivity());
            }
        });
        
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    public static void rateAction(Activity activity) {
        Uri uri = Uri.parse("market://details?id=" + "com.microsoft.office.officehubrow&hl=vi");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + "com.microsoft.office.officehubrow&hl=vi")));
        }
    }
}