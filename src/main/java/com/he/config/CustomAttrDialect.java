package com.he.config;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hesh
 * @date 2019/10/4
 * @des 自定义thymeleaf属性方言
 */
public class CustomAttrDialect extends AbstractProcessorDialect {

    private static final String DIALECT_NAME = "custom";     // 方言名称
    private static final String PREFIX = "zdy";                 // 方言前缀,zdy(自定义) ，使用格式(zdy:*)
    public static final int PROCESSOR_PRECEDENCE = 1000;        // 方言优先级


    public CustomAttrDialect() {
        super(DIALECT_NAME, PREFIX, PROCESSOR_PRECEDENCE);
    }

    /*
     * 初始化方言处理器
     *
     */
    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new CustomAttrProcessor(dialectPrefix));//添加自定义的处理器
        return processors;
    }

}