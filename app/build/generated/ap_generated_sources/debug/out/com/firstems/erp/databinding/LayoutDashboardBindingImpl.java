package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class LayoutDashboardBindingImpl extends LayoutDashboardBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(40);
        sIncludes.setIncludes(1, 
            new String[] {"layout_dashboard_waitting"},
            new int[] {6},
            new int[] {com.firstems.erp.R.layout.layout_dashboard_waitting});
        sIncludes.setIncludes(2, 
            new String[] {"layout_dashboard_card_top"},
            new int[] {4},
            new int[] {com.firstems.erp.R.layout.layout_dashboard_card_top});
        sIncludes.setIncludes(3, 
            new String[] {"layout_dashboard_card_bottom"},
            new int[] {5},
            new int[] {com.firstems.erp.R.layout.layout_dashboard_card_bottom});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.app_bar_layout, 7);
        sViewsWithIds.put(R.id.collapsing_toolbar, 8);
        sViewsWithIds.put(R.id.imageView, 9);
        sViewsWithIds.put(R.id.textView, 10);
        sViewsWithIds.put(R.id.imageView2, 11);
        sViewsWithIds.put(R.id.imageView3, 12);
        sViewsWithIds.put(R.id.nested_content, 13);
        sViewsWithIds.put(R.id.topBarChart, 14);
        sViewsWithIds.put(R.id.textView2, 15);
        sViewsWithIds.put(R.id.textView3, 16);
        sViewsWithIds.put(R.id.imageView4, 17);
        sViewsWithIds.put(R.id.textView4, 18);
        sViewsWithIds.put(R.id.imageView5, 19);
        sViewsWithIds.put(R.id.textView6, 20);
        sViewsWithIds.put(R.id.textView7, 21);
        sViewsWithIds.put(R.id.textView8, 22);
        sViewsWithIds.put(R.id.textView9, 23);
        sViewsWithIds.put(R.id.textView10, 24);
        sViewsWithIds.put(R.id.textView11, 25);
        sViewsWithIds.put(R.id.imageView6, 26);
        sViewsWithIds.put(R.id.textView12, 27);
        sViewsWithIds.put(R.id.textView13, 28);
        sViewsWithIds.put(R.id.textView14, 29);
        sViewsWithIds.put(R.id.imageView7, 30);
        sViewsWithIds.put(R.id.textView15, 31);
        sViewsWithIds.put(R.id.textView16, 32);
        sViewsWithIds.put(R.id.textView17, 33);
        sViewsWithIds.put(R.id.imageView8, 34);
        sViewsWithIds.put(R.id.textView18, 35);
        sViewsWithIds.put(R.id.textView19, 36);
        sViewsWithIds.put(R.id.textView20, 37);
        sViewsWithIds.put(R.id.imageView9, 38);
        sViewsWithIds.put(R.id.chartPie, 39);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @Nullable
    private final com.firstems.erp.databinding.LayoutDashboardWaittingBinding mboundView11;
    @NonNull
    private final android.widget.LinearLayout mboundView2;
    @Nullable
    private final com.firstems.erp.databinding.LayoutDashboardCardTopBinding mboundView21;
    @NonNull
    private final android.widget.LinearLayout mboundView3;
    @Nullable
    private final com.firstems.erp.databinding.LayoutDashboardCardBottomBinding mboundView31;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LayoutDashboardBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 40, sIncludes, sViewsWithIds));
    }
    private LayoutDashboardBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[7]
            , (com.github.mikephil.charting.charts.PieChart) bindings[39]
            , (com.google.android.material.appbar.CollapsingToolbarLayout) bindings[8]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.ImageView) bindings[19]
            , (android.widget.ImageView) bindings[26]
            , (android.widget.ImageView) bindings[30]
            , (android.widget.ImageView) bindings[34]
            , (android.widget.ImageView) bindings[38]
            , (androidx.core.widget.NestedScrollView) bindings[13]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[29]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[32]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[35]
            , (android.widget.TextView) bindings[36]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[37]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[23]
            , (com.github.mikephil.charting.charts.BarChart) bindings[14]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView11 = (com.firstems.erp.databinding.LayoutDashboardWaittingBinding) bindings[6];
        setContainedBinding(this.mboundView11);
        this.mboundView2 = (android.widget.LinearLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView21 = (com.firstems.erp.databinding.LayoutDashboardCardTopBinding) bindings[4];
        setContainedBinding(this.mboundView21);
        this.mboundView3 = (android.widget.LinearLayout) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView31 = (com.firstems.erp.databinding.LayoutDashboardCardBottomBinding) bindings[5];
        setContainedBinding(this.mboundView31);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        mboundView21.invalidateAll();
        mboundView31.invalidateAll();
        mboundView11.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView21.hasPendingBindings()) {
            return true;
        }
        if (mboundView31.hasPendingBindings()) {
            return true;
        }
        if (mboundView11.hasPendingBindings()) {
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
        mboundView21.setLifecycleOwner(lifecycleOwner);
        mboundView31.setLifecycleOwner(lifecycleOwner);
        mboundView11.setLifecycleOwner(lifecycleOwner);
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
        executeBindingsOn(mboundView21);
        executeBindingsOn(mboundView31);
        executeBindingsOn(mboundView11);
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