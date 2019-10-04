package org.e3.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author xujin
 * @package-name org.e3.common.pojo
 * @createtime 2019-10-04 17:57
 */

public class EasyUIDataGridResult implements Serializable {
    private Integer total;

    private List rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
