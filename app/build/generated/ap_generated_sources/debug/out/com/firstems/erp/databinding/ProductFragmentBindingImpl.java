package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ProductFragmentBindingImpl extends ProductFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include6, 1);
        sViewsWithIds.put(R.id.linearLayoutLenSanXuat, 2);
        sViewsWithIds.put(R.id.txtTitleDateCreate, 3);
        sViewsWithIds.put(R.id.edtMaLenhSanXuat, 4);
        sViewsWithIds.put(R.id.imgBarcode, 5);
        sViewsWithIds.put(R.id.linearLayoutTenLenhSanXuat, 6);
        sViewsWithIds.put(R.id.txtTitleTenLenhSanXuat, 7);
        sViewsWithIds.put(R.id.edtTenLenhSanXuat, 8);
        sViewsWithIds.put(R.id.linearLayoutCongDoan, 9);
        sViewsWithIds.put(R.id.txtTitleCongDoan, 10);
        sViewsWithIds.put(R.id.spinerCongDoan, 11);
        sViewsWithIds.put(R.id.linearLayoutNgayTaoLenhSanXuat, 12);
        sViewsWithIds.put(R.id.txtTitleNgayTaoLenhSanXuat, 13);
        sViewsWithIds.put(R.id.txtNgayTaoLenhSanXuat, 14);
        sViewsWithIds.put(R.id.imageViewCalenda, 15);
        sViewsWithIds.put(R.id.linearLayoutGhiChu, 16);
        sViewsWithIds.put(R.id.txtTitleGhiChu, 17);
        sViewsWithIds.put(R.id.edtGhiChu, 18);
        sViewsWithIds.put(R.id.recycleProduct, 19);
        sViewsWithIds.put(R.id.btnDone, 20);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ProductFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private ProductFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[20]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[18]
            , (android.widget.TextView) bindings[4]
            , (android.widget.EditText) bindings[8]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[5]
            , (android.view.View) bindings[1]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[6]
            , (androidx.recyclerview.widget.RecyclerView) bindings[19]
            , (android.widget.Spinner) bindings[11]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[14]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[7]
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