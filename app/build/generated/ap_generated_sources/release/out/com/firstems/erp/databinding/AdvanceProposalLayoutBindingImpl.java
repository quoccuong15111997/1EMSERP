package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AdvanceProposalLayoutBindingImpl extends AdvanceProposalLayoutBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include7, 10);
        sViewsWithIds.put(R.id.lParentContent, 11);
        sViewsWithIds.put(R.id.linearLayoutDateCreate, 12);
        sViewsWithIds.put(R.id.txtTitleNgayTamUng, 13);
        sViewsWithIds.put(R.id.contraintDate, 14);
        sViewsWithIds.put(R.id.txtDateCreate, 15);
        sViewsWithIds.put(R.id.imageView, 16);
        sViewsWithIds.put(R.id.linearLayoutLoaiDeNghiChi, 17);
        sViewsWithIds.put(R.id.txtTitleLoaiDeNghi, 18);
        sViewsWithIds.put(R.id.linearLayoutLoaiDoiTuongNhan, 19);
        sViewsWithIds.put(R.id.txtTitleLoaiDoiTuongNhan, 20);
        sViewsWithIds.put(R.id.linearLayoutTenDoiTuongNhan, 21);
        sViewsWithIds.put(R.id.txtTitleTenDoiTuongNhan, 22);
        sViewsWithIds.put(R.id.linearLayoutNoidungDeNghi, 23);
        sViewsWithIds.put(R.id.txtTitleInfo, 24);
        sViewsWithIds.put(R.id.linearLayoutMoney, 25);
        sViewsWithIds.put(R.id.linearLayout6, 26);
        sViewsWithIds.put(R.id.txtTiteDonViTienTe, 27);
        sViewsWithIds.put(R.id.linearLayout7, 28);
        sViewsWithIds.put(R.id.txtTitleTiGia, 29);
        sViewsWithIds.put(R.id.linearLayoutNgay, 30);
        sViewsWithIds.put(R.id.linearLayoutTienTamUng, 31);
        sViewsWithIds.put(R.id.txtTitleSoTienTamUng, 32);
        sViewsWithIds.put(R.id.linearLayoutTienDuocDuyet, 33);
        sViewsWithIds.put(R.id.txtTitleSoTienDuocDuyet, 34);
        sViewsWithIds.put(R.id.edtSoTienDuocDuyet, 35);
        sViewsWithIds.put(R.id.linearLayoutMaDuAn, 36);
        sViewsWithIds.put(R.id.txtTitleMaDuAn, 37);
        sViewsWithIds.put(R.id.constraintLayout, 38);
        sViewsWithIds.put(R.id.frame_file_include, 39);
        sViewsWithIds.put(R.id.linearLayoutNgayThanhToan, 40);
        sViewsWithIds.put(R.id.txtTitleNgayThanhToan, 41);
        sViewsWithIds.put(R.id.txtNgayThanhToan, 42);
        sViewsWithIds.put(R.id.imageView1, 43);
        sViewsWithIds.put(R.id.constraintLayout14, 44);
        sViewsWithIds.put(R.id.llDelete, 45);
        sViewsWithIds.put(R.id.imgDelete, 46);
        sViewsWithIds.put(R.id.txtDelete, 47);
        sViewsWithIds.put(R.id.constraintLayout8, 48);
        sViewsWithIds.put(R.id.imageView3, 49);
        sViewsWithIds.put(R.id.textView5, 50);
        sViewsWithIds.put(R.id.constraintLayout9, 51);
        sViewsWithIds.put(R.id.txtTrinhKi, 52);
        sViewsWithIds.put(R.id.frame_progress_switch_shift, 53);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public AdvanceProposalLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 54, sIncludes, sViewsWithIds));
    }
    private AdvanceProposalLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[38]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[44]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[48]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[51]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[14]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[4]
            , (android.widget.EditText) bindings[35]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[7]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[6]
            , (android.widget.FrameLayout) bindings[39]
            , (android.widget.FrameLayout) bindings[53]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[43]
            , (android.widget.ImageView) bindings[49]
            , (android.widget.ImageView) bindings[46]
            , (android.view.View) bindings[10]
            , (android.widget.LinearLayout) bindings[11]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[26]
            , (android.widget.LinearLayout) bindings[28]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[36]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[30]
            , (android.widget.LinearLayout) bindings[40]
            , (android.widget.LinearLayout) bindings[23]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[33]
            , (android.widget.LinearLayout) bindings[31]
            , (android.widget.LinearLayout) bindings[45]
            , (android.widget.Spinner) bindings[5]
            , (android.widget.Spinner) bindings[1]
            , (android.widget.Spinner) bindings[2]
            , (android.widget.Spinner) bindings[8]
            , (android.widget.TextView) bindings[50]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[15]
            , (android.widget.TextView) bindings[47]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[42]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[37]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[41]
            , (android.widget.TextView) bindings[34]
            , (android.widget.TextView) bindings[32]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[29]
            , (android.widget.TextView) bindings[52]
            );
        this.edtInfo.setTag(null);
        this.edtSoTienTamUng.setTag(null);
        this.edtTiGia.setTag(null);
        this.layoutDateDeNghiChi.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
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