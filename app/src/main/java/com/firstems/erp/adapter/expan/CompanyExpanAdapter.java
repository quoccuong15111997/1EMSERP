package com.firstems.erp.adapter.expan;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.company.CompanyResponse;
import com.firstems.erp.api.model.response.location.LocationResponse;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/30/2020.
 */
public class CompanyExpanAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CompanyResponse> list;

    public CompanyExpanAdapter(Context context, List<CompanyResponse> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getLocationList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getLocationList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       CompanyResponse company = list.get(groupPosition);
        if (convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.item_company,null);
        }
        TextView txtName = convertView.findViewById(R.id.name);

        txtName.setText(company.getCompanyName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LocationResponse locate = list.get(groupPosition).getLocationList().get(childPosition);
        if (convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.item_location,null);
        }
        TextView txtName = convertView.findViewById(R.id.txtName);
        txtName.setText(locate.getLocationName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);

    }
}
