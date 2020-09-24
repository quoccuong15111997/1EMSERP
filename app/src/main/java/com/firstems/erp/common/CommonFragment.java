package com.firstems.erp.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.firstems.erp.R;
import com.firstems.erp.adapter.DialogSingelChoiseAdapter;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC;
import com.firstems.erp.callback.ConfirmCallback;
import com.firstems.erp.callback.DatePickerCallback;
import com.firstems.erp.callback.SingelChoiseDialogCallback;
import com.firstems.erp.callback.TypeSelecDate;
import com.firstems.erp.helper.accessrole.AccessRole;
import com.firstems.erp.helper.accessrole.AccessRoleProvider;
import com.firstems.erp.helper.datetime.DateTimeHelper;
import com.firstems.erp.helper.toast.ToastHelper;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

import static java.util.Calendar.DAY_OF_MONTH;

/**
 * Created by Nguyen Quoc Cuong on 7/21/2020.
 */
public abstract class CommonFragment extends Fragment {
    protected Typeface tfRegular;
    protected Typeface tfLight;
    protected AlertDialog alertDialog;
    protected ProgressDialog progressdialog;
    protected ProgressDialog progressdialogLoad;
    protected SimpleDateFormat simpleDateFormatDisplay;
    protected SimpleDateFormat simpleDateFormatSystem;
    protected boolean networkCheck = false;
    protected Dialog loadingDialog;
    protected Dialog loadingNonMessDialog;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ToastHelper.init(getActivity());
        
        tfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        simpleDateFormatDisplay= new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormatSystem = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    protected void initProgressDialog(String title, String mess){
        progressdialog = new ProgressDialog(getContext());
        progressdialog.setTitle(title);
        progressdialog.setMessage(mess);
        progressdialog.setCancelable(false);
    }
    protected void initProgressDialogLoad(String title, String mess){
        progressdialogLoad = new ProgressDialog(getContext());
        progressdialogLoad.setTitle(title);
        progressdialogLoad.setMessage(mess);
        progressdialogLoad.setCancelable(false);
    }
    
