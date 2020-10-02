package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ApproveDetailFragmentBindingImpl extends ApproveDetailFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(23);
        sIncludes.setIncludes(1, 
            new String[] {"layout_option_approved"},
            new int[] {3},
            new int[] {com.firstems.erp.R.layout.layout_option_approved});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include9, 2);
        sViewsWithIds.put(R.id.lParentContent, 4);
        sViewsWithIds.put(R.id.layoutTitleNoiDung, 5);
        sViewsWithIds.put(R.id.textView2, 6);
        sViewsWithIds.put(R.id.layoutInfo, 7);
        sViewsWithIds.put(R.id.txtTitleUser, 8);
        sViewsWithIds.put(R.id.txtTitleRoom, 9);
        sViewsWithIds.put(R.id.txtTitleDate, 10);
        sViewsWithIds.put(R.id.txtTitleMoney, 11);
        sViewsWithIds.put(R.id.txtTitleContent, 12);
        sViewsWithIds.put(R.id.txtDate, 13);
        sViewsWithIds.put(R.id.txtRoom, 14);
        sViewsWithIds.put(R.id.txtUserName, 15);
        sViewsWithIds.put(R.id.txtMoney, 16);
        sViewsWithIds.put(R.id.txtContent, 17);
        sViewsWithIds.put(R.id.frame_progress, 18);
        sViewsWithIds.put(R.id.linearLayoutNoidungDeNghi, 19);
        sViewsWithIds.put(R.id.txtTitleInfo, 20);
        sViewsWithIds.put(R.id.edtInfo, 21);
        sViewsWithIds.put(R.id.btnSend, 22);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ApproveDetailFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }
    private ApproveDetailFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.firstems.erp.helper.widgets.EMSButtonSecond) bindings[22]
            , (com.firstems.erp.helper.widgets.EMSEditText) bindings[21]
            , (android.widget.FrameLayout) bindings[18]
            , (android.view.View) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[4]
            , (androidx.cardview.widget.CardView) bindings[7]
            , (com.firstems.erp.databinding.LayoutOptionApprovedBinding) bindings[3]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[1]
            , (androidx.cardview.widget.CardView) bindings[19]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[15]
            );
        this.layoutTitlePheDuyet.setTag(null);
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
        layoutOption.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (layoutOption.hasPendingBindings()) {
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
        layoutOption.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeLayoutOption((com.firstems.erp.databinding.LayoutOptionApprovedBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeLayoutOption(com.firstems.erp.databinding.LayoutOptionApprovedBinding LayoutOption, int fieldId) {
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
        executeBindingsOn(layoutOption);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): layoutOption
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}