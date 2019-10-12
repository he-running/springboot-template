package com.he.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hesh
 * @date 2019/10/4
 * @des XML模版<condition>标签
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condition implements Serializable {

    /**
     * 查询字段,非空
     */
    private Select select = new Select();

    /**
     * 查询条件,可为空
     */
    private Where where;
}
