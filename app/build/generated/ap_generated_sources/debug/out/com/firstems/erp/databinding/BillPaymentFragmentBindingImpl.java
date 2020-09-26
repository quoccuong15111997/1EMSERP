package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BillPaymentFragmentBindingImpl extends BillPaymentFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include24, 1);
        sViewsWithIds.put(R.id.linearLayoutDateCreate, 2);
        sViewsWithIds.put(R.id.txtTitleDateCreate, 3);
        sViewsWithIds.put(R.id.txtDateCreate, 4);
        sViewsWithIds.put(R.id.imageView, 5);
        sViewsWithIds.put(R.id.linearLayoutLoaiDeNghiChi, 6);
        sViewsWithIds.put(R.id.txtTitleLoaiDeNghi, 7);
        sViewsWithIds.put(R.id.spinerLoaiDeNghi, 8);
        sViewsWithIds.put(R.id.linearLayoutLoaiChiTieu, 9);
        sViewsWithIds.put(R.id.txtTitleLoaiChiTieu, 10);
        sViewsWithIds.put(R.id.spinerLoaiChiTieu, 11);
        sViewsWithIds.put(R.id.linearLayoutLoaiDoiTuongNhan, 12);
        sViewsWithIds.put(R.id.txtTitleLoaiDoiTuongNhan, 13);
        sViewsWithIds.put(R.id.spinerLoaiDoiTuongNhan, 14);
        sViewsWithIds.put(R.id.linearLayoutTenDoiTuongNhan, 15);
        sViewsWithIds.put(R.id.txtTitleTenDoiTuongNhan, 16);
        sViewsWithIds.put(R.id.txtTenDoiTuongNhan, 17);
        sViewsWithIds.put(R.id.linearLayoutNoidungDeNghi, 18);
        sViewsWithIds.put(R.id.txtTitleInfo, 19);
        sViewsWithIds.put(R.id.edtInfo, 20);
        sViewsWithIds.put(R.id.linearLayoutMoney, 21);
        sViewsWithIds.put(R.id.linearLayout6, 22);
        sViewsWithIds.put(R.id.txtTiteDonViTienTe, 23);
        sViewsWithIds.put(R.id.spinerDonViTienTe, 24);
        sViewsWithIds.put(R.id.linearLayout7, 25);
        sViewsWithIds.put(R.id.txtTitleTiGia, 26);
        sViewsWithIds.put(R.id.edtTiGia, 27);
        sViewsWithIds.put(R.id.linearLayoutSoPhieuTamUng, 28);
        sViewsWithIds.put(R.id.txtTitleSoPhieuTamUng, 29);
        sViewsWithIds.put(R.id.txtSoPhieuTamUng, 30);
        sViewsWithIds.put(R.id.linearLayoutNgay, 31);
        sViewsWithIds.put(R.id.linearLayoutNgayTamUng, 32);
        sViewsWithIds.put(R.id.txtTitleNgayTamUng, 33);
        sViewsWithIds.put(R.id.txtNgayTamUng, 34);
        sViewsWithIds.put(R.id.linearLayoutTienTamUng, 35);
        sViewsWithIds.put(R.id.txtTitleSoTienTamUng, 36);
        sViewsWithIds.put(R.id.edtSoTienTamUng, 37);
        sViewsWithIds.put(R.id.linearLayoutTienDeNghi, 38);
        sViewsWithIds.put(R.id.linearLayoutSoTienDeNghiChi, 39);
        sViewsWithIds.put(R.id.txtTitleSoTienDeNghiChi, 40);
        sViewsWithIds.put(R.id.edtSoTiendeNghiChi, 41);
        sViewsWithIds.put(R.id.linearLayoutSoTienChi, 42);
        sViewsWithIds.put(R.id.txtTitleSoTienChi, 43);
        sViewsWithIds.put(R.id.edtSoTienChi, 44);
        sViewsWithIds.put(R.id.constraintLayout, 45);
        sViewsWithIds.put(R.id.frame_file_include, 46);
        sViewsWithIds.put(R.id.EMSLinearLayout2, 47);
        sViewsWithIds.put(R.id.txtTitleCode, 48);
        sViewsWithIds.put(R.id.txtTitleLyDoChiTien, 49);
        sViewsWithIds.put(R.id.txtSoTien, 50);
        sViewsWithIds.put(R.id.imgAdd, 51);
        sViewsWithIds.put(R.id.constraintLayout14, 52);
        sViewsWithIds.put(R.id.llDelete, 53);
        sViewsWithIds.put(R.id.imgDelete, 54);
        sViewsWithIds.put(R.id.txtDelete, 55);
        sViewsWithIds.put(R.id.constraintLayout8, 56);
        sViewsWithIds.put(R.id.imageView3, 57);
        sViewsWithIds.put(R.id.textView5, 58);
        sViewsWithIds.put(R.id.constraintLayout9, 59);
        sViewsWithIds.put(R.id.txtTrinhKi, 60);
        sViewsWithIds.put(R.id.frame_progress_switch_shift, 61);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BillPaymentFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 62, sIncludes, sViewsWithIds));
    }
    private BillPaymentFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.firstems.erp.helper.widgets.EMSLinearLayout) bindings[47]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[45]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[52]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[56]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[59]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[20]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[44]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[37]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[41]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[27]
            , (android.widget.FrameLayout) bindings[46]
            , (android.widget.FrameLayout) bindings[61]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[57]
            , (android.widget.ImageView) bindings[51]
            , (android.widget.ImageView) bindings[54]
            , (android.view.View) bindings[1]
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[31]
            , (android.widget.LinearLayout) bindings[32]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[28]
            , (android.widget.LinearLayout) bindings[42]
            , (android.widget.LinearLayout) bindings[39]
            , (android.widget.LinearLayout) bindings[15]
            , (android.widget.LinearLayout) bindings[38]
            , (android.widget.LinearLayout) bindings[35]
            , (android.widget.LinearLayout) bindings[53]
            , (android.widget.Spinner) bindings[24]
            , (android.widget.Spinner) bindings[11]
            , (android.widget.Spinner) bindings[8]
            , (android.widget.Spinner) bindings[14]
            , (android.widget.TextView) bindings[58]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[4]
            , (android.widget.TextView) bindings[55]
            , (android.widget.LinearLayout) bindings[34]
            , (android.widget.TextView) bindings[30]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[50]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[23]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[48]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[13]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[49]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[29]
            , (android.widget.TextView) bindings[43]
            , (android.widget.TextView) bindings[40]
            , (android.widget.TextView) bindings[36]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[60]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}