package com.he.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author hesh
 * @date 2019/10/4
 * @des XML模版<condition>里的<select>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Select implements Serializable{

    /**
     * 表示单个或多个字段，动态添加
     */
    private List<Column> columns;
}
