package com.he.config;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * @author hesh
 * @date 2019/10/4
 * @des 自定义thymeleaf属性处理器
 */
public class CustomAttrProcessor extends AbstractAttributeTagProcessor {

    private static final String ATTR_NAME = "attr";//自定义属性名，即(:)后面的名称，与前缀组合后是（zdy:attr）
    private static final int PRECEDENCE = 10000;//优先级


    public CustomAttrProcessor(final String dialectPrefix) {
        super(
                TemplateMode.XML,               // This processor will apply only to XML mode
                dialectPrefix,                  // Prefix to be applied to name for matching
                null,              // No tag name: match any tag name
                false,        // No prefix to be applied to tag name
                ATTR_NAME,                      // Name of the attribute that will be matched
                true,        // Apply dialect prefix to attribute name
                PRECEDENCE,                     // Precedence (inside dialect's precedence)
                true);          // Remove the matched attribute afterwards
    }


    @Override
    protected void doProcess(
            final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {

        /*
         * In order to evaluate the attribute value as a Thymeleaf Standard Expression,
         * we first obtain the parser, then use it for parsing the attribute value into
         * an expression object, and finally execute this expression object.
         */
        final IEngineConfiguration configuration = context.getConfiguration();

        /*
         * Obtain the Thymeleaf Standard Expression parser
         */
        final IStandardExpressionParser parser =
                StandardExpressions.getExpressionParser(configuration);

        /**
         * 根据“;”拆分属性组合(同一属性不允许多次出现在同一element中，所以使用‘;’进行属性组合)
         */
        System.out.println("--自定义thymeleaf属性值: " + attributeValue);
        String[] attrArray = attributeValue.split(";");

        if (attrArray != null && attrArray.length > 0) {
            //遍历每个属性，设置单个属性值
            for (int i = 0; i < attrArray.length; i++) {
                //解析单个属性
                String attr = attrArray[i];
                /**
                 * Parse the attribute value as a Thymeleaf Standard Expression
                 */
                final IStandardExpression expression =
                        parser.parseExpression(context, attr);

                /**
                 * Execute the expression just parsed
                 */
                final String tagAttr = (String) expression.execute(context);

                //根据“=”拆分属性name和value
                String[] tagAttrArray = tagAttr.split("=");
                String attrName = tagAttrArray[0];
                String attrValue = "";
                if (tagAttrArray.length > 1) {
                    attrValue = tagAttrArray[1];
                }
                //设置属性
                structureHandler.setAttribute(attrName, attrValue);
            }
        }
    }
}
