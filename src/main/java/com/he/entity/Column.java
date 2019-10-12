package com.he.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hesh
 * @date 2019/10/4
 * @des XML模版<condition>里的<select>里的<column>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Column implements Serializable {

    //<column func="count($COLUMN)" name=" c3" nickname=" C3" comments=" 字段3"/>

    private String func = "";

    private String name = "";

    private String nickname = "";

    private String comments = "";

}
