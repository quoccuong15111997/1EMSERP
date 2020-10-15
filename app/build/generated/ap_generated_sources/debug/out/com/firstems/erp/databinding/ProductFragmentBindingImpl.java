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
        sViewsWithIds.put(R.id.linearLayoutNgayTaoLenhSanXuat, 7);
        sViewsWithIds.put(R.id.txtTitleNgayTaoLenhSanXuat, 8);
        sViewsWithIds.put(R.id.txtNgayTaoLenhSanXuat, 9);
        sViewsWithIds.put(R.id.imageViewCalenda, 10);
        sViewsWithIds.put(R.id.linearLayoutTenLenhSanXuat, 11);
        sViewsWithIds.put(R.id.txtTitleTenLenhSanXuat, 12);
        sViewsWithIds.put(R.id.edtTenLenhSanXuat, 13);
        sViewsWithIds.put(R.id.imgBarcode1, 14);
        sViewsWithIds.put(R.id.linearLayoutCongDoan, 15);
        sViewsWithIds.put(R.id.txtTitleCongDoan, 16);
        sViewsWithIds.put(R.id.spinerCongDoan, 17);
        sViewsWithIds.put(R.id.linearLayoutGhiChu, 18);
        sViewsWithIds.put(R.id.txtTitleGhiChu, 19);
        sViewsWithIds.put(R.id.edtGhiChu, 20);
        sViewsWithIds.put(R.id.textView57, 21);
        sViewsWithIds.put(R.id.txtNumberProduct, 22);
        sViewsWithIds.put(R.id.imgViewType, 23);
        sViewsWithIds.put(R.id.llHeaderTable, 24);
        sViewsWithIds.put(R.id.txtProductName, 25);
        sViewsWithIds.put(R.id.txtProductQuatity, 26);
        sViewsWithIds.put(R.id.txtProductQuatityPass, 27);
        sViewsWithIds.put(R.id.txtProductQuatityFail, 28);
        sViewsWithIds.put(R.id.fabEdit, 29);
        sViewsWithIds.put(R.id.fabDone, 30);
        sViewsWithIds.put(R.id.loadingInList, 31);
        sViewsWithIds.put(R.id.recycleProduct, 32);
        sViewsWithIds.put(R.id.txtNon, 33);
        sViewsWithIds.put(R.id.btnDone, 34);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ProductFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 35, sIncludes, sViewsWithIds));
    }
    private ProductFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[34]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[20]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[13]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[30]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[29]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[23]
            , (android.view.View) bindings[1]
            , (android.widget.LinearLayout) bindings[15]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.ProgressBar) bindings[31]
            , (android.widget.LinearLayout) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[32]
            , (android.widget.Spinner) bindings[17]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[12]
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