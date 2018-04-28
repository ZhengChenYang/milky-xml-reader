import org.springframework.context.annotation.Bean;
import org.w3c.dom.Element;

/**
 * Created by lenovo on 2018/4/28.
 */
public class BeanDefinitionParserDelegate {
    public static final String BEANS_NAMESPACE_URI = "http://www.milky.org/schema/beans";

    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";

    public boolean isDefaultNamespace(Element ele) {
        return ele.isDefaultNamespace(BEANS_NAMESPACE_URI);
    }

    public void parseBeanDefinitionElement(Element ele) {
        String id = ele.getAttribute(ID_ATTRIBUTE);

        String className = null;
        BeanDefinition bd = new BeanDefinition();
        //解析class属性
        if(ele.hasAttribute(CLASS_ATTRIBUTE)){
            className = ele.getAttribute(ID_ATTRIBUTE);
        }

        // set id in beanDefinition
        // set className in beanDefinition

        //解析构造函数参数
        parseConstructorArgElements(ele, bd);



    }

    public void parseCustomElement(Element ele) {
    }

    public void parseConstructorArgElements(Element ele, BeanDefinition bd){

    }
}
