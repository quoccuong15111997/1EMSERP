package com.firstems.erp.ui.shared.file;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;

import com.firstems.erp.R;
import com.firstems.erp.adapter.FileInludeAdapter;
import com.firstems.erp.adapter.ImageIncludeAdapter;
import com.firstems.erp.api.model.response.reviewprocess.documentfile.DocumentFile;
import com.firstems.erp.callback.ImageClickCallback;
import com.firstems.erp.callback.ItemFileClick;
import com.firstems.erp.callback.PermissionCallback;
import com.firstems.erp.callback.SaveFileToLocalCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.FileFragmentBinding;
import com.firstems.erp.helper.file.GetFileHelper;
import com.firstems.erp.helper.permision.PermissionProvider;
import com.firstems.erp.helper.widgets.SpacingItemDecoration;
import com.firstems.erp.model.FileIncludeModel;
import com.firstems.erp.model.ImageModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.image.ImageViewActivity;
import com.firstems.erp.ui.shared.viewer.PDFViewerActivity;
import com.firstems.erp.utils.Tools;
import com.github.mikephil.charting.formatter.IFillFormatter;

import org.apache.commons.io.FilenameUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.models.sort.SortingTypes;
import droidninja.filepicker.utils.ContentUriUtils;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;

public class FileFragment extends CommonFragment implements EasyPermissions.PermissionCallbacks {
    
    private FileViewModel mViewModel;
    private FileFragmentBinding binding;
    private RecyclerView recyclerViewFileInclude;
    public static List<FileIncludeModel> fileIncludeList;
    private FileInludeAdapter fileInludeAdapter;
    private RecyclerView recyclerImage;
    private ImageIncludeAdapter imageIncludeAdapter;
    private final static int LOADING_DURATION = 2000;
    private Transition transition;
    private List<DocumentFile> documentFileList;
    private int CAMERA_PIC_REQUEST = 1;
    private int SELECT_FILE = 2;
    private int PICKFILE_RESULT_CODE = 22222;
    private int CODE_OPEN_CAMERA_SHOTTING = 33333;
    private int flagPermission = 0;
    private PermissionCallback permissionCallback;
    public static List<String> listFilePath;
    private List<ImageModel> imageModelList;
    public static List<ImageModel> imageModelListRemove = new ArrayList<>();
    
    public static final int RC_PHOTO_PICKER_PERM = 123;
    public static final int RC_STORGARE_PERM = 124;
    public static final int RC_FILE_PICKER_PERM = 321;
    private static final int CUSTOM_REQUEST_CODE = 532;
    private int MAX_ATTACHMENT_COUNT = 10;
    
    private boolean isShowChooseFile = true;
    public void setEditable(boolean editable) {
        isEditable = editable;
    }
    
    public void setShowChooseFile(boolean showChooseFile) {
        isShowChooseFile = showChooseFile;
    }
    
    private boolean isEditable = true;
    private ArrayList<Uri> photoPaths = new ArrayList<>();
    private ArrayList<Uri> docPaths = new ArrayList<>();
    public FileFragment(List<DocumentFile> documentFileList) {
        this.documentFileList = documentFileList;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.file_fragment, container, false);
        
        transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(600);
        transition.addTarget(binding.getRoot());
        
        initText();
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initText() {
        binding.txtTitleImage.setText(SharedPreferencesManager.getSystemLabel(141) /*Hình ảnh*/);
        binding.textView11.setText(SharedPreferencesManager.getSystemLabel(142) /*File đính kèm*/);
    }
    
