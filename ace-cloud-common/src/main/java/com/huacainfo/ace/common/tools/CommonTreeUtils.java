package com.huacainfo.ace.common.tools;

import com.huacainfo.ace.common.vo.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huacainfo
 */
public class CommonTreeUtils {


    Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<Map<String, Object>> resources;

    public CommonTreeUtils(List<Map<String, Object>> resources) {
        this.resources = resources;
    }

    public List<Tree> getTreeList(String id) {
        List<Tree> list = new ArrayList<>();
        List<Map<String, Object>> temp = this.getChildResourcesList(id);
        if (temp != null && temp.size() != 0) {
            Map<String, Object> row;
            for (int i = 0; i < temp.size(); i++) {
                row = temp.get(i);
                Tree tree = this.getChildTreeList(row);
                list.add(tree);
            }
        }
        return list;
    }

    public List<Tree> getTreeListCaseSelf(String id) {
        logger.info("getTreeListCaseSelf->", id);
        Map<String, Object> resources = null;
        for (Map<String, Object> temp : this.resources) {
            if (String.valueOf(temp.get("ID")).equals(id)) {
                resources = temp;
                break;
            }
        }
        if (resources == null) {
            return null;
        }
        Tree o = this.getChildTreeList(resources);

        List<Tree> list = new ArrayList<Tree>();
        List<Map<String, Object>> temp = this.getChildResourcesList(id);
        if (temp != null) {
            for (int i = 0; i < temp.size(); i++) {
                Map<String, Object> row = temp.get(i);
                Tree tree = this.getChildTreeList(row);
                list.add(tree);
            }
        }
        o.setChildren(isEmpty(list) ? null : list);
        List<Tree> rst = new ArrayList<>();
        rst.add(o);
        return rst;
    }

    public List<Map<String, Object>> getChildResourcesList(String id) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (this.resources != null) {
            Map<String, Object> row;
            for (int i = 0; i < this.resources.size(); i++) {
                row = this.resources.get(i);
                if (String.valueOf(row.get("PID")).equals(id)) {
                    list.add(row);
                }
            }
        }
        return list;
    }

    private Tree getChildTreeList(Map<String, Object> resources) {
        Tree tree = new Tree();
        tree.setIcon(String.valueOf(resources.get("ICON")));
        tree.setHref(String.valueOf(resources.get("HREF")));
        tree.setSrc(String.valueOf(resources.get("SRC")));
        tree.setId(String.valueOf(resources.get("ID")));
        tree.setText(String.valueOf(resources.get("TEXT")));
        tree.setIconCls(String.valueOf(resources.get("ICONCLS")));
        if (resources.get("STATE") == null) {
            tree.setState("open");
        } else {
            tree.setState(String.valueOf(resources.get("STATE")));
        }
        if (String.valueOf(resources.get("CHILD_COUNT")).equals("0")) {
            tree.setCls("file");
            tree.setLeaf(true);
            tree.setState("open");
        } else {
            tree.setCls("folder");
            tree.setLeaf(false);
            List<Tree> children = new ArrayList<Tree>();
            List<Map<String, Object>> list = this.getChildResourcesList(tree.getId());
            for (Map<String, Object> childResources : list) {
                children.add(getChildTreeList(childResources));

            }
            tree.setChildren(isEmpty(children) ? null : children);
        }
        return tree;
    }

    /**
     * 判断list集合是否为空
     *
     * @param list list集合
     * @return true->list为空；false->list有值
     */
    private boolean isEmpty(List list) {
        if (list == null || list.size() == 0) {
            return true;
        }

        return false;
    }
}
