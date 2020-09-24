package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class MoreFragmentBindingImpl extends MoreFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.nested_scroll_view, 1);
        sViewsWithIds.put(R.id.imgBack, 2);
        sViewsWithIds.put(R.id.txtCompName, 3);
        sViewsWithIds.put(R.id.txtLocateName, 4);
        sViewsWithIds.put(R.id.image, 5);
        sViewsWithIds.put(R.id.txtUserName, 6);
        sViewsWithIds.put(R.id.txtPositioName, 7);
        sViewsWithIds.put(R.id.layoutReport, 8);
        sViewsWithIds.put(R.id.imgChart, 9);
        sViewsWithIds.put(R.id.txtTitleReport, 10);
        sViewsWithIds.put(R.id.imageView11, 11);
        sViewsWithIds.put(R.id.layoutNotification, 12);
        sViewsWithIds.put(R.id.imgNotification, 13);
        sViewsWithIds.put(R.id.txtTitleNotification, 14);
        sViewsWithIds.put(R.id.imageView13, 15);
        sViewsWithIds.put(R.id.layoutSetting, 16);
        sViewsWithIds.put(R.id.imgSetting, 17);
        sViewsWithIds.put(R.id.txtTitleSetting, 18);
        sViewsWithIds.put(R.id.imageView15, 19);
        sViewsWithIds.put(R.id.layoutHelp, 20);
        sViewsWithIds.put(R.id.imgHelp, 21);
        sViewsWithIds.put(R.id.txtTilleHelp, 22);
        sViewsWithIds.put(R.id.imageView17, 23);
        sViewsWithIds.put(R.id.layoutChangePassword, 24);
        sViewsWithIds.put(R.id.imageView18, 25);
        sViewsWithIds.put(R.id.textView25, 26);
        sViewsWithIds.put(R.id.imageView19, 27);
        sViewsWithIds.put(R.id.layoutLogout, 28);
        sViewsWithIds.put(R.id.imageView20, 29);
        sViewsWithIds.put(R.id.textView26, 30);
        sViewsWithIds.put(R.id.imageView21, 31);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public MoreFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 32, sIncludes, sViewsWithIds));
    }
    private MoreFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.mikhaellopez.circularimageview.CircularImageView) bindings[5]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[19]
            , (android.widget.ImageView) bindings[23]
            , (android.widget.ImageView) bindings[25]
            , (android.widget.ImageView) bindings[27]
            , (android.widget.ImageView) bindings[29]
            , (android.widget.ImageView) bindings[31]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.ImageView) bindings[21]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[17]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[24]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[20]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[28]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[12]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[8]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[16]
            , (androidx.core.widget.NestedScrollView) bindings[1]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[18]
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