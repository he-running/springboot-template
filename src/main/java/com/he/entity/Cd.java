package com.he.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hesh
 * @date 2019/10/4
 * @des XML模版<condition>里的<where>里的cd标签
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cd implements Serializable {


    private String func = "";


    private String column = "";


    private String compare = "";


    private String value = "";


    private String relation = "";
}
