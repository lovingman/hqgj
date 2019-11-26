package com.huacainfo.ace.common.tools;

import com.huacainfo.ace.common.vo.CheckTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CKTreeUtils {

    private List<Map<String, Object>> resources;


    public CKTreeUtils(List<Map<String, Object>> resources) {
        this.resources = resources;
    }

    public List<CheckTree> getCheckTreeList(String id) {
        List<CheckTree> list = new ArrayList<CheckTree>();
        List<Map<String, Object>> temp = this.getChildResourcesList(id);
        if (temp != null) {
            for (int i = 0; i < temp.size(); i++) {
                Map<String, Object> row = temp.get(i);
                CheckTree tree = this.getChildCheckTreeList(row);
                list.add(tree);
            }
        }
        return list;
    }

    public List<Map<String, Object>> getChildResourcesList(String id) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (this.resources != null) {
            for (int i = 0; i < this.resources.size(); i++) {
                Map<String, Object> row = (Map<String, Object>) this.resources.get(i);
                if (String.valueOf(row.get("PID")).equals(id)) {
                    list.add(row);
                }
            }
        }
        return list;
    }

    private CheckTree getChildCheckTreeList(Map<String, Object> resources) {
        CheckTree tree = new CheckTree();
        tree.setIcon(String.valueOf(resources.get("ICON")));
        tree.setId(String.valueOf(resources.get("ID")));
        tree.setText(String.valueOf(resources.get("TEXT")));
        tree.setChecked(Boolean.valueOf(String.valueOf(resources.get("CHECKED"))));
        if (String.valueOf(resources.get("CHILD_COUNT")).equals("0")) {
            tree.setCls("file");
            tree.setLeaf(true);
        } else {
            tree.setCls("folder");
            tree.setLeaf(false);
            if (tree.getChecked()) {
                tree.setChecked(null);
            }
            List<CheckTree> children = new ArrayList<CheckTree>();
            List<Map<String, Object>> list = this.getChildResourcesList(tree.getId());
            for (Map<String, Object> childResources : list) {
                children.add(getChildCheckTreeList(childResources));

            }
            tree.setChildren(children);
        }
        return tree;
    }
}
