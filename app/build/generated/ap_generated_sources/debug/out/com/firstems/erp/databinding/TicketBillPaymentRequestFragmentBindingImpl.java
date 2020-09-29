package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class TicketBillPaymentRequestFragmentBindingImpl extends TicketBillPaymentRequestFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.header, 1);
        sViewsWithIds.put(R.id.linearLayoutNoidungDeNghi, 2);
        sViewsWithIds.put(R.id.txtTitleInfo, 3);
        sViewsWithIds.put(R.id.edtInfo, 4);
        sViewsWithIds.put(R.id.linearLayoutSoTien, 5);
        sViewsWithIds.put(R.id.txtTitleSoTien, 6);
        sViewsWithIds.put(R.id.edtSoTien, 7);
        sViewsWithIds.put(R.id.linearLayoutSoHoaDon, 8);
        sViewsWithIds.put(R.id.txtTitleSoHoaDon, 9);
        sViewsWithIds.put(R.id.edtSoHoaDon, 10);
        sViewsWithIds.put(R.id.linearLayoutDate, 11);
        sViewsWithIds.put(R.id.txtTitleDate, 12);
        sViewsWithIds.put(R.id.layoutDateDeNghiChi, 13);
        sViewsWithIds.put(R.id.edtDate, 14);
        sViewsWithIds.put(R.id.imageView, 15);
        sViewsWithIds.put(R.id.materialRippleLayout, 16);
        sViewsWithIds.put(R.id.btnDone, 17);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public TicketBillPaymentRequestFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }
    private TicketBillPaymentRequestFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[17]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[14]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[4]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[10]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[7]
            , (android.view.View) bindings[1]
            , (android.widget.ImageView) bindings[15]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[5]
            , (com.balysv.materialripple.MaterialRippleLayout) bindings[16]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[6]
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