package com.firstems.erp.adapter.signature;import androidx.annotation.Nullable;import androidx.recyclerview.widget.DiffUtil;import androidx.recyclerview.widget.ListUpdateCallback;import com.firstems.erp.adapter.diff.signature.SignatureDiffUtilCallBack;import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;import java.io.Serializable;import java.util.List;public class SignatureModel implements Serializable,Comparable {    private String dcmnCode;    private List<SignatureItemApiResponse> signatureItemApiResponseList;        public SignatureModel(String dcmnCode, List<SignatureItemApiResponse> signatureItemApiResponseList) {        this.dcmnCode = dcmnCode;        this.signatureItemApiResponseList = signatureItemApiResponseList;    }        public SignatureModel() {    }        public String getDcmnCode() {        return dcmnCode;    }        public void setDcmnCode(String dcmnCode) {        this.dcmnCode = dcmnCode;    }        public List<SignatureItemApiResponse> getSignatureItemApiResponseList() {        return signatureItemApiResponseList;    }        public void setSignatureItemApiResponseList(List<SignatureItemApiResponse> signatureItemApiResponseList) {        this.signatureItemApiResponseList = signatureItemApiResponseList;    }        @Override    public int compareTo(Object o) {                SignatureModel  model = (SignatureModel) o;            final int[] flag = {0};                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SignatureDiffUtilCallBack(model.getSignatureItemApiResponseList(),this.signatureItemApiResponseList));        diffResult.dispatchUpdatesTo(new ListUpdateCallback() {            @Override            public void onInserted(int position, int count) {                    }                @Override            public void onRemoved(int position, int count) {                    }                @Override            public void onMoved(int fromPosition, int toPosition) {                    }                @Override            public void onChanged(int position, int count, @Nullable Object payload) {                flag[0] = 1;            }        });        return flag[0];    }}