import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.context.annotation.Bean;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by lenovo on 2018/4/28.
 */
public class BeanDefinitionParserDelegate {
    public static final String BEANS_NAMESPACE_URI = "http://www.milky.org/schema/beans";

    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String CONSTRUCTOR_ARG_ATTRIBUTE = "constructor-arg";
    private static final String INDEX_ATTRIBUTE = "index";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String TYPE_ATTRIBUTE = "type";
    private static final String REF_ATTRIBUTE = "ref";
    private static final String ARRAY_ATTRIBUTE = "array";
    private static final String LIST_ATTRIBUTE = "list";
    private static final String SET_ATTRIBUTE = "set";
    private static final String MAP_ATTRIBUTE = "map";

    public boolean isDefaultNamespace(Element ele) {
        return ele.isDefaultNamespace(BEANS_NAMESPACE_URI);
    }

    public void parseBeanDefinitionElement(Element ele) throws Exception {
        String id = ele.getAttribute(ID_ATTRIBUTE);

        String className = null;
        BeanDefinition bd = new BeanDefinition();
        //解析class属性
        if(ele.hasAttribute(CLASS_ATTRIBUTE)){
            className = ele.getAttribute(ID_ATTRIBUTE);
        }

        // TODO set id in beanDefinition
        // TODO set className in beanDefinition

        //解析构造函数参数
        parseConstructorArgElements(ele, bd);


    }

    public void parseCustomElement(Element ele) {
    }

    public Object parseCustomElement(Element ele, BeanDefinition bd){
        return null;
    }

    public void parseConstructorArgElements(Element ele, BeanDefinition bd) throws Exception {
        NodeList nl = ele.getChildNodes();
        for(int i=0; i<nl.getLength(); i++){
            Node node = nl.item(i);
            if(node instanceof Element){
                if(((Element) node).getTagName().equals(CONSTRUCTOR_ARG_ATTRIBUTE)){
                    parseConstructorArgElement(ele, bd);
                }
            }
        }
    }

    public void parseConstructorArgElement(Element ele, BeanDefinition bd) throws Exception {
        String indexAttr = ele.getAttribute(INDEX_ATTRIBUTE);
        String typeAttr = ele.getAttribute(TYPE_ATTRIBUTE);
        String refAttr = ele.getAttribute(REF_ATTRIBUTE);
        if(typeAttr!=null){
            parsePropertyValue(ele, bd);

        }

    }

    private void parsePropertyValue(Element ele, BeanDefinition bd) throws Exception {

        NodeList nl = ele.getChildNodes();
        Element subElement = null;
        for(int i=0; i<nl.getLength(); i++){
            Node node = nl.item(i);
            if(subElement!=null){
                throw new Exception("must not contain more than one sub-element");
            }
            else{
                subElement = (Element) node;
            }
        }

        if(ele.hasAttribute(REF_ATTRIBUTE)){
            // TODO set ref in bd
        }
        else if(ele.hasAttribute(VALUE_ATTRIBUTE)){
            // TODO set value in bd
        }
        else if(subElement!=null){
            Object value = parsePropertySubElement(subElement, bd);
            // TODO set the value in bd
        }

    }

    private Object parsePropertySubElement(Element ele, BeanDefinition bd) throws Exception {
        if(!isDefaultNamespace(ele)){
            return parseCustomElement(ele, bd);
        }
        String nodeName = ele.getTagName();
        if(nodeName.equals(LIST_ATTRIBUTE)){
            return parseListElement(ele, bd);
        }
        else if(nodeName.equals(ARRAY_ATTRIBUTE)){
            return parseArrayElement(ele, bd);
        }
        else if(nodeName.equals(MAP_ATTRIBUTE)){
            return parseMapElement(ele, bd);
        }
        else if(nodeName.equals(SET_ATTRIBUTE)){
            return parseSetElemnt(ele, bd);
        }
        else{
            throw new Exception("unknown property!");
        }
    }

    private Object parseSetElemnt(Element ele, BeanDefinition bd) {
        return  null;
    }

    private Object parseMapElement(Element ele, BeanDefinition bd) {
        return  null;
    }

    private Object parseArrayElement(Element ele, BeanDefinition bd) {
        return  null;
    }

    private Object parseListElement(Element ele, BeanDefinition bd) {
        return  null;
    }
}
