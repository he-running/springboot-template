package com.he.controller;

import com.he.entity.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.*;


/**
 * @author hesh
 * @date 2019/10/4
 * @des thymeleaf模版
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    SpringTemplateEngine springTemplateEngine;

    @ApiOperation(value = "Thymeleaf模版的XML模式", notes = "XML模版")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastname", value = "姓氏"),
            @ApiImplicitParam(name = "firstname", value = "名字"),
            @ApiImplicitParam(name = "country", value = "国籍")
    })
    @GetMapping(value = "/test1", produces = {MediaType.APPLICATION_XML_VALUE})//produces改为XML
    public String test1(@RequestParam String lastname, @RequestParam String firstname, @RequestParam String country) {
        Map<String, String> pinfo = new HashMap<>();
        Context context = new Context();
        context.setVariable("pinfo", pinfo);
        pinfo.put("lastname", lastname);
        pinfo.put("firstname", firstname);
        pinfo.put("country", country);

        log.info("---pinfo:{}", pinfo);
        String content = springTemplateEngine.process("person-test", context);
        log.info("---xml:\n{}", content);
        return content;
    }

    @ApiOperation(value = "Thymeleaf动态修改XML", notes = "可指定参数动态修改XML")
    @GetMapping(value = "/test2", produces = {MediaType.APPLICATION_XML_VALUE})//produces改为XML
    public String test2() {
        Context context = new Context();
        //<condition>标签
        Condition condition = new Condition();
        condition = testSql();
        context.setVariable("condition", condition);

        String content = springTemplateEngine.process("xml-protocol", context);
        log.info("Thymeleaf dynamic---xml:\n{}", content);
        return content;
    }

    /**
     * ‘select count(c1) as xxx,c2 from tb where ...’
     *
     * @return Condition
     */
    private Condition testSql() {
        Condition condition = new Condition();

        //select字段
        List<Column> columns = new ArrayList<>();
        Column c1 = new Column();
        c1.setFunc("count(id)");
        c1.setNickname("id_count");

        Column c2 = new Column();
        c2.setName("id");

        columns.add(c1);
        columns.add(c2);
        condition.getSelect().setColumns(columns);

        //where条件
        List<Cd> cds = new ArrayList<>();
        Cd cd1 = new Cd();
        cd1.setColumn("name");
        cd1.setCompare("=");
        cd1.setValue("hehe");

        Cd cd2 = new Cd();
        cd2.setColumn("age");
        cd2.setCompare("=");
        cd2.setValue("18");
        cd2.setRelation("and");

        cds.add(cd1);
        cds.add(cd2);
        Where where = new Where(cds);
        condition.setWhere(where);

        return condition;
    }

}
