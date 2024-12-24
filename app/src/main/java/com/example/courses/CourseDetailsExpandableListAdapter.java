package com.example.courses;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
public class CourseDetailsExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> groupList; // The groups (e.g., course modules)
    private List<List<String>> childList; // The children (e.g., topics within each module)

    public CourseDetailsExpandableListAdapter(Context context, List<String> groupList, List<List<String>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size(); // Number of groups
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size(); // Number of children in the group
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition); // Group item
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition); // Child item
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        }

        TextView groupText = convertView.findViewById(android.R.id.text1);
        groupText.setText(groupList.get(groupPosition)); // Set the group title
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView childText = convertView.findViewById(android.R.id.text1);
        childText.setText(childList.get(groupPosition).get(childPosition)); // Set the child content
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
