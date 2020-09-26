package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DocumentFragmentBindingImpl extends DocumentFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include, 1);
        sViewsWithIds.put(R.id.lParentContent, 2);
        sViewsWithIds.put(R.id.linearLayout6, 3);
        sViewsWithIds.put(R.id.txtTitleDocumentCode, 4);
        sViewsWithIds.put(R.id.edtDocumentCode, 5);
        sViewsWithIds.put(R.id.linearLayout7, 6);
        sViewsWithIds.put(R.id.txtTitleDocumentName, 7);
        sViewsWithIds.put(R.id.edtDocumentName, 8);
        sViewsWithIds.put(R.id.linearLayout8, 9);
        sViewsWithIds.put(R.id.txtTitleInfo, 10);
        sViewsWithIds.put(R.id.edtInfo, 11);
        sViewsWithIds.put(R.id.txtOpenGraphSearch, 12);
        sViewsWithIds.put(R.id.txtCloseGraphSearch, 13);
        sViewsWithIds.put(R.id.layoutGraphsearch, 14);
        sViewsWithIds.put(R.id.linearLayout9, 15);
        sViewsWithIds.put(R.id.txtTitleReleaseYear, 16);
        sViewsWithIds.put(R.id.edtReleaseYear, 17);
        sViewsWithIds.put(R.id.linearLayoutLocate, 18);
        sViewsWithIds.put(R.id.txtTitleLocate, 19);
        sViewsWithIds.put(R.id.spinerLocate, 20);
        sViewsWithIds.put(R.id.linearLayoutContent, 21);
        sViewsWithIds.put(R.id.txtTitleContent, 22);
        sViewsWithIds.put(R.id.spinerContent, 23);
        sViewsWithIds.put(R.id.linearLayoutStack, 24);
        sViewsWithIds.put(R.id.txtTitleStack, 25);
        sViewsWithIds.put(R.id.edtStack, 26);
        sViewsWithIds.put(R.id.linearLayoutCategory, 27);
        sViewsWithIds.put(R.id.txtTitleCategory, 28);
        sViewsWithIds.put(R.id.spinerCategory, 29);
        sViewsWithIds.put(R.id.linearLayoutCompanyBranch, 30);
        sViewsWithIds.put(R.id.txtTitleCompanyBranch, 31);
        sViewsWithIds.put(R.id.spinnerCompanyBranch, 32);
        sViewsWithIds.put(R.id.chkSearchByTime, 33);
        sViewsWithIds.put(R.id.linearLayoutTime, 34);
        sViewsWithIds.put(R.id.txtTu, 35);
        sViewsWithIds.put(R.id.layoutDateForm, 36);
        sViewsWithIds.put(R.id.txtDateFrom, 37);
        sViewsWithIds.put(R.id.imageView24, 38);
        sViewsWithIds.put(R.id.txtDen, 39);
        sViewsWithIds.put(R.id.layoutDateTo, 40);
        sViewsWithIds.put(R.id.txtDateTo, 41);
        sViewsWithIds.put(R.id.imageView241, 42);
        sViewsWithIds.put(R.id.button, 43);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DocumentFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 44, sIncludes, sViewsWithIds));
    }
    private DocumentFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[43]
            , (android.widget.CheckBox) bindings[33]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[5]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[8]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[11]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[17]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[26]
            , (android.widget.ImageView) bindings[38]
            , (android.widget.ImageView) bindings[42]
            , (android.view.View) bindings[1]
            , (android.widget.LinearLayout) bindings[2]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[36]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[40]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[15]
            , (android.widget.LinearLayout) bindings[27]
            , (android.widget.LinearLayout) bindings[30]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.LinearLayout) bindings[34]
            , (android.widget.Spinner) bindings[29]
            , (android.widget.Spinner) bindings[23]
            , (android.widget.Spinner) bindings[20]
            , (android.widget.Spinner) bindings[32]
            , (android.widget.TextView) bindings[13]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[37]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[41]
            , (android.widget.TextView) bindings[39]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[35]
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