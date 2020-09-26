package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BusinessRegistrationFragmentBindingImpl extends BusinessRegistrationFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include5, 16);
        sViewsWithIds.put(R.id.nestedScrollView, 17);
        sViewsWithIds.put(R.id.lParentContent, 18);
        sViewsWithIds.put(R.id.linearLayout5, 19);
        sViewsWithIds.put(R.id.linearLayout67, 20);
        sViewsWithIds.put(R.id.txtTitleNumberDay, 21);
        sViewsWithIds.put(R.id.layoutNumberDay, 22);
        sViewsWithIds.put(R.id.imageView241, 23);
        sViewsWithIds.put(R.id.linearLayout71, 24);
        sViewsWithIds.put(R.id.txtTitleDateEnd1, 25);
        sViewsWithIds.put(R.id.layoutDateEnd1, 26);
        sViewsWithIds.put(R.id.txtTitleDateEnd11, 27);
        sViewsWithIds.put(R.id.imageView2421, 28);
        sViewsWithIds.put(R.id.linearLayoutDateCreate, 29);
        sViewsWithIds.put(R.id.txtTitleDateCreate, 30);
        sViewsWithIds.put(R.id.imageView, 31);
        sViewsWithIds.put(R.id.linearLayout, 32);
        sViewsWithIds.put(R.id.linearLayout6, 33);
        sViewsWithIds.put(R.id.txtTitleDateBegin, 34);
        sViewsWithIds.put(R.id.imageView24, 35);
        sViewsWithIds.put(R.id.linearLayout7, 36);
        sViewsWithIds.put(R.id.txtTitleDateEnd, 37);
        sViewsWithIds.put(R.id.imageView242, 38);
        sViewsWithIds.put(R.id.linearLayout8, 39);
        sViewsWithIds.put(R.id.txtTitleInfo, 40);
        sViewsWithIds.put(R.id.constraintLayout2, 41);
        sViewsWithIds.put(R.id.txtTitleContent, 42);
        sViewsWithIds.put(R.id.EMSLinearLayout2, 43);
        sViewsWithIds.put(R.id.txtTitleDate, 44);
        sViewsWithIds.put(R.id.txtTitleSpecies, 45);
        sViewsWithIds.put(R.id.imgAdd, 46);
        sViewsWithIds.put(R.id.recycleview, 47);
        sViewsWithIds.put(R.id.frame_reviews_progress, 48);
        sViewsWithIds.put(R.id.linearLayout11, 49);
        sViewsWithIds.put(R.id.constraintLayout11, 50);
        sViewsWithIds.put(R.id.imageView3, 51);
        sViewsWithIds.put(R.id.textView5, 52);
        sViewsWithIds.put(R.id.constraintLayout10, 53);
        sViewsWithIds.put(R.id.txtTrinhKi, 54);
        sViewsWithIds.put(R.id.llDelete, 55);
        sViewsWithIds.put(R.id.imageView32, 56);
        sViewsWithIds.put(R.id.textView54, 57);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BusinessRegistrationFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 58, sIncludes, sViewsWithIds));
    }
    private BusinessRegistrationFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.firstems.erp.helper.widgets.EMSLinearLayout) bindings[43]
            , (android.widget.CheckBox) bindings[10]
            , (android.widget.CheckBox) bindings[12]
            , (android.widget.CheckBox) bindings[14]
            , (android.widget.CheckBox) bindings[8]
            , (android.widget.CheckBox) bindings[9]
            , (android.widget.CheckBox) bindings[15]
            , (android.widget.CheckBox) bindings[13]
            , (android.widget.CheckBox) bindings[11]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[53]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[50]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[41]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[7]
            , (android.widget.FrameLayout) bindings[48]
            , (android.widget.ImageView) bindings[31]
            , (android.widget.ImageView) bindings[35]
            , (android.widget.ImageView) bindings[23]
            , (android.widget.ImageView) bindings[38]
            , (android.widget.ImageView) bindings[28]
            , (android.widget.ImageView) bindings[51]
            , (android.widget.ImageView) bindings[56]
            , (android.widget.ImageView) bindings[46]
            , (android.view.View) bindings[16]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[18]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[26]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[3]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[32]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[49]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[33]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[36]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.LinearLayout) bindings[39]
            , (android.widget.LinearLayout) bindings[29]
            , (android.widget.LinearLayout) bindings[55]
            , (androidx.core.widget.NestedScrollView) bindings[17]
            , (androidx.recyclerview.widget.RecyclerView) bindings[47]
            , (android.widget.TextView) bindings[52]
            , (android.widget.TextView) bindings[57]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[4]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[2]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[6]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[1]
            , (android.widget.TextView) bindings[42]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[44]
            , (android.widget.TextView) bindings[34]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[37]
            , (android.widget.TextView) bindings[25]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[27]
            , (android.widget.TextView) bindings[40]
            , (android.widget.TextView) bindings[21]
            , (com.firstems.erp.helper.widgets.EMSTextviewWhite) bindings[45]
            , (android.widget.TextView) bindings[54]
            );
        this.chkHotel.setTag(null);
        this.chkPhuongTienCongTacKhac.setTag(null);
        this.chkTamUng.setTag(null);
        this.chkVeMayBay.setTag(null);
        this.chkVeTauLua.setTag(null);
        this.chkVeTauThuy.setTag(null);
        this.chkVisa.setTag(null);
        this.chkXeCongTacTronChuyen.setTag(null);
        this.edtInfo.setTag(null);
        this.layoutDateEnd.setTag(null);
        this.layoutDateForm.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtDateBegin.setTag(null);
        this.txtDateCreate.setTag(null);
        this.txtDateEnd.setTag(null);
        this.txtNumberDay.setTag(null);
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
            setItem((com.firstems.erp.model.business.Business) variable);
        }
        else if (BR.isEditable == variableId) {
            setIsEditable((java.lang.Boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.firstems.erp.model.business.Business Item) {
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
        com.firstems.erp.model.business.Business item = mItem;
        int itemWorkDay = 0;
        boolean itemService6 = false;
        boolean itemService2 = false;
        boolean itemService4 = false;
        boolean itemService0 = false;
        java.lang.String stringValueOfItemWorkDay = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxIsEditable = false;
        java.lang.String itemMainDateDisplay = null;
        boolean itemService7 = false;
        boolean itemService3 = false;
        boolean itemService5 = false;
        boolean itemService1 = false;
        java.lang.String itemDateBeginDisplay = null;
        java.lang.String itemNoteText = null;
        java.lang.String itemDateEndDisplay = null;
        java.lang.Boolean isEditable = mIsEditable;

        if ((dirtyFlags & 0x5L) != 0) {



                if (item != null) {
                    // read item.workDay
                    itemWorkDay = item.getWorkDay();
                    // read item.service_6
                    itemService6 = item.isService_6();
                    // read item.service_2
                    itemService2 = item.isService_2();
                    // read item.service_4
                    itemService4 = item.isService_4();
                    // read item.service_0
                    itemService0 = item.isService_0();
                    // read item.mainDateDisplay
                    itemMainDateDisplay = item.getMainDateDisplay();
                    // read item.service_7
                    itemService7 = item.isService_7();
                    // read item.service_3
                    itemService3 = item.isService_3();
                    // read item.service_5
                    itemService5 = item.isService_5();
                    // read item.service_1
                    itemService1 = item.isService_1();
                    // read item.dateBeginDisplay
                    itemDateBeginDisplay = item.getDateBeginDisplay();
                    // read item.noteText
                    itemNoteText = item.getNoteText();
                    // read item.dateEndDisplay
                    itemDateEndDisplay = item.getDateEndDisplay();
                }


                // read String.valueOf(item.workDay)
                stringValueOfItemWorkDay = java.lang.String.valueOf(itemWorkDay);
        }
        if ((dirtyFlags & 0x6L) != 0) {



                // read androidx.databinding.ViewDataBinding.safeUnbox(isEditable)
                androidxDatabindingViewDataBindingSafeUnboxIsEditable = androidx.databinding.ViewDataBinding.safeUnbox(isEditable);
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkHotel, itemService2);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkPhuongTienCongTacKhac, itemService4);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkTamUng, itemService6);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkVeMayBay, itemService0);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkVeTauLua, itemService1);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkVeTauThuy, itemService7);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkVisa, itemService5);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkXeCongTacTronChuyen, itemService3);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtInfo, itemNoteText);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtDateBegin, itemDateBeginDisplay);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtDateCreate, itemMainDateDisplay);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtDateEnd, itemDateEndDisplay);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtNumberDay, stringValueOfItemWorkDay);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.chkHotel.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.chkPhuongTienCongTacKhac.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.chkTamUng.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.chkVeMayBay.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.chkVeTauLua.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.chkVeTauThuy.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.chkVisa.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.chkXeCongTacTronChuyen.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.edtInfo.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.layoutDateEnd.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.layoutDateForm.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.txtDateBegin.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
            this.txtDateEnd.setEnabled(androidxDatabindingViewDataBindingSafeUnboxIsEditable);
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