package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SellFragmentBindingImpl extends SellFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(17);
        sIncludes.setIncludes(0, 
            new String[] {"layout_select_date"},
            new int[] {1},
            new int[] {com.firstems.erp.R.layout.layout_select_date});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textView21, 2);
        sViewsWithIds.put(R.id.imageView10, 3);
        sViewsWithIds.put(R.id.textView23, 4);
        sViewsWithIds.put(R.id.view8, 5);
        sViewsWithIds.put(R.id.textView24, 6);
        sViewsWithIds.put(R.id.textView27, 7);
        sViewsWithIds.put(R.id.textView28, 8);
        sViewsWithIds.put(R.id.textView29, 9);
        sViewsWithIds.put(R.id.constraintLayoutRevenue, 10);
        sViewsWithIds.put(R.id.textView30, 11);
        sViewsWithIds.put(R.id.view9, 12);
        sViewsWithIds.put(R.id.imageView14, 13);
        sViewsWithIds.put(R.id.textView31, 14);
        sViewsWithIds.put(R.id.topBarChart, 15);
        sViewsWithIds.put(R.id.imgFullScreen, 16);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SellFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }
    private SellFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[10]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[16]
            , (com.firstems.erp.databinding.LayoutSelectDateBinding) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[14]
            , (com.github.mikephil.charting.charts.BarChart) bindings[15]
            , (android.view.View) bindings[5]
            , (android.view.View) bindings[12]
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
                mDirtyFlags = 0x2L;
        }
        include15.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (include15.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        include15.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeInclude15((com.firstems.erp.databinding.LayoutSelectDateBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeInclude15(com.firstems.erp.databinding.LayoutSelectDateBinding Include15, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        executeBindingsOn(include15);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): include15
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}