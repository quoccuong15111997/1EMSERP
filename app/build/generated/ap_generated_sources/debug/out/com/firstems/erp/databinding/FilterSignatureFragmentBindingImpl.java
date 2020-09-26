package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FilterSignatureFragmentBindingImpl extends FilterSignatureFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include8, 4);
        sViewsWithIds.put(R.id.linearLayout, 5);
        sViewsWithIds.put(R.id.linearLayout6, 6);
        sViewsWithIds.put(R.id.txtTitleDateBegin, 7);
        sViewsWithIds.put(R.id.layoutDateForm, 8);
        sViewsWithIds.put(R.id.txtDateBegin, 9);
        sViewsWithIds.put(R.id.imageView24, 10);
        sViewsWithIds.put(R.id.linearLayout7, 11);
        sViewsWithIds.put(R.id.txtTitleDateEnd, 12);
        sViewsWithIds.put(R.id.layoutDateEnd, 13);
        sViewsWithIds.put(R.id.txtDateEnd, 14);
        sViewsWithIds.put(R.id.imageView242, 15);
        sViewsWithIds.put(R.id.txtTitleStatus, 16);
        sViewsWithIds.put(R.id.view4, 17);
        sViewsWithIds.put(R.id.view5, 18);
        sViewsWithIds.put(R.id.view6, 19);
        sViewsWithIds.put(R.id.btnSort, 20);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FilterSignatureFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private FilterSignatureFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.firstems.erp.helper.widgets.EMSButtonSecond) bindings[20]
            , (android.widget.CheckBox) bindings[2]
            , (android.widget.CheckBox) bindings[1]
            , (android.widget.CheckBox) bindings[3]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[15]
            , (android.view.View) bindings[4]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[13]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[8]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[11]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[9]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[14]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[16]
            , (android.view.View) bindings[17]
            , (android.view.View) bindings[18]
            , (android.view.View) bindings[19]
            );
        this.chkChoDuyet.setTag(null);
        this.chkChuaTrinhKy.setTag(null);
        this.chkHoanTat.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
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
        if (BR.filter == variableId) {
            setFilter((com.firstems.erp.model.FilterModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setFilter(@Nullable com.firstems.erp.model.FilterModel Filter) {
        this.mFilter = Filter;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.filter);
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
        boolean filterWaitApproved = false;
        com.firstems.erp.model.FilterModel filter = mFilter;
        boolean filterWaitsignature = false;
        boolean filterDone = false;

        if ((dirtyFlags & 0x3L) != 0) {



                if (filter != null) {
                    // read filter.waitApproved
                    filterWaitApproved = filter.isWaitApproved();
                    // read filter.waitsignature
                    filterWaitsignature = filter.isWaitsignature();
                    // read filter.done
                    filterDone = filter.isDone();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkChoDuyet, filterWaitApproved);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkChuaTrinhKy, filterWaitsignature);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkHoanTat, filterDone);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): filter
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}