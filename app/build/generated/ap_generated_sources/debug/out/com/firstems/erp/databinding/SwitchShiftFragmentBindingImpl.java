package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SwitchShiftFragmentBindingImpl extends SwitchShiftFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include2, 4);
        sViewsWithIds.put(R.id.lParentContent, 5);
        sViewsWithIds.put(R.id.textView, 6);
        sViewsWithIds.put(R.id.view, 7);
        sViewsWithIds.put(R.id.linearLayout6, 8);
        sViewsWithIds.put(R.id.txtTitleDocumentCode, 9);
        sViewsWithIds.put(R.id.imageView, 10);
        sViewsWithIds.put(R.id.linearLayout8, 11);
        sViewsWithIds.put(R.id.txtTitleInfo, 12);
        sViewsWithIds.put(R.id.EMSLinearLayout2, 13);
        sViewsWithIds.put(R.id.txtTitleDate, 14);
        sViewsWithIds.put(R.id.txtTitleSpecies, 15);
        sViewsWithIds.put(R.id.txtTitleSum, 16);
        sViewsWithIds.put(R.id.imgAdd, 17);
        sViewsWithIds.put(R.id.recycleview, 18);
        sViewsWithIds.put(R.id.frame_progress_switch_shift, 19);
        sViewsWithIds.put(R.id.constraintLayout14, 20);
        sViewsWithIds.put(R.id.llDelete, 21);
        sViewsWithIds.put(R.id.imgDelete, 22);
        sViewsWithIds.put(R.id.txtDelete, 23);
        sViewsWithIds.put(R.id.constraintLayout8, 24);
        sViewsWithIds.put(R.id.imageView3, 25);
        sViewsWithIds.put(R.id.textView5, 26);
        sViewsWithIds.put(R.id.constraintLayout9, 27);
        sViewsWithIds.put(R.id.txtTrinhKi, 28);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SwitchShiftFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 29, sIncludes, sViewsWithIds));
    }
    private SwitchShiftFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.firstems.erp.helper.widgets.EMSLinearLayout) bindings[13]
            , (android.widget.CheckBox) bindings[3]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[20]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[24]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[27]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[1]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[2]
            , (android.widget.FrameLayout) bindings[19]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[25]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.ImageView) bindings[22]
            , (android.view.View) bindings[4]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[21]
            , (androidx.recyclerview.widget.RecyclerView) bindings[18]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[23]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[14]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[12]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[15]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[16]
            , (android.widget.TextView) bindings[28]
            , (android.view.View) bindings[7]
            );
        this.chkReShift.setTag(null);
        this.editTextTextPersonName.setTag(null);
        this.edtInfo.setTag(null);
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
            setItem((com.firstems.erp.api.model.response.signature.switchshift.SwitchShiftItem) variable);
        }
        else if (BR.isEditable == variableId) {
            setIsEditable((java.lang.Boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.firstems.erp.api.model.response.signature.switchshift.SwitchShiftItem Item) {
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
        com.firstems.erp.api.model.response.signature.switchshift.SwitchShiftItem item = mItem;
        boolean androidxDatabindingViewDataBindingSafeUnboxIsEditable = false;
        java.lang.String itemMainDateDisplay = null;
        java.lang.String itemNoteText = null;
        boolean itemCheckedInt1 = false;
        java.lang.Boolean isEditable = mIsEditable;
        int itemChecked = 0;

        if ((dirtyFlags & 0x5L) != 0) {



                if (item != null) {
                    // read item.mainDateDisplay
                    itemMainDateDisplay = item.getMainDateDisplay();
                    // read item.noteText
                    itemNoteText = item.getNoteText();
                    // read item.checked
                    itemChecked = item.getChecked();
                }


                // read item.checked == 1
                itemCheckedInt1 = (itemChecked) == (1);
        }
        if ((dirtyFlags & 0x6L) != 0) {



                // read androidx.databinding.ViewDataBinding.safeUnbox(isEditable)
                androidxDatabindingViewDataBindingSafeUnboxIsEditable = androidx.databinding.ViewDataBinding.safeUnbox(isEditable);
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkReShift, itemCheckedInt1);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.editTextTextPersonName, itemMainDateDisplay);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtInfo, itemNoteText);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.chkReShift.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
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