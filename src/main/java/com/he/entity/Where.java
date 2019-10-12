package com.he.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author hesh
 * @date 2019/10/4
 * @des XML模版<condition>里的<where>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Where implements Serializable{

    /**
     * cd标签：单条件，动态添加
     */
    private List<Cd> cds;
}
