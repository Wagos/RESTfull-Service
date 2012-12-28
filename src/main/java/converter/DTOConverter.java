package converter;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 17.12.12
 * Time: 12:03
 */
public class DTOConverter {
    public static Object cast(Object from, Class to){
        try {
            Object obj=to.newInstance();
            for(PropertyDescriptor descriptor: Introspector.getBeanInfo(to).getPropertyDescriptors()){
                if (descriptor.getReadMethod() != null && !"class".equals(descriptor.getName())) {
                    descriptor.getWriteMethod().invoke(obj,
                            new PropertyDescriptor(descriptor.getName(), from.getClass()).getReadMethod().invoke(from));
                }
            }
            return obj;
        } catch (IntrospectionException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static List<Object> castAll(Collection from,Class to){
        List<Object> collection=new ArrayList<>();
        for(Object o:from){
            collection.add(cast(o,to));
        }
        return collection;
    }
}
