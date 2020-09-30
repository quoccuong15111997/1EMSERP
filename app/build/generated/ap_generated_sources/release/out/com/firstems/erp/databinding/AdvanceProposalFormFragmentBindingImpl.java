package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AdvanceProposalFormFragmentBindingImpl extends AdvanceProposalFormFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include7, 10);
        sViewsWithIds.put(R.id.linearLayoutDateCreate, 11);
        sViewsWithIds.put(R.id.txtTitleNgayTamUng, 12);
        sViewsWithIds.put(R.id.contraintDate, 13);
        sViewsWithIds.put(R.id.txtDateCreate, 14);
        sViewsWithIds.put(R.id.imageView, 15);
        sViewsWithIds.put(R.id.linearLayoutLoaiDeNghiChi, 16);
        sViewsWithIds.put(R.id.txtTitleLoaiDeNghi, 17);
        sViewsWithIds.put(R.id.linearLayoutLoaiDoiTuongNhan, 18);
        sViewsWithIds.put(R.id.txtTitleLoaiDoiTuongNhan, 19);
        sViewsWithIds.put(R.id.linearLayoutTenDoiTuongNhan, 20);
        sViewsWithIds.put(R.id.txtTitleTenDoiTuongNhan, 21);
        sViewsWithIds.put(R.id.linearLayoutNoidungDeNghi, 22);
        sViewsWithIds.put(R.id.txtTitleInfo, 23);
        sViewsWithIds.put(R.id.linearLayoutMoney, 24);
        sViewsWithIds.put(R.id.linearLayout6, 25);
        sViewsWithIds.put(R.id.txtTiteDonViTienTe, 26);
        sViewsWithIds.put(R.id.linearLayout7, 27);
        sViewsWithIds.put(R.id.txtTitleTiGia, 28);
        sViewsWithIds.put(R.id.linearLayoutNgay, 29);
        sViewsWithIds.put(R.id.linearLayoutTienTamUng, 30);
        sViewsWithIds.put(R.id.txtTitleSoTienTamUng, 31);
        sViewsWithIds.put(R.id.linearLayoutTienDuocDuyet, 32);
        sViewsWithIds.put(R.id.txtTitleSoTienDuocDuyet, 33);
        sViewsWithIds.put(R.id.edtSoTienDuocDuyet, 34);
        sViewsWithIds.put(R.id.linearLayoutMaDuAn, 35);
        sViewsWithIds.put(R.id.txtTitleMaDuAn, 36);
        sViewsWithIds.put(R.id.constraintLayout, 37);
        sViewsWithIds.put(R.id.frame_file_include, 38);
        sViewsWithIds.put(R.id.linearLayoutNgayThanhToan, 39);
        sViewsWithIds.put(R.id.txtTitleNgayThanhToan, 40);
        sViewsWithIds.put(R.id.txtNgayThanhToan, 41);
        sViewsWithIds.put(R.id.imageView1, 42);
        sViewsWithIds.put(R.id.constraintLayout14, 43);
        sViewsWithIds.put(R.id.llDelete, 44);
        sViewsWithIds.put(R.id.imgDelete, 45);
        sViewsWithIds.put(R.id.txtDelete, 46);
        sViewsWithIds.put(R.id.constraintLayout8, 47);
        sViewsWithIds.put(R.id.imageView3, 48);
        sViewsWithIds.put(R.id.textView5, 49);
        sViewsWithIds.put(R.id.constraintLayout9, 50);
        sViewsWithIds.put(R.id.txtTrinhKi, 51);
        sViewsWithIds.put(R.id.frame_progress_switch_shift, 52);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public AdvanceProposalFormFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 53, sIncludes, sViewsWithIds));
    }
    private AdvanceProposalFormFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[37]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[43]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[47]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[50]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[13]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[4]
            , (android.widget.EditText) bindings[34]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[7]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[6]
            , (android.widget.FrameLayout) bindings[38]
            , (android.widget.FrameLayout) bindings[52]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[42]
            , (android.widget.ImageView) bindings[48]
            , (android.widget.ImageView) bindings[45]
            , (android.view.View) bindings[10]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[27]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[35]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.LinearLayout) bindings[29]
            , (android.widget.LinearLayout) bindings[39]
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[32]
            , (android.widget.LinearLayout) bindings[30]
            , (android.widget.LinearLayout) bindings[44]
            , (android.widget.Spinner) bindings[5]
            , (android.widget.Spinner) bindings[1]
            , (android.widget.Spinner) bindings[2]
            , (android.widget.Spinner) bindings[8]
            , (android.widget.TextView) bindings[49]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[14]
            , (android.widget.TextView) bindings[46]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[41]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[36]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[40]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[51]
            );
        this.edtInfo.setTag(null);
        this.edtSoTienTamUng.setTag(null);
        this.edtTiGia.setTag(null);
        this.layoutDateDeNghiChi.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.spinerDonViTienTe.setTag(null);
        this.spinerLoaiDeNghiTamUng.setTag(null);
        this.spinerLoaiDoiTuongNhan.setTag(null);
        this.spinerMaDuAn.setTag(null);
        this.txtTenDoiTuongNhan.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.isEditable == variableId) {
            setIsEditable((java.lang.Boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setIsEditable(@Nullable java.lang.Boolean IsEditable) {
        this.mIsEditable = IsEditable;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.isEditable);
        super.requestRebind();
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
        boolean androidxDatabindingViewDataBindingSafeUnboxIsEditable = false;
        java.lang.Boolean isEditable = mIsEditable;

        if ((dirtyFlags & 0x3L) != 0) {



                // read androidx.databinding.ViewDataBinding.safeUnbox(isEditable)
                androidxDatabindingViewDataBindingSafeUnboxIsEditable = androidx.databinding.ViewDataBinding.safeUnbox(isEditable);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.edtInfo.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.edtSoTienTamUng.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.edtTiGia.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.layoutDateDeNghiChi.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.spinerDonViTienTe.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.spinerLoaiDeNghiTamUng.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.spinerLoaiDoiTuongNhan.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.spinerMaDuAn.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.txtTenDoiTuongNhan.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): isEditable
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}