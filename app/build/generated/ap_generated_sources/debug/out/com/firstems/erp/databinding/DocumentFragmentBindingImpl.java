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
        sViewsWithIds.put(R.id.linearLayout6, 2);
        sViewsWithIds.put(R.id.txtTitleDocumentCode, 3);
        sViewsWithIds.put(R.id.edtDocumentCode, 4);
        sViewsWithIds.put(R.id.linearLayout7, 5);
        sViewsWithIds.put(R.id.txtTitleDocumentName, 6);
        sViewsWithIds.put(R.id.edtDocumentName, 7);
        sViewsWithIds.put(R.id.linearLayout8, 8);
        sViewsWithIds.put(R.id.txtTitleInfo, 9);
        sViewsWithIds.put(R.id.edtInfo, 10);
        sViewsWithIds.put(R.id.txtOpenGraphSearch, 11);
        sViewsWithIds.put(R.id.txtCloseGraphSearch, 12);
        sViewsWithIds.put(R.id.layoutGraphsearch, 13);
        sViewsWithIds.put(R.id.linearLayout9, 14);
        sViewsWithIds.put(R.id.txtTitleReleaseYear, 15);
        sViewsWithIds.put(R.id.edtReleaseYear, 16);
        sViewsWithIds.put(R.id.linearLayoutLocate, 17);
        sViewsWithIds.put(R.id.txtTitleLocate, 18);
        sViewsWithIds.put(R.id.spinerLocate, 19);
        sViewsWithIds.put(R.id.linearLayoutContent, 20);
        sViewsWithIds.put(R.id.txtTitleContent, 21);
        sViewsWithIds.put(R.id.spinerContent, 22);
        sViewsWithIds.put(R.id.linearLayoutStack, 23);
        sViewsWithIds.put(R.id.txtTitleStack, 24);
        sViewsWithIds.put(R.id.edtStack, 25);
        sViewsWithIds.put(R.id.linearLayoutCategory, 26);
        sViewsWithIds.put(R.id.txtTitleCategory, 27);
        sViewsWithIds.put(R.id.spinerCategory, 28);
        sViewsWithIds.put(R.id.linearLayoutCompanyBranch, 29);
        sViewsWithIds.put(R.id.txtTitleCompanyBranch, 30);
        sViewsWithIds.put(R.id.spinnerCompanyBranch, 31);
        sViewsWithIds.put(R.id.chkSearchByTime, 32);
        sViewsWithIds.put(R.id.linearLayoutTime, 33);
        sViewsWithIds.put(R.id.txtTu, 34);
        sViewsWithIds.put(R.id.layoutDateForm, 35);
        sViewsWithIds.put(R.id.txtDateFrom, 36);
        sViewsWithIds.put(R.id.imageView24, 37);
        sViewsWithIds.put(R.id.txtDen, 38);
        sViewsWithIds.put(R.id.layoutDateTo, 39);
        sViewsWithIds.put(R.id.txtDateTo, 40);
        sViewsWithIds.put(R.id.imageView241, 41);
        sViewsWithIds.put(R.id.button, 42);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DocumentFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 43, sIncludes, sViewsWithIds));
    }
    private DocumentFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[42]
            , (android.widget.CheckBox) bindings[32]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[4]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[7]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[10]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[16]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[25]
            , (android.widget.ImageView) bindings[37]
            , (android.widget.ImageView) bindings[41]
            , (android.view.View) bindings[1]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[35]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[39]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[26]
            , (android.widget.LinearLayout) bindings[29]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[23]
            , (android.widget.LinearLayout) bindings[33]
            , (android.widget.Spinner) bindings[28]
            , (android.widget.Spinner) bindings[22]
            , (android.widget.Spinner) bindings[19]
            , (android.widget.Spinner) bindings[31]
            , (android.widget.TextView) bindings[12]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[36]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[40]
            , (android.widget.TextView) bindings[38]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[34]
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