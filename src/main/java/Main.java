import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by lenovo on 2018/4/28.
 */
public class Main {

    public static void main(String[] args) throws IOException, SAXException {
        File file = new File("C:\\Users\\lenovo\\Desktop\\milky\\target\\classes\\test.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);//名称空间支持
        DocumentBuilder dbBuilder = null;
        Document doc = null;
        try {
            dbBuilder = factory.newDocumentBuilder();
            doc = dbBuilder.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Element root = doc.getDocumentElement();
        parseBeanDefinitions(root, new BeanDefinitionParserDelegate());
    }
    public static void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate){
        NodeList nl = root.getChildNodes();
        for(int i=0; i<nl.getLength(); i++){
            Node node = nl.item(i);
            if(node instanceof Element){
                Element ele = (Element) node;
                if(delegate.isDefaultNamespace(ele)){
                    parseDefaultElement(ele, delegate);
                }
                else{
                    delegate.parseCustomElement(ele);
                }
            }
        }
    }

    private static void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
        if(ele.getTagName().equals("bean")){
            delegate.parseBeanDefinitionElement(ele);
        }
    }
}
