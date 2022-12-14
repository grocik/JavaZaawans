import Logger.Logger;
import Strategy.*;
import org.apache.log4j.PropertyConfigurator;

import java.lang.reflect.Method;
import java.util.List;


public class SaveInvoker {
    Logger logger = new Logger();

    public void invoke(Class objectToInvokeMethodFrom,String name)  {
        String log4jPath = "src/main/java/Logs/log4j.properties";
        PropertyConfigurator.configure(log4jPath);
        try {
            Class c = Class.forName(objectToInvokeMethodFrom.getName());
            Object o = c.newInstance();
            Method m = c.getDeclaredMethod(name);
            m.setAccessible(true);
            m.invoke(o);
        } catch (Exception e){
            System.out.println(e.getCause().getClass().getSimpleName());
            List.of(new ConnectionLostStrategy(),
                    new FileInUseStrategy(),
                    new MissingFileStrategy(),
                    new NoPermissionStrategy(),
                    new UnableToConnectStrategy()).stream()
                    .filter(strategy ->
                            strategy.getName()
                                    .equals(e.getCause().getClass().getSimpleName()))
                    .forEach(strategy -> strategy.whatToDo(objectToInvokeMethodFrom,name,logger));
        }



    }


}

