import org.springframework.beans.*;

import java.util.ArrayList;

/**
 * Created by lenovo on 2018/5/1.
 */
public class ManagedArray<E> extends ArrayList<E> implements TypeConverter {

    private String elementTypeName;

    public String getElementTypeName() {
        return elementTypeName;
    }

    public void setElementTypeName(String elementTypeName) {
        this.elementTypeName = elementTypeName;
    }

    public Object convert() throws Exception {
        return null;
    }
}
