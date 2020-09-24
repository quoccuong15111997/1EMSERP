package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DashboardFragmentBindingImpl extends DashboardFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(22);
        sIncludes.setIncludes(1, 
            new String[] {"layout_top_dashboard"},
            new int[] {3},
            new int[] {com.firstems.erp.R.layout.layout_top_dashboard});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include12, 2);
        sViewsWithIds.put(R.id.constraintLayout5, 4);
        sViewsWithIds.put(R.id.imgCalenda, 5);
        sViewsWithIds.put(R.id.spinnerTime, 6);
        sViewsWithIds.put(R.id.topBarChart, 7);
        sViewsWithIds.put(R.id.constraintLayout6, 8);
        sViewsWithIds.put(R.id.txtTitleOrder, 9);
        sViewsWithIds.put(R.id.txtOrderValue, 10);
        sViewsWithIds.put(R.id.txtValueNumberOrder, 11);
        sViewsWithIds.put(R.id.txtUnitOrder, 12);
        sViewsWithIds.put(R.id.imageView7, 13);
        sViewsWithIds.put(R.id.view7, 14);
        sViewsWithIds.put(R.id.txtTitleVentory, 15);
        sViewsWithIds.put(R.id.txtVentoryValue, 16);
        sViewsWithIds.put(R.id.txtValueNumberVentory, 17);
        sViewsWithIds.put(R.id.txtUnitVentory, 18);
        sViewsWithIds.put(R.id.txtTitleRevenue, 19);
        sViewsWithIds.put(R.id.imgContinueRevenue, 20);
        sViewsWithIds.put(R.id.chartPie, 21);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DashboardFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }
    private DashboardFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.github.mikephil.charting.charts.PieChart) bindings[21]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[4]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[8]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[20]
            , (android.view.View) bindings[2]
            , (com.firstems.erp.databinding.LayoutTopDashboardBinding) bindings[3]
            , (android.widget.Spinner) bindings[6]
            , (com.github.mikephil.charting.charts.BarChart) bindings[7]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[16]
            , (android.view.View) bindings[14]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
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
                mDirtyFlags = 0x2L;
        }
        include13.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (include13.hasPendingBindings()) {
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
        include13.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeInclude13((com.firstems.erp.databinding.LayoutTopDashboardBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeInclude13(com.firstems.erp.databinding.LayoutTopDashboardBinding Include13, int fieldId) {
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
        executeBindingsOn(include13);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): include13
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}