package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class LayoutOptionApprovedBindingImpl extends LayoutOptionApprovedBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.txtTitleApprove, 1);
        sViewsWithIds.put(R.id.linearLayout10, 2);
        sViewsWithIds.put(R.id.radOk, 3);
        sViewsWithIds.put(R.id.radRequestInfomation, 4);
        sViewsWithIds.put(R.id.linearLayout12, 5);
        sViewsWithIds.put(R.id.radConsultation, 6);
        sViewsWithIds.put(R.id.radCancel, 7);
        sViewsWithIds.put(R.id.linearLayout121, 8);
        sViewsWithIds.put(R.id.radCheck, 9);
        sViewsWithIds.put(R.id.radAction, 10);
        sViewsWithIds.put(R.id.layoutSelectEmployee, 11);
        sViewsWithIds.put(R.id.txtPhongBan, 12);
        sViewsWithIds.put(R.id.txtNhanVien, 13);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LayoutOptionApprovedBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private LayoutOptionApprovedBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.CheckBox) bindings[10]
            , (android.widget.CheckBox) bindings[7]
            , (android.widget.CheckBox) bindings[9]
            , (android.widget.CheckBox) bindings[6]
            , (android.widget.CheckBox) bindings[3]
            , (android.widget.CheckBox) bindings[4]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[1]
            );
        this.constraintLayout3.setTag(null);
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