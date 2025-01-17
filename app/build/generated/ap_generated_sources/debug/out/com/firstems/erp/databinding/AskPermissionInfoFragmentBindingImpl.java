package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AskPermissionInfoFragmentBindingImpl extends AskPermissionInfoFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include3, 10);
        sViewsWithIds.put(R.id.linearLayout, 11);
        sViewsWithIds.put(R.id.linearLayout6, 12);
        sViewsWithIds.put(R.id.txtTitleDateBegin, 13);
        sViewsWithIds.put(R.id.layoutDateForm, 14);
        sViewsWithIds.put(R.id.imageView24, 15);
        sViewsWithIds.put(R.id.linearLayout7, 16);
        sViewsWithIds.put(R.id.txtTitleDateEnd, 17);
        sViewsWithIds.put(R.id.layoutDateEnd, 18);
        sViewsWithIds.put(R.id.imageView242, 19);
        sViewsWithIds.put(R.id.linearLayoutEmployee, 20);
        sViewsWithIds.put(R.id.txtTitleEmployee, 21);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongSang, 22);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongSang, 23);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongChieu, 24);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongChieu, 25);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongToi, 26);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongToi, 27);
        sViewsWithIds.put(R.id.materialRippleLayout, 28);
        sViewsWithIds.put(R.id.btnDone, 29);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public AskPermissionInfoFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 30, sIncludes, sViewsWithIds));
    }
    private AskPermissionInfoFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.Button) bindings[29]
            , (android.widget.CheckBox) bindings[6]
            , (android.widget.CheckBox) bindings[8]
            , (android.widget.CheckBox) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[9]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.ImageView) bindings[19]
            , (android.view.View) bindings[10]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[18]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[14]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[26]
            , (com.balysv.materialripple.MaterialRippleLayout) bindings[28]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[1]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[2]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[27]
            );
        this.chkAfternoon.setTag(null);
        this.chkEverning.setTag(null);
        this.chkMorning.setTag(null);
        this.edtEmployee.setTag(null);
        this.edtLoaiChamCongChieu.setTag(null);
        this.edtLoaiChamCongSang.setTag(null);
        this.edtLoaiChamCongToi.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtDateBegin.setTag(null);
        this.txtDateEnd.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
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
        if (BR.model == variableId) {
            setModel((com.firstems.erp.model.approved.Approved) variable);
        }
        else if (BR.dateEnd == variableId) {
            setDateEnd((java.lang.String) variable);
        }
        else if (BR.employDuocChamCong == variableId) {
            setEmployDuocChamCong((com.firstems.erp.api.model.response.employee.Employee) variable);
        }
        else if (BR.dateBegin == variableId) {
            setDateBegin((java.lang.String) variable);
        }
        else if (BR.employDiLam == variableId) {
            setEmployDiLam((com.firstems.erp.api.model.response.employee.Employee) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setModel(@Nullable com.firstems.erp.model.approved.Approved Model) {
        this.mModel = Model;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.model);
        super.requestRebind();
    }
    public void setDateEnd(@Nullable java.lang.String DateEnd) {
        this.mDateEnd = DateEnd;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.dateEnd);
        super.requestRebind();
    }
    public void setEmployDuocChamCong(@Nullable com.firstems.erp.api.model.response.employee.Employee EmployDuocChamCong) {
        this.mEmployDuocChamCong = EmployDuocChamCong;
    }
    public void setDateBegin(@Nullable java.lang.String DateBegin) {
        this.mDateBegin = DateBegin;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.dateBegin);
        super.requestRebind();
    }
    public void setEmployDiLam(@Nullable com.firstems.erp.api.model.response.employee.Employee EmployDiLam) {
        updateRegistration(1, EmployDiLam);
        this.mEmployDiLam = EmployDiLam;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.employDiLam);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeEmployDuocChamCong((com.firstems.erp.api.model.response.employee.Employee) object, fieldId);
            case 1 :
                return onChangeEmployDiLam((com.firstems.erp.api.model.response.employee.Employee) object, fieldId);
        }
        return false;
    }
    private boolean onChangeEmployDuocChamCong(com.firstems.erp.api.model.response.employee.Employee EmployDuocChamCong, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeEmployDiLam(com.firstems.erp.api.model.response.employee.Employee EmployDiLam, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.itemName) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
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
        com.firstems.erp.model.approved.Approved model = mModel;
        java.lang.String dateEnd = mDateEnd;
        java.lang.String modelContentAfternoonItemName = null;
        boolean modelMorning = false;
        com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC modelContentEverning = null;
        java.lang.String dateBegin = mDateBegin;
        boolean modelEverning = false;
        java.lang.String modelContentMornigItemName = null;
        boolean modelAfternoon = false;
        com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC modelContentAfternoon = null;
        com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC modelContentMornig = null;
        java.lang.String employDiLamItemName = null;
        java.lang.String modelContentEverningItemName = null;
        com.firstems.erp.api.model.response.employee.Employee employDiLam = mEmployDiLam;

        if ((dirtyFlags & 0x44L) != 0) {



                if (model != null) {
                    // read model.morning
                    modelMorning = model.isMorning();
                    // read model.contentEverning
                    modelContentEverning = model.getContentEverning();
                    // read model.everning
                    modelEverning = model.isEverning();
                    // read model.afternoon
                    modelAfternoon = model.isAfternoon();
                    // read model.contentAfternoon
                    modelContentAfternoon = model.getContentAfternoon();
                    // read model.contentMornig
                    modelContentMornig = model.getContentMornig();
                }


                if (modelContentEverning != null) {
                    // read model.contentEverning.itemName
                    modelContentEverningItemName = modelContentEverning.getItemName();
                }
                if (modelContentAfternoon != null) {
                    // read model.contentAfternoon.itemName
                    modelContentAfternoonItemName = modelContentAfternoon.getItemName();
                }
                if (modelContentMornig != null) {
                    // read model.contentMornig.itemName
                    modelContentMornigItemName = modelContentMornig.getItemName();
                }
        }
        if ((dirtyFlags & 0x48L) != 0) {
        }
        if ((dirtyFlags & 0x50L) != 0) {
        }
        if ((dirtyFlags & 0x62L) != 0) {



                if (employDiLam != null) {
                    // read employDiLam.itemName
                    employDiLamItemName = employDiLam.getItemName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x44L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkAfternoon, modelAfternoon);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkEverning, modelEverning);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkMorning, modelMorning);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtLoaiChamCongChieu, modelContentAfternoonItemName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtLoaiChamCongSang, modelContentMornigItemName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtLoaiChamCongToi, modelContentEverningItemName);
        }
        if ((dirtyFlags & 0x62L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtEmployee, employDiLamItemName);
        }
        if ((dirtyFlags & 0x50L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtDateBegin, dateBegin);
        }
        if ((dirtyFlags & 0x48L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtDateEnd, dateEnd);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): employDuocChamCong
        flag 1 (0x2L): employDiLam
        flag 2 (0x3L): model
        flag 3 (0x4L): dateEnd
        flag 4 (0x5L): dateBegin
        flag 5 (0x6L): employDiLam.itemName
        flag 6 (0x7L): null
    flag mapping end*/
    //end
}