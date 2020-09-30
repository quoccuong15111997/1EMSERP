package com.firstems.erp.databinding;
import com.firstems.erp.R;
import com.firstems.erp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ConfigFragmentBindingImpl extends ConfigFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include11, 19);
        sViewsWithIds.put(R.id.nested_scroll_view, 20);
        sViewsWithIds.put(R.id.lParentContent, 21);
        sViewsWithIds.put(R.id.spinerLang, 22);
        sViewsWithIds.put(R.id.switchInfoSignature, 23);
        sViewsWithIds.put(R.id.switchWaitSignature, 24);
        sViewsWithIds.put(R.id.switchWaitApproved, 25);
        sViewsWithIds.put(R.id.switchCompleteSignature, 26);
        sViewsWithIds.put(R.id.switchInfoApproved, 27);
        sViewsWithIds.put(R.id.switchResquestFormPart, 28);
        sViewsWithIds.put(R.id.switchResquestFormCongKhoan, 29);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView10;
    @NonNull
    private final android.widget.TextView mboundView11;
    @NonNull
    private final android.widget.TextView mboundView13;
    @NonNull
    private final android.widget.TextView mboundView15;
    @NonNull
    private final android.widget.TextView mboundView16;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final android.widget.TextView mboundView7;
    @NonNull
    private final android.widget.TextView mboundView9;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener edtDomainandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of ui.edtDomain
            //         is ui.setEdtDomain((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtDomain);
            // localize variables for thread safety
            // ui != null
            boolean uiJavaLangObjectNull = false;
            // ui
            com.firstems.erp.viewmodel.config.ConfigUIBindingModel ui = mUi;
            // ui.edtDomain
            java.lang.String uiEdtDomain = null;



            uiJavaLangObjectNull = (ui) != (null);
            if (uiJavaLangObjectNull) {




                ui.setEdtDomain(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener edtNumberDayandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of ui.edtNumberDay
            //         is ui.setEdtNumberDay((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtNumberDay);
            // localize variables for thread safety
            // ui != null
            boolean uiJavaLangObjectNull = false;
            // ui
            com.firstems.erp.viewmodel.config.ConfigUIBindingModel ui = mUi;
            // ui.edtNumberDay
            java.lang.String uiEdtNumberDay = null;



            uiJavaLangObjectNull = (ui) != (null);
            if (uiJavaLangObjectNull) {




                ui.setEdtNumberDay(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public ConfigFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 30, sIncludes, sViewsWithIds));
    }
    private ConfigFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[18]
            , (android.widget.Button) bindings[4]
            , (android.widget.Button) bindings[17]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[8]
            , (android.view.View) bindings[19]
            , (android.widget.LinearLayout) bindings[21]
            , (androidx.core.widget.NestedScrollView) bindings[20]
            , (android.widget.Spinner) bindings[22]
            , (androidx.appcompat.widget.SwitchCompat) bindings[26]
            , (androidx.appcompat.widget.SwitchCompat) bindings[27]
            , (androidx.appcompat.widget.SwitchCompat) bindings[23]
            , (androidx.appcompat.widget.SwitchCompat) bindings[29]
            , (androidx.appcompat.widget.SwitchCompat) bindings[28]
            , (androidx.appcompat.widget.SwitchCompat) bindings[25]
            , (androidx.appcompat.widget.SwitchCompat) bindings[24]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.btnCancel.setTag(null);
        this.btnDefault.setTag(null);
        this.btnSave.setTag(null);
        this.edtDomain.setTag(null);
        this.edtNumberDay.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (android.widget.TextView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (android.widget.TextView) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView13 = (android.widget.TextView) bindings[13];
        this.mboundView13.setTag(null);
        this.mboundView15 = (android.widget.TextView) bindings[15];
        this.mboundView15.setTag(null);
        this.mboundView16 = (android.widget.TextView) bindings[16];
        this.mboundView16.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView7 = (android.widget.TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView9 = (android.widget.TextView) bindings[9];
        this.mboundView9.setTag(null);
        this.txtTitlSettingSignature.setTag(null);
        this.txtTitleApprove.setTag(null);
        this.txtTitleAskPermistion.setTag(null);
        this.txtTitleDomain.setTag(null);
        this.txtTitleSystemSetting.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80000L;
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
        if (BR.ui == variableId) {
            setUi((com.firstems.erp.viewmodel.config.ConfigUIBindingModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUi(@Nullable com.firstems.erp.viewmodel.config.ConfigUIBindingModel Ui) {
        updateRegistration(0, Ui);
        this.mUi = Ui;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.ui);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUi((com.firstems.erp.viewmodel.config.ConfigUIBindingModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUi(com.firstems.erp.viewmodel.config.ConfigUIBindingModel Ui, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.titleSystemSetting) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.titleDomain) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.edtDomain) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.buttonDefault) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.titleLanguage) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        else if (fieldId == BR.titleSignatureSetting) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        else if (fieldId == BR.titleNumberDay) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        else if (fieldId == BR.edtNumberDay) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        else if (fieldId == BR.switchInfoSignature) {
            synchronized(this) {
                    mDirtyFlags |= 0x200L;
            }
            return true;
        }
        else if (fieldId == BR.switchUnSignature) {
            synchronized(this) {
                    mDirtyFlags |= 0x400L;
            }
            return true;
        }
        else if (fieldId == BR.switchDone) {
            synchronized(this) {
                    mDirtyFlags |= 0x800L;
            }
            return true;
        }
        else if (fieldId == BR.titleApproveSetiing) {
            synchronized(this) {
                    mDirtyFlags |= 0x1000L;
            }
            return true;
        }
        else if (fieldId == BR.switchInfoApprove) {
            synchronized(this) {
                    mDirtyFlags |= 0x2000L;
            }
            return true;
        }
        else if (fieldId == BR.titleAskPermistion) {
            synchronized(this) {
                    mDirtyFlags |= 0x4000L;
            }
            return true;
        }
        else if (fieldId == BR.switchBySession) {
            synchronized(this) {
                    mDirtyFlags |= 0x8000L;
            }
            return true;
        }
        else if (fieldId == BR.switchWork) {
            synchronized(this) {
                    mDirtyFlags |= 0x10000L;
            }
            return true;
        }
        else if (fieldId == BR.buttonSave) {
            synchronized(this) {
                    mDirtyFlags |= 0x20000L;
            }
            return true;
        }
        else if (fieldId == BR.buttonCancel) {
            synchronized(this) {
                    mDirtyFlags |= 0x40000L;
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
        java.lang.String uiSwitchInfoApprove = null;
        java.lang.String uiTitleAskPermistion = null;
        java.lang.String uiSwitchInfoSignature = null;
        java.lang.String uiSwitchUnSignature = null;
        com.firstems.erp.viewmodel.config.ConfigUIBindingModel ui = mUi;
        java.lang.String uiButtonDefault = null;
        java.lang.String uiTitleLanguage = null;
        java.lang.String uiButtonCancel = null;
        java.lang.String uiSwitchDone = null;
        java.lang.String uiTitleApproveSetiing = null;
        java.lang.String uiSwitchWork = null;
        java.lang.String uiTitleNumberDay = null;
        java.lang.String uiEdtDomain = null;
        java.lang.String uiEdtNumberDay = null;
        java.lang.String uiTitleSystemSetting = null;
        java.lang.String uiTitleDomain = null;
        java.lang.String uiSwitchBySession = null;
        java.lang.String uiTitleSignatureSetting = null;
        java.lang.String uiButtonSave = null;

        if ((dirtyFlags & 0xfffffL) != 0) {


            if ((dirtyFlags & 0x82001L) != 0) {

                    if (ui != null) {
                        // read ui.switchInfoApprove
                        uiSwitchInfoApprove = ui.getSwitchInfoApprove();
                    }
            }
            if ((dirtyFlags & 0x84001L) != 0) {

                    if (ui != null) {
                        // read ui.titleAskPermistion
                        uiTitleAskPermistion = ui.getTitleAskPermistion();
                    }
            }
            if ((dirtyFlags & 0x80201L) != 0) {

                    if (ui != null) {
                        // read ui.switchInfoSignature
                        uiSwitchInfoSignature = ui.getSwitchInfoSignature();
                    }
            }
            if ((dirtyFlags & 0x80401L) != 0) {

                    if (ui != null) {
                        // read ui.switchUnSignature
                        uiSwitchUnSignature = ui.getSwitchUnSignature();
                    }
            }
            if ((dirtyFlags & 0x80011L) != 0) {

                    if (ui != null) {
                        // read ui.buttonDefault
                        uiButtonDefault = ui.getButtonDefault();
                    }
            }
            if ((dirtyFlags & 0x80021L) != 0) {

                    if (ui != null) {
                        // read ui.titleLanguage
                        uiTitleLanguage = ui.getTitleLanguage();
                    }
            }
            if ((dirtyFlags & 0xc0001L) != 0) {

                    if (ui != null) {
                        // read ui.buttonCancel
                        uiButtonCancel = ui.getButtonCancel();
                    }
            }
            if ((dirtyFlags & 0x80801L) != 0) {

                    if (ui != null) {
                        // read ui.switchDone
                        uiSwitchDone = ui.getSwitchDone();
                    }
            }
            if ((dirtyFlags & 0x81001L) != 0) {

                    if (ui != null) {
                        // read ui.titleApproveSetiing
                        uiTitleApproveSetiing = ui.getTitleApproveSetiing();
                    }
            }
            if ((dirtyFlags & 0x90001L) != 0) {

                    if (ui != null) {
                        // read ui.switchWork
                        uiSwitchWork = ui.getSwitchWork();
                    }
            }
            if ((dirtyFlags & 0x80081L) != 0) {

                    if (ui != null) {
                        // read ui.titleNumberDay
                        uiTitleNumberDay = ui.getTitleNumberDay();
                    }
            }
            if ((dirtyFlags & 0x80009L) != 0) {

                    if (ui != null) {
                        // read ui.edtDomain
                        uiEdtDomain = ui.getEdtDomain();
                    }
            }
            if ((dirtyFlags & 0x80101L) != 0) {

                    if (ui != null) {
                        // read ui.edtNumberDay
                        uiEdtNumberDay = ui.getEdtNumberDay();
                    }
            }
            if ((dirtyFlags & 0x80003L) != 0) {

                    if (ui != null) {
                        // read ui.titleSystemSetting
                        uiTitleSystemSetting = ui.getTitleSystemSetting();
                    }
            }
            if ((dirtyFlags & 0x80005L) != 0) {

                    if (ui != null) {
                        // read ui.titleDomain
                        uiTitleDomain = ui.getTitleDomain();
                    }
            }
            if ((dirtyFlags & 0x88001L) != 0) {

                    if (ui != null) {
                        // read ui.switchBySession
                        uiSwitchBySession = ui.getSwitchBySession();
                    }
            }
            if ((dirtyFlags & 0x80041L) != 0) {

                    if (ui != null) {
                        // read ui.titleSignatureSetting
                        uiTitleSignatureSetting = ui.getTitleSignatureSetting();
                    }
            }
            if ((dirtyFlags & 0xa0001L) != 0) {

                    if (ui != null) {
                        // read ui.buttonSave
                        uiButtonSave = ui.getButtonSave();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xc0001L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnCancel, uiButtonCancel);
        }
        if ((dirtyFlags & 0x80011L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnDefault, uiButtonDefault);
        }
        if ((dirtyFlags & 0xa0001L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.btnSave, uiButtonSave);
        }
        if ((dirtyFlags & 0x80009L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtDomain, uiEdtDomain);
        }
        if ((dirtyFlags & 0x80000L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtDomain, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtDomainandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtNumberDay, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtNumberDayandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x80101L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtNumberDay, uiEdtNumberDay);
        }
        if ((dirtyFlags & 0x80401L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView10, uiSwitchUnSignature);
        }
        if ((dirtyFlags & 0x80801L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView11, uiSwitchDone);
        }
        if ((dirtyFlags & 0x82001L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView13, uiSwitchInfoApprove);
        }
        if ((dirtyFlags & 0x88001L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView15, uiSwitchBySession);
        }
        if ((dirtyFlags & 0x90001L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView16, uiSwitchWork);
        }
        if ((dirtyFlags & 0x80021L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, uiTitleLanguage);
        }
        if ((dirtyFlags & 0x80081L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, uiTitleNumberDay);
        }
        if ((dirtyFlags & 0x80201L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView9, uiSwitchInfoSignature);
        }
        if ((dirtyFlags & 0x80041L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtTitlSettingSignature, uiTitleSignatureSetting);
        }
        if ((dirtyFlags & 0x81001L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtTitleApprove, uiTitleApproveSetiing);
        }
        if ((dirtyFlags & 0x84001L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtTitleAskPermistion, uiTitleAskPermistion);
        }
        if ((dirtyFlags & 0x80005L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtTitleDomain, uiTitleDomain);
        }
        if ((dirtyFlags & 0x80003L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.txtTitleSystemSetting, uiTitleSystemSetting);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): ui
        flag 1 (0x2L): ui.titleSystemSetting
        flag 2 (0x3L): ui.titleDomain
        flag 3 (0x4L): ui.edtDomain
        flag 4 (0x5L): ui.buttonDefault
        flag 5 (0x6L): ui.titleLanguage
        flag 6 (0x7L): ui.titleSignatureSetting
        flag 7 (0x8L): ui.titleNumberDay
        flag 8 (0x9L): ui.edtNumberDay
        flag 9 (0xaL): ui.switchInfoSignature
        flag 10 (0xbL): ui.switchUnSignature
        flag 11 (0xcL): ui.switchDone
        flag 12 (0xdL): ui.titleApproveSetiing
        flag 13 (0xeL): ui.switchInfoApprove
        flag 14 (0xfL): ui.titleAskPermistion
        flag 15 (0x10L): ui.switchBySession
        flag 16 (0x11L): ui.switchWork
        flag 17 (0x12L): ui.buttonSave
        flag 18 (0x13L): ui.buttonCancel
        flag 19 (0x14L): null
    flag mapping end*/
    //end
}