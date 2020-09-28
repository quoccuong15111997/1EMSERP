package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BusinessRegistrationInfoFragmentBindingImpl extends BusinessRegistrationInfoFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include10, 1);
        sViewsWithIds.put(R.id.cardView, 2);
        sViewsWithIds.put(R.id.linearLayoutLocate, 3);
        sViewsWithIds.put(R.id.linearLayoutLocateType, 4);
        sViewsWithIds.put(R.id.txtTileNoiCongTac, 5);
        sViewsWithIds.put(R.id.spinerLocateType, 6);
        sViewsWithIds.put(R.id.linearLayoutLocateDetail, 7);
        sViewsWithIds.put(R.id.txtTitle, 8);
        sViewsWithIds.put(R.id.txtLocate, 9);
        sViewsWithIds.put(R.id.linearLayout, 10);
        sViewsWithIds.put(R.id.linearLayout6, 11);
        sViewsWithIds.put(R.id.txtTitleDateBegin, 12);
        sViewsWithIds.put(R.id.layoutDateForm, 13);
        sViewsWithIds.put(R.id.txtDateBegin, 14);
        sViewsWithIds.put(R.id.imageView24, 15);
        sViewsWithIds.put(R.id.linearLayout7, 16);
        sViewsWithIds.put(R.id.txtTitleDateEnd, 17);
        sViewsWithIds.put(R.id.layoutDateEnd, 18);
        sViewsWithIds.put(R.id.txtDateEnd, 19);
        sViewsWithIds.put(R.id.imageView242, 20);
        sViewsWithIds.put(R.id.linearLayout2, 21);
        sViewsWithIds.put(R.id.chkMorning, 22);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongSang, 23);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongSang, 24);
        sViewsWithIds.put(R.id.spinerLoaiChamCongSang, 25);
        sViewsWithIds.put(R.id.linearLayout222, 26);
        sViewsWithIds.put(R.id.chkAfternoon, 27);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongChieu, 28);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongChieu, 29);
        sViewsWithIds.put(R.id.spinerLoaiChamCongChieu, 30);
        sViewsWithIds.put(R.id.linearLayout22222, 31);
        sViewsWithIds.put(R.id.chkEverning, 32);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongToi, 33);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongToi, 34);
        sViewsWithIds.put(R.id.spinerLoaiChamCongToi, 35);
        sViewsWithIds.put(R.id.btnDone, 36);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BusinessRegistrationInfoFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 37, sIncludes, sViewsWithIds));
    }
    private BusinessRegistrationInfoFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.firstems.erp.helper.widgets.EMSButtonSecond) bindings[36]
            , (androidx.cardview.widget.CardView) bindings[2]
            , (android.widget.CheckBox) bindings[27]
            , (android.widget.CheckBox) bindings[32]
            , (android.widget.CheckBox) bindings[22]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[20]
            , (android.view.View) bindings[1]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[18]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[13]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[26]
            , (android.widget.LinearLayout) bindings[31]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[28]
            , (android.widget.LinearLayout) bindings[23]
            , (android.widget.LinearLayout) bindings[33]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.Spinner) bindings[30]
            , (android.widget.Spinner) bindings[25]
            , (android.widget.Spinner) bindings[35]
            , (android.widget.Spinner) bindings[6]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[14]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[19]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[29]
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