package com.firstems.erp.callback;

import com.firstems.erp.model.FileIncludeModel;

/**
 * Created by Nguyen Quoc Cuong on 7/15/2020.
 */
public interface ItemFileClick{
    void onItemClick(FileIncludeModel fileIncludeModel);
    void onRemoveClick(int position);
}