    protected void showDialogDatePicker(DatePickerCallback datePickerCallback){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.layout_calenda, null);
        dialogBuilder.setView(dialogView);
        Calendar calendar= Calendar.getInstance();
        CalendarView calendarView = dialogView.findViewById(R.id.calendarView);
        TypeSelecDate typeSelecDate=new TypeSelecDate() {
            @Override
            public void typeSelectBeginDate(Calendar calendarEnd) {
                //calendarView.setMaxDate(calendarEnd.getTimeInMillis());
            }
            
            @Override
            public void typeSelectEndDate(Calendar calendarBegin) {
                //calendarView.setMinDate(calendarBegin.getTimeInMillis());
            }
        };
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.clear();
                calendar.set(DAY_OF_MONTH,dayOfMonth);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.YEAR,year);
                System.out.println("========"+calendar.get(Calendar.DAY_OF_WEEK));
            }
        });
        Button btnOk = dialogView.findViewById(R.id.btnOK);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);
                datePickerCallback.onDatePick(calendar);
                alertDialog.dismiss();
            }
        });
        datePickerCallback.typeSelect(typeSelecDate);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
    protected String converDate(Calendar calendar){
        String dateReturn = "";
        String day = DateTimeHelper.pad(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        //String year = String.valueOf(calendar.get(Calendar.YEAR));
        dateReturn = day+" tháng "+month;
        return dateReturn;
    }
    private String modDate(Calendar calendar){
        String[] days = new String[] {"Chủ nhật" , "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy" };
        System.out.println("tttttttttttttttttt"+String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)-1));
        System.out.println("tttttttttttttttttt"+days[calendar.get(Calendar.DAY_OF_WEEK)-1]);
        
        return days[calendar.get(Calendar.DAY_OF_WEEK)-1];
    }
    protected void showToastSuccess(String mess){
        ToastHelper.getInstance().toastIconSuccess(mess, Toast.LENGTH_SHORT);
    }
    protected void showToastError(String mess){
        ToastHelper.getInstance().toastIconError(mess, Toast.LENGTH_SHORT);
    }
    protected void showToastInfo(String mess){
        ToastHelper.getInstance().toastIconInfo(mess, Toast.LENGTH_SHORT);
    }
    
    protected void showErrorDialog(String title, String mess) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_warning);
        dialog.setCancelable(true);
        
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        TextView txtTitle = dialog.findViewById(R.id.title);
        TextView txtContent = dialog.findViewById(R.id.content);
        
        txtTitle.setText(title);
        txtContent.setText(mess);
        
        
        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    protected void showConfirmMessage(String title, String message,String titleButtonAccept, String titleButtonCancel, ConfirmCallback confirmCallback) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_confirm, null);
        dialogBuilder.setView(dialogView);
        TextView lb_custom_view_alert_dialog = (TextView) dialogView.findViewById(R.id.lb_custom_view_alert_dialog_message);
        TextView c = (TextView) dialogView.findViewById(R.id.lb_custom_view_alert_dialog_title);
        Button dialogCancelButton = dialogView.findViewById(R.id.btn_custom_view_alert_dialog_close);
        dialogCancelButton.setText(titleButtonCancel);
        Button dialogOkButton=dialogView.findViewById(R.id.btn_custom_view_alert_dialog_ok);
        dialogOkButton.setText(titleButtonAccept);
        
        c.setText(title.toUpperCase());
        lb_custom_view_alert_dialog.setText(message);
        dialogCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog != null) {
                    alertDialog.dismiss();
                    confirmCallback.onCancel();
                }
            }
        });
        dialogOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                confirmCallback.onAccept();
            }
        });
        
        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
    
    protected void showSingelChoiseDialod(List<TimekeepingTypeDC> listData, SingelChoiseDialogCallback singelChoiseDialogCallback) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_singel_choise);
        dialog.setCancelable(true);
        
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        RecyclerView recyclerView = dialog.findViewById(R.id.recycleview);
        DialogSingelChoiseAdapter adapter= new DialogSingelChoiseAdapter(listData, new SingelChoiseDialogCallback() {
            @Override
            public void itemSelected(int postion) {
                singelChoiseDialogCallback.itemSelected(postion);
                dialog.dismiss();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(dialog.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        
        dialog.show();
    }
    protected AccessRole checkAccessRole(int sttEsign, int accessRight, ConstraintLayout layoutSave, ConstraintLayout layoutPut, LinearLayout layoutDelete, ImageView imgAdd){
        if (sttEsign>0){
            layoutSave.setVisibility(View.GONE);
            layoutPut.setVisibility(View.GONE);
            layoutDelete.setVisibility(View.GONE);
            if (imgAdd!=null){
                imgAdd.setVisibility(View.INVISIBLE);
            }
            AccessRole accessRole = AccessRoleProvider
                    .getInstance()
                    .getAccessRole(0);
            return accessRole;
        }
        else {
            AccessRole accessRole = AccessRoleProvider
                    .getInstance()
                    .getAccessRole(accessRight);
            
            layoutSave.setVisibility(View.GONE);
            
            if (accessRole.isDelete()){
                layoutDelete.setVisibility(View.VISIBLE);
            }
            else {
                layoutDelete.setVisibility(View.GONE);
            }
            
            if (accessRole.isAdd() || accessRole.isEdit()){
                layoutSave.setVisibility(View.VISIBLE);
            }
            else {
                layoutSave.setVisibility(View.GONE);
            }
            
            if (accessRole.isEdit()){
                layoutPut.setVisibility(View.VISIBLE);
            }
            else {
                layoutPut.setVisibility(View.GONE);
            }
            
            if (accessRole.isAdd()){
                if (imgAdd!=null){
                    imgAdd.setVisibility(View.VISIBLE);
                }
            }
            else {
                if (imgAdd!=null){
                    imgAdd.setVisibility(View.INVISIBLE);
                }
            }
            
            return accessRole;
        }
    }
    protected void showSuccessDialog(String title, String mess) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_info);
        dialog.setCancelable(true);
        
        TextView txtTitle = dialog.findViewById(R.id.title);
        TextView txtContent = dialog.findViewById(R.id.content);
        
        txtTitle.setText(title);
        txtContent.setText(mess);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        
        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getActivity().finish();
            }
        });
        
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if (!isNetworkAvailable()){
            showCustomDialog();
        }
    }
    private void showCustomDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.no_internet_waring);
        dialog.setCancelable(true);
        
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        
        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()){
                    dialog.dismiss();
                }
            }
        });
        
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    protected void showLoadingDialog(String mess){
        loadingDialog= new Dialog(getContext());
        loadingDialog.setContentView(R.layout.item_loading_dialog);
        loadingDialog.setCancelable(true);
        loadingDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(loadingDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        TextView textView =loadingDialog.findViewById(R.id.tvLoading);
        textView.setText(mess);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setAttributes(lp);
        loadingDialog.show();
    }
    protected void showLoadingNonMessDialog(){
        loadingNonMessDialog= new Dialog(getContext());
        loadingNonMessDialog.setContentView(R.layout.item_loading_dialog_non__message);
        loadingNonMessDialog.setCancelable(true);
        loadingNonMessDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(loadingNonMessDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        loadingNonMessDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        
        loadingNonMessDialog.setCancelable(false);
        loadingNonMessDialog.getWindow().setAttributes(lp);
        loadingNonMessDialog.show();
    }
    protected void setAnimViewVisible(@NotNull final View lParentContent, @NotNull final View vTarget, long duration) {
        Intrinsics.checkParameterIsNotNull(lParentContent, "lParentContent");
        Intrinsics.checkParameterIsNotNull(vTarget, "vTarget");
        
        try {
            (new Handler()).postDelayed((Runnable)(new Runnable() {
                public final void run() {
                    View var10000 = lParentContent;
                    if (var10000 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
                    } else {
                        TransitionManager.beginDelayedTransition((ViewGroup)var10000);
                        vTarget.setVisibility(View.VISIBLE);
                    }
                }
            }), duration);
        } catch (Exception var6) {
            var6.printStackTrace();
            vTarget.setVisibility(View.VISIBLE);
        }
        
    }
    
    protected void setAnimViewGone(@NotNull final View lParentContent, @NotNull final View vTarget, long duration) {
        Intrinsics.checkParameterIsNotNull(lParentContent, "lParentContent");
        Intrinsics.checkParameterIsNotNull(vTarget, "vTarget");
        
        try {
            (new Handler()).postDelayed((Runnable)(new Runnable() {
                public final void run() {
                    View var10000 = lParentContent;
                    if (var10000 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
                    } else {
                        TransitionManager.beginDelayedTransition((ViewGroup)var10000);
                        vTarget.setVisibility(View.GONE);
                    }
                }
            }), duration);
        } catch (Exception var6) {
            var6.printStackTrace();
            vTarget.setVisibility(View.GONE);
        }
        
    }
    
    protected void setOveridePendingTransisi(@NotNull Activity context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        
        try {
            context.overridePendingTransition(17432576, 17432577);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        
    }
}
