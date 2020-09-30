package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SwitchShiftInfoFragmentBindingImpl extends SwitchShiftInfoFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include3, 11);
        sViewsWithIds.put(R.id.linearLayout, 12);
        sViewsWithIds.put(R.id.linearLayout6, 13);
        sViewsWithIds.put(R.id.txtTitleDateBegin, 14);
        sViewsWithIds.put(R.id.layoutDateForm, 15);
        sViewsWithIds.put(R.id.imageView24, 16);
        sViewsWithIds.put(R.id.linearLayout7, 17);
        sViewsWithIds.put(R.id.txtTitleDateEnd, 18);
        sViewsWithIds.put(R.id.layoutDateEnd, 19);
        sViewsWithIds.put(R.id.imageView242, 20);
        sViewsWithIds.put(R.id.linearLayoutEmployee, 21);
        sViewsWithIds.put(R.id.txtTitleEmployee, 22);
        sViewsWithIds.put(R.id.linearLayoutNhanVienDuocChamCong, 23);
        sViewsWithIds.put(R.id.txtTitleNhanVienDuocChamCong, 24);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongSang, 25);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongSang, 26);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongChieu, 27);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongChieu, 28);
        sViewsWithIds.put(R.id.linearLayoutLoaiChamCongToi, 29);
        sViewsWithIds.put(R.id.txtTitleLoaiChamCongToi, 30);
        sViewsWithIds.put(R.id.materialRippleLayout, 31);
        sViewsWithIds.put(R.id.btnDone, 32);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener edtEmployeeandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of employDiLam.itemName
            //         is employDiLam.setItemName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtEmployee);
            // localize variables for thread safety
            // employDiLam.itemName
            java.lang.String employDiLamItemName = null;
            // employDiLam
            com.firstems.erp.api.model.response.employee.Employee employDiLam = mEmployDiLam;
            // employDiLam != null
            boolean employDiLamJavaLangObjectNull = false;



            employDiLamJavaLangObjectNull = (employDiLam) != (null);
            if (employDiLamJavaLangObjectNull) {




                employDiLam.setItemName(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener edtNhanVienDuocChamCongandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of employDuocChamCong.itemName
            //         is employDuocChamCong.setItemName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtNhanVienDuocChamCong);
            // localize variables for thread safety
            // employDuocChamCong.itemName
            java.lang.String employDuocChamCongItemName = null;
            // employDuocChamCong != null
            boolean employDuocChamCongJavaLangObjectNull = false;
            // employDuocChamCong
            com.firstems.erp.api.model.response.employee.Employee employDuocChamCong = mEmployDuocChamCong;



            employDuocChamCongJavaLangObjectNull = (employDuocChamCong) != (null);
            if (employDuocChamCongJavaLangObjectNull) {




                employDuocChamCong.setItemName(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public SwitchShiftInfoFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 33, sIncludes, sViewsWithIds));
    }
    private SwitchShiftInfoFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.Button) bindings[32]
            , (android.widget.CheckBox) bindings[7]
            , (android.widget.CheckBox) bindings[9]
            , (android.widget.CheckBox) bindings[5]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[4]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[20]
            , (android.view.View) bindings[11]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[19]
            , (com.firstems.erp.helper.widgets.EMSConstrainLayoutBorder) bindings[15]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[27]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[29]
            , (android.widget.LinearLayout) bindings[23]
            , (com.balysv.materialripple.MaterialRippleLayout) bindings[31]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[1]
            , (com.firstems.erp.helper.widgets.EMSTextviewHighLineNonBorder) bindings[2]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[24]
            );
        this.chkAfternoon.setTag(null);
        this.chkEverning.setTag(null);
        this.chkMorning.setTag(null);
        this.edtEmployee.setTag(null);
        this.edtLoaiChamCongChieu.setTag(null);
        this.edtLoaiChamCongSang.setTag(null);
        this.edtLoaiChamCongToi.setTag(null);
        this.edtNhanVienDuocChamCong.setTag(null);
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
                mDirtyFlags = 0x80L;
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
            setModel((com.firstems.erp.model.switchshift.SwitchShift) variable);
        }
        else if (BR.dateBegin == variableId) {
            setDateBegin((java.lang.String) variable);
        }
        else if (BR.employDiLam == variableId) {
            setEmployDiLam((com.firstems.erp.api.model.response.employee.Employee) variable);
        }
        else if (BR.dateEnd == variableId) {
            setDateEnd((java.lang.String) variable);
        }
        else if (BR.employDuocChamCong == variableId) {
            setEmployDuocChamCong((com.firstems.erp.api.model.response.employee.Employee) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setModel(@Nullable com.firstems.erp.model.switchshift.SwitchShift Model) {
        this.mModel = Model;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.model);
        super.requestRebind();
    }
    public void setDateBegin(@Nullable java.lang.String DateBegin) {
        this.mDateBegin = DateBegin;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.dateBegin);
        super.requestRebind();
    }
    public void setEmployDiLam(@Nullable com.firstems.erp.api.model.response.employee.Employee EmployDiLam) {
        updateRegistration(0, EmployDiLam);
        this.mEmployDiLam = EmployDiLam;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.employDiLam);
        super.requestRebind();
    }
    public void setDateEnd(@Nullable java.lang.String DateEnd) {
        this.mDateEnd = DateEnd;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.dateEnd);
        super.requestRebind();
    }
    public void setEmployDuocChamCong(@Nullable com.firstems.erp.api.model.response.employee.Employee EmployDuocChamCong) {
        updateRegistration(1, EmployDuocChamCong);
        this.mEmployDuocChamCong = EmployDuocChamCong;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.employDuocChamCong);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeEmployDiLam((com.firstems.erp.api.model.response.employee.Employee) object, fieldId);
            case 1 :
                return onChangeEmployDuocChamCong((com.firstems.erp.api.model.response.employee.Employee) object, fieldId);
        }
        return false;
    }
    private boolean onChangeEmployDiLam(com.firstems.erp.api.model.response.employee.Employee EmployDiLam, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
    private boolean onChangeEmployDuocChamCong(com.firstems.erp.api.model.response.employee.Employee EmployDuocChamCong, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.itemName) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
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
        com.firstems.erp.model.switchshift.SwitchShift model = mModel;
        java.lang.String modelContentAfternoonItemName = null;
        boolean modelMorning = false;
        java.lang.String employDuocChamCongItemName = null;
        java.lang.String dateBegin = mDateBegin;
        boolean modelEverning = false;
        boolean modelAfternoon = false;
        com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC modelContentAfternoon = null;
        com.firstems.erp.api.model.response.employee.Employee employDiLam = mEmployDiLam;
        java.lang.String dateEnd = mDateEnd;
        com.firstems.erp.api.model.response.employee.Employee employDuocChamCong = mEmployDuocChamCong;
        com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC modelContentEverning = null;
        java.lang.String modelContentMornigItemName = null;
        com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC modelContentMornig = null;
        java.lang.String employDiLamItemName = null;
        java.lang.String modelContentEverningItemName = null;

        if ((dirtyFlags & 0x84L) != 0) {



                if (model != null) {
                    // read model.morning
                    modelMorning = model.isMorning();
                    // read model.everning
                    modelEverning = model.isEverning();
                    // read model.afternoon
                    modelAfternoon = model.isAfternoon();
                    // read model.contentAfternoon
                    modelContentAfternoon = model.getContentAfternoon();
                    // read model.contentEverning
                    modelContentEverning = model.getContentEverning();
                    // read model.contentMornig
                    modelContentMornig = model.getContentMornig();
                }


                if (modelContentAfternoon != null) {
                    // read model.contentAfternoon.itemName
                    modelContentAfternoonItemName = modelContentAfternoon.getItemName();
                }
                if (modelContentEverning != null) {
                    // read model.contentEverning.itemName
                    modelContentEverningItemName = modelContentEverning.getItemName();
                }
                if (modelContentMornig != null) {
                    // read model.contentMornig.itemName
                    modelContentMornigItemName = modelContentMornig.getItemName();
                }
        }
        if ((dirtyFlags & 0x88L) != 0) {
        }
        if ((dirtyFlags & 0xa1L) != 0) {



                if (employDiLam != null) {
                    // read employDiLam.itemName
                    employDiLamItemName = employDiLam.getItemName();
                }
        }
        if ((dirtyFlags & 0x90L) != 0) {
        }
        if ((dirtyFlags & 0xc2L) != 0) {



                if (employDuocChamCong != null) {
                    // read employDuocChamCong.itemName
                    employDuocChamCongItemName = employDuocChamCong.getItemName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x84L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkAfternoon, modelAfternoon);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkEverning, modelEverning);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.chkMorning, modelMorning);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtLoaiChamCongChieu, modelContentAfternoonItemName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtLoaiChamCongSang, modelContentMornigItemName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtLoaiChamCongToi, modelContentEverningItemName);
        }
        if ((dirtyFlags & 0xa1L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtEmployee, employDiLamItemName);
        }
        if ((dirtyFlags & 0x80L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtEmployee, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtEmployeeandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtNhanVienDuocChamCong, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtNhanVienDuocChamCongandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0xc2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtNhanVienDuocChamCong, employDuocChamCongItemName);
        }
        if ((dirtyFlags & 0x88L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtDateBegin, dateBegin);
        }
        if ((dirtyFlags & 0x90L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtDateEnd, dateEnd);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): employDiLam
        flag 1 (0x2L): employDuocChamCong
        flag 2 (0x3L): model
        flag 3 (0x4L): dateBegin
        flag 4 (0x5L): dateEnd
        flag 5 (0x6L): employDiLam.itemName
        flag 6 (0x7L): employDuocChamCong.itemName
        flag 7 (0x8L): null
    flag mapping end*/
    //end
}