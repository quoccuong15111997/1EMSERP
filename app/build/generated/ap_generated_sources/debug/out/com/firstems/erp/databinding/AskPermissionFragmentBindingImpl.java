package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AskPermissionFragmentBindingImpl extends AskPermissionFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include2, 4);
        sViewsWithIds.put(R.id.lParentContent, 5);
        sViewsWithIds.put(R.id.linearLayout6, 6);
        sViewsWithIds.put(R.id.txtTitleDateCreate, 7);
        sViewsWithIds.put(R.id.imageView, 8);
        sViewsWithIds.put(R.id.linearLayout8, 9);
        sViewsWithIds.put(R.id.txtTitleInfo, 10);
        sViewsWithIds.put(R.id.layoutSum, 11);
        sViewsWithIds.put(R.id.txtTitleDocumentCode, 12);
        sViewsWithIds.put(R.id.EMSLinearLayout2, 13);
        sViewsWithIds.put(R.id.txtTitleDate, 14);
        sViewsWithIds.put(R.id.txtTitleSpecies, 15);
        sViewsWithIds.put(R.id.txtTitleSum, 16);
        sViewsWithIds.put(R.id.imgAdd, 17);
        sViewsWithIds.put(R.id.recycleview, 18);
        sViewsWithIds.put(R.id.frame_ask_permistion, 19);
        sViewsWithIds.put(R.id.constraintLayout12, 20);
        sViewsWithIds.put(R.id.imageView3, 21);
        sViewsWithIds.put(R.id.textView5, 22);
        sViewsWithIds.put(R.id.constraintLayout13, 23);
        sViewsWithIds.put(R.id.txtTrinhKi, 24);
        sViewsWithIds.put(R.id.llDelete, 25);
        sViewsWithIds.put(R.id.imageView37, 26);
        sViewsWithIds.put(R.id.textView55, 27);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public AskPermissionFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }
    private AskPermissionFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.firstems.erp.helper.widgets.EMSLinearLayout) bindings[13]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[20]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[23]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[1]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.FrameLayout) bindings[19]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[21]
            , (android.widget.ImageView) bindings[26]
            , (android.widget.ImageView) bindings[17]
            , (android.view.View) bindings[4]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[25]
            , (androidx.recyclerview.widget.RecyclerView) bindings[18]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[27]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[14]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[10]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[15]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[16]
            , (android.widget.TextView) bindings[24]
            );
        this.editTextTextPersonName.setTag(null);
        this.edtInfo.setTag(null);
        this.edtSumDay.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.item == variableId) {
            setItem((com.firstems.erp.api.model.response.askpermistion.AskPermistionHeader) variable);
        }
        else if (BR.isEditable == variableId) {
            setIsEditable((java.lang.Boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.firstems.erp.api.model.response.askpermistion.AskPermistionHeader Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    public void setIsEditable(@Nullable java.lang.Boolean IsEditable) {
        this.mIsEditable = IsEditable;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.isEditable);
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
        com.firstems.erp.api.model.response.askpermistion.AskPermistionHeader item = mItem;
        boolean androidxDatabindingViewDataBindingSafeUnboxIsEditable = false;
        int intItemSUMLEAV = 0;
        java.lang.String itemMainDate = null;
        double itemSUMLEAV = 0.0;
        java.lang.String itemMEXLNNTE = null;
        java.lang.String stringValueOfIntItemSUMLEAV = null;
        java.lang.Boolean isEditable = mIsEditable;

        if ((dirtyFlags & 0x5L) != 0) {



                if (item != null) {
                    // read item.mainDate
                    itemMainDate = item.getMainDate();
                    // read item.sUMLEAV
                    itemSUMLEAV = item.sUMLEAV;
                    // read item.mEXLNNTE
                    itemMEXLNNTE = item.mEXLNNTE;
                }


                // read (int) item.sUMLEAV
                intItemSUMLEAV = ((int) (itemSUMLEAV));


                // read String.valueOf((int) item.sUMLEAV)
                stringValueOfIntItemSUMLEAV = java.lang.String.valueOf(intItemSUMLEAV);
        }
        if ((dirtyFlags & 0x6L) != 0) {



                // read androidx.databinding.ViewDataBinding.safeUnbox(isEditable)
                androidxDatabindingViewDataBindingSafeUnboxIsEditable = androidx.databinding.ViewDataBinding.safeUnbox(isEditable);
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.editTextTextPersonName, itemMainDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtInfo, itemMEXLNNTE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtSumDay, stringValueOfIntItemSUMLEAV);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.edtInfo.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): isEditable
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}