    private void addEvents() {
        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionCamera();
                
            }
        });
        binding.imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });
    }
    private void checkPermission(){
        boolean res = PermissionProvider.getInstance().requestPermissionW(READ_EXTERNAL_STORAGE, com.firstems.erp.utils.Constant.CODE_REQUEST_PERMISSION_READ,getActivity());
        if (res){
            openFileSelect();
        }
    }
    @AfterPermissionGranted(RC_STORGARE_PERM)
    private void openFileSelect(){
        if (EasyPermissions.hasPermissions(getContext(), READ_EXTERNAL_STORAGE)){
            if (EasyPermissions.hasPermissions(getContext(),WRITE_EXTERNAL_STORAGE)){
                pickDocClicked();
            }
            else {
                EasyPermissions.requestPermissions(getActivity(), SharedPreferencesManager.getSystemLabel(193) /*Truy cập bộ nhớ thiết bị*/,
                        RC_STORGARE_PERM, WRITE_EXTERNAL_STORAGE);
            }
        }
        else {
            EasyPermissions.requestPermissions(getActivity(), SharedPreferencesManager.getSystemLabel(193) /*Truy cập bộ nhớ thiết bị*/,
                    RC_STORGARE_PERM, READ_EXTERNAL_STORAGE);
        }
    }
    @AfterPermissionGranted(RC_PHOTO_PICKER_PERM)
    public void pickPhotoClicked() {
        if (EasyPermissions.hasPermissions(getContext(), FilePickerConst.PERMISSIONS_FILE_PICKER)) {
            onPickPhoto();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(getActivity(), SharedPreferencesManager.getSystemLabel(193) /*Truy cập bộ nhớ thiết bị*/,
                    RC_PHOTO_PICKER_PERM, FilePickerConst.PERMISSIONS_FILE_PICKER);
        }
    }
    
    private void onPickPhoto() {
        int maxCount = MAX_ATTACHMENT_COUNT - docPaths.size();
        if ((docPaths.size() + photoPaths.size()) == MAX_ATTACHMENT_COUNT) {
            Toast.makeText(getContext(), "Không thể chọn quá " + MAX_ATTACHMENT_COUNT + " file",
                    Toast.LENGTH_SHORT).show();
        } else {
            FilePickerBuilder.getInstance()
                    .setMaxCount(maxCount)
                    .setSelectedFiles(photoPaths) //this is optional
                    .setActivityTheme(R.style.FilePickerTheme)
                    .setActivityTitle(SharedPreferencesManager.getSystemLabel(194) /*Chọn hình ảnh đính kèm*/)
                    .enableVideoPicker(false)
                    .enableCameraSupport(true)
                    .showGifs(false)
                    .showFolderView(true)
                    .enableSelectAll(false)
                    .enableImagePicker(true)
                    .setCameraPlaceholder(R.drawable.ic_camera)
                    .withOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .enableCameraSupport(true)
                    .pickPhoto(this, CUSTOM_REQUEST_CODE);
        }
    }
    @AfterPermissionGranted(RC_FILE_PICKER_PERM)
    public void pickDocClicked() {
        if (EasyPermissions.hasPermissions(getActivity(), FilePickerConst.PERMISSIONS_FILE_PICKER)) {
            onPickDoc();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this,SharedPreferencesManager.getSystemLabel(193) /*Truy cập bộ nhớ thiết bị*/ ,
                    RC_FILE_PICKER_PERM, FilePickerConst.PERMISSIONS_FILE_PICKER);
        }
    }
    
    private void onPickDoc() {
        String[] zips = {"zip", "rar"};
        String[] pdfs = {"aac","pdf"};
        int maxCount = MAX_ATTACHMENT_COUNT - photoPaths.size();
        if ((docPaths.size() + photoPaths.size()) == MAX_ATTACHMENT_COUNT) {
            Toast.makeText(getContext(), "Không thể chọn quá " + MAX_ATTACHMENT_COUNT + " file",
                    Toast.LENGTH_SHORT).show();
        } else {
            FilePickerBuilder.getInstance()
                    .setMaxCount(maxCount)
                    .setSelectedFiles(docPaths)
                    .setActivityTheme(R.style.FilePickerTheme)
                    .setActivityTitle(SharedPreferencesManager.getSystemLabel(195) /*Chọn File đính kèm*/)
                    .addFileSupport("ZIP", zips)
                    .addFileSupport("AAC", pdfs, R.drawable.ic_pdf)
                    .enableDocSupport(true)
                    .enableSelectAll(false)
                    .sortDocumentsBy(SortingTypes.name)
                    .withOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .pickFile(this);
        }
    }
    private void checkPermissionCamera() {
        boolean res = PermissionProvider.getInstance().requestPermissionW(Manifest.permission.CAMERA, com.firstems.erp.utils.Constant.CODE_REQUEST_PERMISSION_CAMERA,getActivity());
        if (res){
            openCamera();
        }
    }
    private void openCamera() {
        pickPhotoClicked();
    }
    private void addControls() {
        EasyPermissions.requestPermissions(getActivity(), SharedPreferencesManager.getSystemLabel(193) /*Truy cập bộ nhớ thiết bị*/,
                RC_PHOTO_PICKER_PERM, FilePickerConst.PERMISSIONS_FILE_PICKER);
        EasyPermissions.requestPermissions(getActivity(), SharedPreferencesManager.getSystemLabel(193) /*Truy cập bộ nhớ thiết bị*/,
                RC_STORGARE_PERM, READ_EXTERNAL_STORAGE);
        imageModelList = new ArrayList<>();
        fileIncludeList = new ArrayList<>();
        recyclerViewFileInclude=binding.recycleFile;
        recyclerViewFileInclude.setVisibility(View.GONE);
        recyclerImage = binding.recycleImage;
        recyclerImage.setVisibility(View.GONE);
        recyclerImage.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerImage.addItemDecoration(new SpacingItemDecoration(3, Tools.dpToPx(getContext(), 2), true));
        fileInludeAdapter = new FileInludeAdapter(fileIncludeList, new ItemFileClick() {
            @Override
            public void onItemClick(FileIncludeModel fileIncludeModel) {
                System.out.println(fileIncludeModel.getLink());
                openFileInclude(fileIncludeModel);
                if (fileIncludeModel.getFileType().equals("png") || fileIncludeModel.getFileType().equals("jpg")){
                
                }
                else {
                
                }
            }
            
            @Override
            public void onRemoveClick(int position) {
                if (fileIncludeList.get(position).getLoadType()==1){
                    ImageModel imageModel = new ImageModel();
                    imageModel.setImgPath(fileIncludeList.get(position).getFilePath());
                    imageModel.setFileCode(fileIncludeList.get(position).getFileCode());
                    imageModelListRemove.add(imageModel);
                }
                fileIncludeList.remove(position);
                fileInludeAdapter.notifyDataSetChanged();
            }
        });
        fileInludeAdapter.setRemoveIsVisible(isEditable);
        imageIncludeAdapter = new ImageIncludeAdapter(imageModelList, new ImageClickCallback() {
            @Override
            public void onRemoveClick(int position) {
                if (imageModelList.get(position).getType() == 1){
                    imageModelListRemove.add(imageModelList.get(position));
                }
                if (imageModelList.get(position).getType() == 2){
                    for (int i = 0;i< listFilePath.size();i++){
                        String s = listFilePath.get(i);
                        if (s.equals(imageModelList.get(position).getImgPath())){
                            listFilePath.remove(i);
                            break;
                        }
                    }
                }
                imageModelList.remove(position);
                imageIncludeAdapter.notifyDataSetChanged();
            }
            
            @Override
            public void onClickImage(int position) {
                Intent intent = new Intent(getContext(), ImageViewActivity.class);
                intent.putExtra(Constant.NANE_PUT_LIST_BITMAP, (Serializable) imageModelList);
                intent.putExtra(Constant.NANE_PUT_POSITION_ITEM_CLICK,position);
                startActivity(intent);
            }
        });
        binding.constraintLayout.setVisibility(isShowChooseFile ? View.VISIBLE : View.GONE);
        imageIncludeAdapter.setEditable(isEditable);
        listFilePath = new ArrayList<>();
        getData();
        initComponent();
    }
    
    private void getData() {
        if (documentFileList.size()>0){
            for (DocumentFile file : documentFileList){
                if (file.getFileType()!=null){
                    if (file.getFileType().equals("png") || file.getFileType().equals("jpg")){
                        ImageModel imageModel = new ImageModel();
                        imageModel.setType(1);
                        imageModel.setImgUrl(file.getLinkFile());
                        imageModel.setFileCode(file.getFileCode());
                        imageModelList.add(imageModel);
                        imageIncludeAdapter.notifyDataSetChanged();
                    }
                    else {
                        FileIncludeModel fileIncludeModel = new FileIncludeModel();
                        fileIncludeModel.setFileName(file.getFileName());
                        fileIncludeModel.setLink(file.getLinkFile());
                        fileIncludeModel.setFileCode(file.getFileCode());
                        fileIncludeModel.setLoadType(1);
                        System.out.println(fileIncludeModel.getLink());
                        if (file.getFileType().toLowerCase().equals("pdf")){
                            fileIncludeModel.setIcon(R.drawable.ic_pdf_file);
                        }
                        if (file.getFileType().toLowerCase().equals("xlsx") || file.getFileType().toLowerCase().equals("xls")){
                            fileIncludeModel.setIcon(R.drawable.ic_excel);
                        }
                        if (file.getFileType().toLowerCase().equals("docx") || file.getFileType().toLowerCase().equals("doc")){
                            fileIncludeModel.setIcon(R.drawable.ic_word_file);
                        }
                        else if (file.getFileType().equals("")){
                            fileIncludeModel.setIcon(R.drawable.ic_error_image);
                        }
                        fileIncludeModel.setFileType(file.getFileType());
                        fileIncludeList.add(fileIncludeModel);
                    }
                }
            }
        }
        binding.txtTitleImage.setVisibility(imageModelList.size()==0 ? View.GONE : View.VISIBLE);
        binding.textView11.setVisibility(fileIncludeList.size() == 0 ? View.GONE : View.VISIBLE);
    }
    
    private void initComponent() {
        recyclerViewFileInclude.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutProgress = new LinearLayoutManager(getContext());
        linearLayoutProgress.setOrientation(RecyclerView.VERTICAL);
        
        LinearLayoutManager linearLayoutFile= new LinearLayoutManager(getContext());
        linearLayoutFile.setOrientation(RecyclerView.VERTICAL);
        recyclerViewFileInclude.setAdapter(fileInludeAdapter);
        recyclerViewFileInclude.setLayoutManager(linearLayoutFile);
        recyclerViewFileInclude.setHasFixedSize(true);
        
        recyclerImage.setVisibility(View.VISIBLE);
        recyclerImage.setHasFixedSize(true);
        recyclerImage.setAdapter(imageIncludeAdapter);
        recyclerImage.setHasFixedSize(true);
        
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FileViewModel.class);
        // TODO: Use the ViewModel
    }
    private void openFileInclude(FileIncludeModel fileIncludeModel){
        Intent intent = new Intent(getContext(), PDFViewerActivity.class);
        intent.putExtra(Constant.NAME_PUT_PDF_MODEL_FILE,fileIncludeModel);
        startActivity(intent);
        
        /*GetFileHelper.getInstance().doGetFile("http://api-dev.firstems.com" + fileIncludeModel.getLink(),
                SharedPreferencesManager.getInstance().getPrefToken(),
                String.valueOf(System.currentTimeMillis()),
                fileIncludeModel.getFileType()
                , getContext()
                , new GetFileHelper.GetPdfFileCallback() {
                    @Override
                    public void onGetFile(File file) {
                        open_File(file);
                            *//*binding.pdfView.fromFile(file)
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
                            binding.pdfView.useBestQuality(true);*//*
                    }
                });*/
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode== com.firstems.erp.utils.Constant.CODE_REQUEST_PERMISSION_READ){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                checkPermission();
            }
            else
                showFailPermission();
        }
        else if (requestCode== com.firstems.erp.utils.Constant.CODE_REQUEST_PERMISSION_WRITE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                flagPermission++;
                
            }
            else
                showFailPermission();
        }
        else if (requestCode== com.firstems.erp.utils.Constant.CODE_REQUEST_PERMISSION_CAMERA){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                checkPermissionCamera();
            }
            else
                showFailPermission();
        }
        else if (requestCode== RC_STORGARE_PERM){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openFileSelect();
            }
            else
                showFailPermission();
        }
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    
    private void resultPermissionCheck() {
        if (flagPermission==3){
            permissionCallback.onCheckPermissionComplete();
        }
    }
    private void showFailPermission() {
    }
    public static File bitmapToFile(Bitmap bitmap, String fileNameToSave) {
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + fileNameToSave);
            file.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 , bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        }catch (Exception e){
            e.printStackTrace();
            return file;
        }
    }
    private void openFileExplorer(){
        Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
        fileintent.setType("gagt/sdf");
        try {
            startActivityForResult(fileintent, PICKFILE_RESULT_CODE);
        } catch (ActivityNotFoundException e) {
            Log.e("tag", "No activity can handle picking a file. Showing alternatives.");
        }
    }
    private void loadByStorge(String path) {
        try{
            ImageModel imageModel = new ImageModel();
            imageModel.setType(2);
            imageModel.setImgPath(path);
            imageModelList.add(imageModel);
            imageIncludeAdapter.notifyDataSetChanged();
        }
        catch (Exception ex){
            Log.e("LOI",ex.toString());
        }
    }
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    public Bitmap getThumbnail(String pathHinh) {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathHinh, bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
            return null;
        int originalSize = (bounds.outHeight > bounds.outWidth) ?
                bounds.outHeight
                : bounds.outWidth;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = originalSize / 500;
        return BitmapFactory.decodeFile(pathHinh, opts);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK && data != null) {
            Bitmap bitmapCamera = (Bitmap) data.getExtras().get("data");
            GetFileHelper.getInstance().saveImageToLocalApp(getContext(), bitmapCamera, new SaveFileToLocalCallback() {
                @Override
                public void onSaveSuccess(String path) {
                    ImageModel imageModel = new ImageModel();
                    imageModel.setType(2);
                    imageModel.setImgPath(path);
                    imageModelList.add(imageModel);
                    imageIncludeAdapter.notifyDataSetChanged();
                }
                
                @Override
                public void onSaveFail(String mess) {
                
                }
            });
        }
        if (requestCode == CODE_OPEN_CAMERA_SHOTTING && resultCode == RESULT_OK && data!=null){
            String path = data.getStringExtra(Constant.NAME_PUT_PATH_IMAGE);
            ImageModel imageModel = new ImageModel();
            imageModel.setType(2);
            imageModel.setImgPath(path);
            imageModelList.add(imageModel);
            imageIncludeAdapter.notifyDataSetChanged();
        }
        if (requestCode == SELECT_FILE && resultCode == RESULT_OK && data != null) {
            Uri uri=data.getData();
            String path=getRealPathFromURI(uri);
            loadByStorge(path);
            listFilePath.add(path);
        }
        if (requestCode == PICKFILE_RESULT_CODE && resultCode == RESULT_OK){
            String filePath = data.getData().getPath();
            listFilePath.add(filePath);
        }
        switch (requestCode) {
            case CUSTOM_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    ArrayList<Uri> dataList = data.getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                    if (dataList != null) {
                        photoPaths = new ArrayList<Uri>();
                        photoPaths.addAll(dataList);
                    }
                }
                break;
            
            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    ArrayList<Uri> dataList = data.getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS);
                    if (dataList != null) {
                        docPaths = new ArrayList<>();
                        docPaths.addAll(dataList);
                    }
                }
                break;
        }
        
        addThemToView(photoPaths, docPaths);
        photoPaths.clear();
        docPaths.clear();
    }
    
    private void addThemToView(ArrayList<Uri> photoPaths, ArrayList<Uri> docPaths) {
        try {
            for (Uri uri : photoPaths){
                String path = ContentUriUtils.INSTANCE.getFilePath(getContext(), uri);
                ImageModel imageModel = new ImageModel();
                imageModel.setType(2);
                imageModel.setImgPath(path);
                imageModelList.add(imageModel);
                listFilePath.add(path);
            }
            for (Uri uri : docPaths){
                String path = ContentUriUtils.INSTANCE.getFilePath(getContext(), uri);
                File file = new File(path);
                FileIncludeModel fileIncludeModel= new FileIncludeModel();
                fileIncludeModel.setFileName(file.getName());
                fileIncludeModel.setLoadType(2);
                String ext = FilenameUtils.getExtension(file.getName());
                fileIncludeModel.setFileType(ext);
                fileIncludeModel.setFilePath(path);
                
                switch (ext.toLowerCase()){
                    case "pdf" : {fileIncludeModel.setIcon(R.drawable.ic_pdf_file); break;}
                    case "xlsx" : {fileIncludeModel.setIcon(R.drawable.ic_excel); break;}
                    case "xls" : {fileIncludeModel.setIcon(R.drawable.ic_excel); break;}
                    case "docx" : {fileIncludeModel.setIcon(R.drawable.ic_word_file); break;}
                    case "doc" : {fileIncludeModel.setIcon(R.drawable.ic_word_file); break;}
                    case "zip" : {fileIncludeModel.setIcon(R.drawable.ic_zip_file); break;}
                    case "rar" : {fileIncludeModel.setIcon(R.drawable.ic_zip_file); break;}
                    default: {fileIncludeModel.setIcon(R.drawable.icon_file_unknown); break;}
                }
                fileIncludeList.add(fileIncludeModel);
            }
            fileInludeAdapter.notifyDataSetChanged();
            imageIncludeAdapter.notifyDataSetChanged();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    
    }
    
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
    
    }
}