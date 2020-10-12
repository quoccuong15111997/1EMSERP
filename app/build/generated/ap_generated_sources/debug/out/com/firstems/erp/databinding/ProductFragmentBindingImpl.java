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
        sViewsWithIds.put(R.id.parent, 2);
        sViewsWithIds.put(R.id.linearLayoutLenSanXuat, 3);
        sViewsWithIds.put(R.id.txtTitleDateCreate, 4);
        sViewsWithIds.put(R.id.edtMaLenhSanXuat, 5);
        sViewsWithIds.put(R.id.imgBarcode, 6);
        sViewsWithIds.put(R.id.linearLayoutTenLenhSanXuat, 7);
        sViewsWithIds.put(R.id.txtTitleTenLenhSanXuat, 8);
        sViewsWithIds.put(R.id.edtTenLenhSanXuat, 9);
        sViewsWithIds.put(R.id.linearLayoutCongDoan, 10);
        sViewsWithIds.put(R.id.txtTitleCongDoan, 11);
        sViewsWithIds.put(R.id.spinerCongDoan, 12);
        sViewsWithIds.put(R.id.linearLayoutNgayTaoLenhSanXuat, 13);
        sViewsWithIds.put(R.id.txtTitleNgayTaoLenhSanXuat, 14);
        sViewsWithIds.put(R.id.txtNgayTaoLenhSanXuat, 15);
        sViewsWithIds.put(R.id.imageViewCalenda, 16);
        sViewsWithIds.put(R.id.linearLayoutGhiChu, 17);
        sViewsWithIds.put(R.id.txtTitleGhiChu, 18);
        sViewsWithIds.put(R.id.edtGhiChu, 19);
        sViewsWithIds.put(R.id.loadingInList, 20);
        sViewsWithIds.put(R.id.recycleProduct, 21);
        sViewsWithIds.put(R.id.txtNon, 22);
        sViewsWithIds.put(R.id.btnDone, 23);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ProductFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 24, sIncludes, sViewsWithIds));
    }
    private ProductFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[23]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[19]
            , (android.widget.TextView) bindings[5]
            , (android.widget.EditText) bindings[9]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[6]
            , (android.view.View) bindings[1]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.ProgressBar) bindings[20]
            , (android.widget.LinearLayout) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[21]
            , (android.widget.Spinner) bindings[12]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[15]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[8]
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