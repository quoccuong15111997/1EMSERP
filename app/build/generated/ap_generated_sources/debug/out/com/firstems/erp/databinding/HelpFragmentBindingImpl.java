package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HelpFragmentBindingImpl extends HelpFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include18, 1);
        sViewsWithIds.put(R.id.txtTitleSystemSetting, 2);
        sViewsWithIds.put(R.id.cardSystemSetting, 3);
        sViewsWithIds.put(R.id.imageView301, 4);
        sViewsWithIds.put(R.id.textView471, 5);
        sViewsWithIds.put(R.id.imageView30, 6);
        sViewsWithIds.put(R.id.textView47, 7);
        sViewsWithIds.put(R.id.textView48, 8);
        sViewsWithIds.put(R.id.txtTitleHelp, 9);
        sViewsWithIds.put(R.id.imageView31, 10);
        sViewsWithIds.put(R.id.textView49, 11);
        sViewsWithIds.put(R.id.view10, 12);
        sViewsWithIds.put(R.id.imageView33, 13);
        sViewsWithIds.put(R.id.textView51, 14);
        sViewsWithIds.put(R.id.view11, 15);
        sViewsWithIds.put(R.id.imageView34, 16);
        sViewsWithIds.put(R.id.textView52, 17);
        sViewsWithIds.put(R.id.view12, 18);
        sViewsWithIds.put(R.id.imageView35, 19);
        sViewsWithIds.put(R.id.textView53, 20);
        sViewsWithIds.put(R.id.txtCoppyright, 21);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HelpFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }
    private HelpFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[3]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[19]
            , (android.view.View) bindings[1]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[2]
            , (android.view.View) bindings[12]
            , (android.view.View) bindings[15]
            , (android.view.View) bindings[18]
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