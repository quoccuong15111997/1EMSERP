package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ServiceContactsFragmentBindingImpl extends ServiceContactsFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include4, 8);
        sViewsWithIds.put(R.id.lParentContent, 9);
        sViewsWithIds.put(R.id.linearLayoutDateCreate, 10);
        sViewsWithIds.put(R.id.txtTitleDateCreate, 11);
        sViewsWithIds.put(R.id.txtDateCreate, 12);
        sViewsWithIds.put(R.id.imageView, 13);
        sViewsWithIds.put(R.id.linearLayoutListReceiver, 14);
        sViewsWithIds.put(R.id.txtTitleReceiver, 15);
        sViewsWithIds.put(R.id.linearLayoutListRefer, 16);
        sViewsWithIds.put(R.id.txtTitleListRefer, 17);
        sViewsWithIds.put(R.id.linearLayoutRelatedField, 18);
        sViewsWithIds.put(R.id.txtTitleRelatedField, 19);
        sViewsWithIds.put(R.id.linearLayoutPurposeContact, 20);
        sViewsWithIds.put(R.id.txtTitlePurposeContact, 21);
        sViewsWithIds.put(R.id.linearLayoutContactContent, 22);
        sViewsWithIds.put(R.id.txtTitleContactContent, 23);
        sViewsWithIds.put(R.id.constraintLayout, 24);
        sViewsWithIds.put(R.id.frame_file_include, 25);
        sViewsWithIds.put(R.id.linearLayoutDateComplete, 26);
        sViewsWithIds.put(R.id.txtTitleDateComplete, 27);
        sViewsWithIds.put(R.id.txtDateComplete, 28);
        sViewsWithIds.put(R.id.imageViewCalenda, 29);
        sViewsWithIds.put(R.id.linearLayoutLocate, 30);
        sViewsWithIds.put(R.id.txtTitleLocate, 31);
        sViewsWithIds.put(R.id.constraintLayout14, 32);
        sViewsWithIds.put(R.id.llDelete, 33);
        sViewsWithIds.put(R.id.imgDelete, 34);
        sViewsWithIds.put(R.id.txtDelete, 35);
        sViewsWithIds.put(R.id.constraintLayout8, 36);
        sViewsWithIds.put(R.id.imageView3, 37);
        sViewsWithIds.put(R.id.textView5, 38);
        sViewsWithIds.put(R.id.constraintLayout9, 39);
        sViewsWithIds.put(R.id.txtTrinhKi, 40);
        sViewsWithIds.put(R.id.frame_reviews_progress, 41);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ServiceContactsFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 42, sIncludes, sViewsWithIds));
    }
    private ServiceContactsFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[24]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[32]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[36]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[39]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[5]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[7]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[4]
            , (android.widget.FrameLayout) bindings[25]
            , (android.widget.FrameLayout) bindings[41]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[37]
            , (android.widget.ImageView) bindings[29]
            , (android.widget.ImageView) bindings[34]
            , (android.view.View) bindings[8]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[9]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[26]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[30]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[33]
            , (android.widget.Spinner) bindings[3]
            , (android.widget.TextView) bindings[38]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[28]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[12]
            , (android.widget.TextView) bindings[35]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[40]
            );
        this.edtContactContent.setTag(null);
        this.edtLocate.setTag(null);
        this.edtPurposeContact.setTag(null);
        this.layoutTimeDone.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.spinerRelatedField.setTag(null);
        this.txtListRefer.setTag(null);
        this.txtReceiver.setTag(null);
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
        if (BR.isEdit == variableId) {
            setIsEdit((java.lang.Boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setIsEdit(@Nullable java.lang.Boolean IsEdit) {
        this.mIsEdit = IsEdit;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.isEdit);
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
        boolean androidxDatabindingViewDataBindingSafeUnboxIsEdit = false;
        java.lang.Boolean isEdit = mIsEdit;

        if ((dirtyFlags & 0x3L) != 0) {



                // read androidx.databinding.ViewDataBinding.safeUnbox(isEdit)
                androidxDatabindingViewDataBindingSafeUnboxIsEdit = androidx.databinding.ViewDataBinding.safeUnbox(isEdit);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.edtContactContent.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEdit);
            this.edtLocate.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEdit);
            this.edtPurposeContact.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEdit);
            this.layoutTimeDone.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEdit);
            this.spinerRelatedField.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEdit);
            this.txtListRefer.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEdit);
            this.txtReceiver.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEdit);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): isEdit
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}