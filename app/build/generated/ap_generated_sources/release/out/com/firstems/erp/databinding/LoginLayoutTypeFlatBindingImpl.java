package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class LoginLayoutTypeFlatBindingImpl extends LoginLayoutTypeFlatBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearLayout15, 1);
        sViewsWithIds.put(R.id.imageView4, 2);
        sViewsWithIds.put(R.id.tvSubHeader, 3);
        sViewsWithIds.put(R.id.cardView4, 4);
        sViewsWithIds.put(R.id.textInputEditText, 5);
        sViewsWithIds.put(R.id.textInputEditText2, 6);
        sViewsWithIds.put(R.id.chkRemember, 7);
        sViewsWithIds.put(R.id.button2, 8);
        sViewsWithIds.put(R.id.textView56, 9);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LoginLayoutTypeFlatBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private LoginLayoutTypeFlatBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.button.MaterialButton) bindings[8]
            , (androidx.cardview.widget.CardView) bindings[4]
            , (android.widget.CheckBox) bindings[7]
            , (android.widget.ImageView) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.LinearLayout) bindings[1]
            , (com.google.android.material.textfield.TextInputEditText) bindings[5]
            , (com.google.android.material.textfield.TextInputEditText) bindings[6]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[3]
            );
        this.lParentContent.setTag(null);
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