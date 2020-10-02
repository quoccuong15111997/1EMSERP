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
        sViewsWithIds.put(R.id.include10, 2);
        sViewsWithIds.put(R.id.cardView, 3);
        sViewsWithIds.put(R.id.linearLayoutLocate, 4);
        sViewsWithIds.put(R.id.linearLayoutLocateType, 5);
        sViewsWithIds.put(R.id.txtTileNoiCongTac, 6);
        sViewsWithIds.put(R.id.spinerLocateType, 7);
        sViewsWithIds.put(R.id.linearLayoutLocateDetail, 8);
        sViewsWithIds.put(R.id.txtTitle, 9);
        sViewsWithIds.put(R.id.txtLocate, 10);
        sViewsWithIds.put(R.id.linearLayout, 11);
        sViewsWithIds.put(R.id.linearLayout6, 12);
        sViewsWithIds.put(R.id.txtTitleDateBegin, 13);
        sViewsWithIds.put(R.id.layoutDateForm, 14);
        sViewsWithIds.put(R.id.txtDateBegin, 15);
        sViewsWithIds.put(R.id.imageView24, 16);
        sViewsWithIds.put(R.id.linearLayout7, 17);
        sViewsWithIds.put(R.id.txtTitleDateEnd, 18);
        sViewsWithIds.put(R.id.layoutDateEnd, 19);
        sViewsWithIds.put(R.id.txtDateEnd, 20);
        sViewsWithIds.put(R.id.imageView242, 21);
        sViewsWithIds.put(R.id.linearLayout16, 22);
        sViewsWithIds.put(R.id.linearLayout2, 23);
        sViewsWithIds.put(R.id.chkMorning, 24);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongSang, 25);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongSang, 26);
        sViewsWithIds.put(R.id.spinerLoaiChamCongSang, 27);
        sViewsWithIds.put(R.id.linearLayout222, 28);
        sViewsWithIds.put(R.id.chkAfternoon, 29);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongChieu, 30);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongChieu, 31);
        sViewsWithIds.put(R.id.spinerLoaiChamCongChieu, 32);
        sViewsWithIds.put(R.id.linearLayout22222, 33);
        sViewsWithIds.put(R.id.chkEverning, 34);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongToi, 35);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongToi, 36);
        sViewsWithIds.put(R.id.spinerLoaiChamCongToi, 37);
        sViewsWithIds.put(R.id.materialRippleLayout, 38);
        sViewsWithIds.put(R.id.btnDone, 39);
    }
    // views
    @NonNull
    private final androidx.core.widget.NestedScrollView mboundView0;
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BusinessRegistrationInfoFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 40, sIncludes, sViewsWithIds));
    }
    private BusinessRegistrationInfoFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[39]
            , (androidx.cardview.widget.CardView) bindings[3]
            , (android.widget.CheckBox) bindings[29]
            , (android.widget.CheckBox) bindings[34]
            , (android.widget.CheckBox) bindings[24]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[21]
            , (android.view.View) bindings[2]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[19]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[14]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[23]
            , (android.widget.LinearLayout) bindings[28]
            , (android.widget.LinearLayout) bindings[33]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[30]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[35]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[5]
            , (com.balysv.materialripple.MaterialRippleLayout) bindings[38]
            , (android.widget.Spinner) bindings[32]
            , (android.widget.Spinner) bindings[27]
            , (android.widget.Spinner) bindings[37]
            , (android.widget.Spinner) bindings[7]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[15]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[20]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[36]
            );
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[1];
        this.mboundView1.setTag(null);
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