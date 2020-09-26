package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DocumentDetailFragmentBindingImpl extends DocumentDetailFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include27, 9);
        sViewsWithIds.put(R.id.txtTitleLoaiTaiLieu, 10);
        sViewsWithIds.put(R.id.txtTilteSoCongVan, 11);
        sViewsWithIds.put(R.id.txtTitleNgayPhatHanh, 12);
        sViewsWithIds.put(R.id.txtTitleNoiDungChinh, 13);
        sViewsWithIds.put(R.id.txtTitleNoiDungChiTiet, 14);
        sViewsWithIds.put(R.id.txtTitleNoiPhatHanh, 15);
        sViewsWithIds.put(R.id.txtTitleTrangThai, 16);
        sViewsWithIds.put(R.id.frame_file_include, 17);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final android.widget.TextView mboundView6;
    @NonNull
    private final android.widget.TextView mboundView7;
    @NonNull
    private final android.widget.TextView mboundView8;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DocumentDetailFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }
    private DocumentDetailFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.FrameLayout) bindings[17]
            , (android.view.View) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[16]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.txtDocumentName.setTag(null);
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
        if (BR.item == variableId) {
            setItem((com.firstems.erp.api.model.response.document.DocumentItemApiResponse) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.firstems.erp.api.model.response.document.DocumentItemApiResponse Item) {
        updateRegistration(0, Item);
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItem((com.firstems.erp.api.model.response.document.DocumentItemApiResponse) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem(com.firstems.erp.api.model.response.document.DocumentItemApiResponse Item, int fieldId) {
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
        java.lang.String itemCNTNBRIF = null;
        com.firstems.erp.api.model.response.document.DocumentItemApiResponse item = mItem;
        java.lang.String itemDCTPNAME = null;
        java.lang.String itemSTTENAME = null;
        java.lang.String itemCNTNDCMN = null;
        java.lang.String itemMAINNUMB = null;
        java.lang.String itemBegDateFormat = null;
        java.lang.String itemPBLSNAME = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.cNTNBRIF
                    itemCNTNBRIF = item.cNTNBRIF;
                    // read item.dCTPNAME
                    itemDCTPNAME = item.dCTPNAME;
                    // read item.sTTENAME
                    itemSTTENAME = item.sTTENAME;
                    // read item.cNTNDCMN
                    itemCNTNDCMN = item.cNTNDCMN;
                    // read item.mAINNUMB
                    itemMAINNUMB = item.mAINNUMB;
                    // read item.begDateFormat
                    itemBegDateFormat = item.getBegDateFormat();
                    // read item.pBLSNAME
                    itemPBLSNAME = item.pBLSNAME;
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, itemDCTPNAME);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, itemMAINNUMB);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, itemBegDateFormat);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, itemCNTNBRIF);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, itemCNTNDCMN);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, itemPBLSNAME);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView8, itemSTTENAME);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtDocumentName, itemCNTNBRIF);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}