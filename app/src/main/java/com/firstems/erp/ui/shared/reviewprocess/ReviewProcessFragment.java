package com.firstems.erp.ui.shared.reviewprocess;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;

import com.firstems.erp.R;
import com.firstems.erp.adapter.FileInludeAdapter;
import com.firstems.erp.adapter.ImageIncludeAdapter;
import com.firstems.erp.adapter.ReviewProgressAdapter;
import com.firstems.erp.api.model.response.reviewprocess.ReviewProcessDetail;
import com.firstems.erp.api.model.response.reviewprocess.ReviewProcessItem;
import com.firstems.erp.api.model.response.reviewprocess.documentfile.DocumentFile;
import com.firstems.erp.callback.ImageClickCallback;
import com.firstems.erp.callback.ItemFileClick;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.ReviewProcessFragmentBinding;
import com.firstems.erp.helper.widgets.SpacingItemDecoration;
import com.firstems.erp.model.FileIncludeModel;
import com.firstems.erp.model.ImageModel;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.ui.shared.image.ImageViewActivity;
import com.firstems.erp.ui.shared.viewer.PDFViewerActivity;
import com.firstems.erp.utils.Tools;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerConst;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ReviewProcessFragment extends CommonFragment {

    private ReviewProcessViewModel mViewModel;
    private List<ReviewProcessDetail> list;
    private ReviewProgressAdapter reviewProgressAdapter;
    private RecyclerView recyclerView;
    private ReviewProcessFragmentBinding binding;
    private RecyclerView recyclerViewFileInclude;
    private List<FileIncludeModel> fileIncludeList;
    private FileInludeAdapter fileInludeAdapter;
    private String dcmnCode;
    private String keyCode;
    private RecyclerView recyclerImage;
    private ImageIncludeAdapter imageIncludeAdapter;
    private Transition transition;
    private List<ImageModel> imageModelList;
    public static final int RC_STORGARE_PERM = 124;

    public ReviewProcessFragment(String dcmnCode, String keyCode) {
        this.dcmnCode = dcmnCode;
        this.keyCode = keyCode;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.review_process_fragment, container, false);

        initText();
        addControls();
        addEvents();
        transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(600);
        transition.addTarget(binding.getRoot());
        return binding.getRoot();
    }

    private void initText() {
        binding.txtTitleImage.setText(SharedPreferencesManager.getSystemLabel(141));
        binding.textView11.setText(SharedPreferencesManager.getSystemLabel(142));
        binding.textView3.setText(SharedPreferencesManager.getSystemLabel(143));
    }

    private void addEvents() {
        binding.constraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.imgDown.getVisibility() == View.VISIBLE) {
                    if (list.size() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        binding.imgDown.setVisibility(View.INVISIBLE);
                        binding.imgUp.setVisibility(View.VISIBLE);
                    } else {
                        binding.txtNon.setVisibility(View.VISIBLE);
                        binding.imgDown.setVisibility(View.INVISIBLE);
                        binding.imgUp.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (list.size() > 0) {
                        recyclerView.setVisibility(View.GONE);
                        binding.imgDown.setVisibility(View.VISIBLE);
                        binding.imgUp.setVisibility(View.INVISIBLE);
                    } else {
                        binding.txtNon.setVisibility(View.GONE);
                        binding.imgDown.setVisibility(View.VISIBLE);
                        binding.imgUp.setVisibility(View.INVISIBLE);
                    }

                }
            }
        });
    }

    private void addControls() {
        imageModelList = new ArrayList<>();

        EasyPermissions.requestPermissions(getActivity(), SharedPreferencesManager.getSystemLabel(193) /*Truy cập bộ nhớ thiết bị*/,
                RC_STORGARE_PERM, READ_EXTERNAL_STORAGE);

        fileIncludeList = new ArrayList<>();
        recyclerView = binding.recycleProgress;
        recyclerViewFileInclude = binding.recycleFile;
        recyclerImage = binding.recycleImage;
        list = new ArrayList<>();
        recyclerView.setVisibility(View.GONE);
        recyclerViewFileInclude.setVisibility(View.GONE);
        recyclerImage.setVisibility(View.GONE);
        reviewProgressAdapter = new ReviewProgressAdapter(list);
        recyclerImage.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerImage.addItemDecoration(new SpacingItemDecoration(3, Tools.dpToPx(getContext(), 2), true));
        fileInludeAdapter = new FileInludeAdapter(fileIncludeList, new ItemFileClick() {
            @Override
            public void onItemClick(FileIncludeModel fileIncludeModel) {
                System.out.println(fileIncludeModel.getLink());
               /* Intent intent = new Intent(getContext(), PDFViewerActivity.class);
                intent.putExtra(Constant.NAME_PUT_PDF_MODEL_FILE,fileIncludeModel);
                startActivity(intent);*/
                openFileInclude(fileIncludeModel);
                if (fileIncludeModel.getFileType().equals("png") || fileIncludeModel.getFileType().equals("jpg")) {

                } else {

                }
            }

            @Override
            public void onRemoveClick(int position) {

            }
        });
        fileInludeAdapter.setRemoveIsVisible(false);
        imageIncludeAdapter = new ImageIncludeAdapter(imageModelList, new ImageClickCallback() {
            @Override
            public void onRemoveClick(int position) {

            }

            @Override
            public void onClickImage(int position) {
                Intent intent = new Intent(getContext(), ImageViewActivity.class);
                intent.putExtra(Constant.NANE_PUT_LIST_BITMAP, (Serializable) imageModelList);
                intent.putExtra(Constant.NANE_PUT_POSITION_ITEM_CLICK, position);
                startActivity(intent);
            }
        });
        imageIncludeAdapter.setEditable(false);

        initComponent();
    }

    private void initComponent() {
        recyclerView.setVisibility(View.VISIBLE);
        recyclerViewFileInclude.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutProgress = new LinearLayoutManager(getContext());
        linearLayoutProgress.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(reviewProgressAdapter);
        recyclerView.setLayoutManager(linearLayoutProgress);

        LinearLayoutManager linearLayoutFile = new LinearLayoutManager(getContext());
        linearLayoutFile.setOrientation(RecyclerView.VERTICAL);
        recyclerViewFileInclude.setAdapter(fileInludeAdapter);
        recyclerViewFileInclude.setLayoutManager(linearLayoutFile);

        recyclerImage.setVisibility(View.VISIBLE);
        recyclerImage.setHasFixedSize(true);
        recyclerImage.setAdapter(imageIncludeAdapter);

        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReviewProcessViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        mViewModel.loadData(dcmnCode, keyCode);
        try {
            mViewModel.getLiveDataReviewProgressList().observe(getViewLifecycleOwner(), new Observer<List<ReviewProcessItem>>() {
                @Override
                public void onChanged(List<ReviewProcessItem> reviewProcessItems) {
                    try {
                        if (reviewProcessItems != null) {
                            if (reviewProcessItems.size() > 0) {
                                if (reviewProcessItems.get(0).getFileList() != null) {
                                    list.clear();
                                    list.addAll(reviewProcessItems.get(0).getDetails());
                                    fileIncludeList.clear();
                                    if (reviewProcessItems.get(0).getFileList() != null) {
                                        for (DocumentFile file : reviewProcessItems.get(0).getFileList()) {
                                            if (file.getFileType() != null) {
                                                if (file.getFileType().equals("png") || file.getFileType().equals("jpg")) {
                                                    ImageModel imageModel = new ImageModel();
                                                    imageModel.setType(1);
                                                    imageModel.setImgUrl(file.getLinkFile());
                                                    imageModelList.add(imageModel);
                                                } else {
                                                    FileIncludeModel fileIncludeModel = new FileIncludeModel();
                                                    fileIncludeModel.setFileName(file.getFileName());
                                                    fileIncludeModel.setLink(file.getLinkFile());
                                                    System.out.println(fileIncludeModel.getLink());
                                                    if (file.getFileType().equals("Pdf")) {
                                                        fileIncludeModel.setIcon(R.drawable.ic_pdf_file);
                                                    }
                                                    if (file.getFileType().equals("xlsx") || file.getFileType().equals("xls")) {
                                                        fileIncludeModel.setIcon(R.drawable.ic_excel);
                                                    }
                                                    if (file.getFileType().equals("docx") || file.getFileType().equals("doc")) {
                                                        fileIncludeModel.setIcon(R.drawable.ic_word_file);
                                                    } else if (file.getFileType().equals("")) {
                                                        fileIncludeModel.setIcon(R.drawable.ic_error_image);
                                                    }
                                                    fileIncludeModel.setFileType(file.getFileType());
                                                    fileIncludeList.add(fileIncludeModel);
                                                }
                                            }
                                        }
                                    }
                                    if (fileIncludeList.size() == 0 && imageModelList.size() == 0) {
                                        binding.cardView3.setVisibility(View.GONE);
                                    } else {
                                        binding.cardView3.setVisibility(View.VISIBLE);
                                    }
                                    fileInludeAdapter.notifyDataSetChanged();
                                    reviewProgressAdapter.notifyDataSetChanged();
                                    imageIncludeAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("Size of image list :" + imageModelList.size());
                    if (fileIncludeList.size() == 0 && imageModelList.size() == 0) {
                        binding.cardView3.setVisibility(View.GONE);
                    } else {
                        binding.cardView3.setVisibility(View.VISIBLE);
                    }
                    if (imageModelList.size() == 0) {
                        recyclerImage.setVisibility(View.GONE);
                        binding.txtTitleImage.setVisibility(View.GONE);
                    } else {
                        recyclerImage.setVisibility(View.VISIBLE);
                        binding.txtTitleImage.setVisibility(View.VISIBLE);
                    }
                    if (fileIncludeList.size() == 0) {
                        recyclerViewFileInclude.setVisibility(View.GONE);
                        binding.textView11.setVisibility(View.GONE);
                    } else {
                        recyclerViewFileInclude.setVisibility(View.VISIBLE);
                        binding.textView11.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterPermissionGranted(RC_STORGARE_PERM)
    private void openFileInclude(FileIncludeModel fileIncludeModel) {
        if (EasyPermissions.hasPermissions(getContext(), READ_EXTERNAL_STORAGE)) {
            if (EasyPermissions.hasPermissions(getContext(), WRITE_EXTERNAL_STORAGE)) {
                Intent intent = new Intent(getContext(), PDFViewerActivity.class);
                intent.putExtra(Constant.NAME_PUT_PDF_MODEL_FILE, fileIncludeModel);
                startActivity(intent);
            } else {
                EasyPermissions.requestPermissions(getActivity(), SharedPreferencesManager.getSystemLabel(193) /*Truy cập bộ nhớ thiết bị*/,
                        RC_STORGARE_PERM, WRITE_EXTERNAL_STORAGE);
            }
        } else {
            EasyPermissions.requestPermissions(getActivity(), SharedPreferencesManager.getSystemLabel(193) /*Truy cập bộ nhớ thiết bị*/,
                    RC_STORGARE_PERM, READ_EXTERNAL_STORAGE);
        }
       /* GetFileHelper.getInstance().doGetFile("http://api-dev.firstems.com" + fileIncludeModel.getLink(),
                SharedPreferencesManager.getInstance().getPrefToken(),
                String.valueOf(System.currentTimeMillis()),
                fileIncludeModel.getFileType()
                ,getContext(), new GetFileHelper.GetPdfFileCallback() {
                    @Override
                    public void onGetFile(File file) {
                        open_File(file);
                           *//* binding.pdfView.fromFile(file)
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
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri apkURI = FileProvider.getUriForFile(getContext(), getActivity().getPackageName(), file);

        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        String mimeType = myMime.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(apkURI.toString()));
        intent.setDataAndType(apkURI, mimeType);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_STORGARE_PERM) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else
                showFailPermission();
        }
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void showFailPermission() {
    }
}