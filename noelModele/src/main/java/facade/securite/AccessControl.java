package facade.securite;

import facade.erreurs.AccesRefuseException;
import modele.personnes.Lutin;

import java.lang.reflect.Method;

public class AccessControl {
    public static void checkAnnotationAccess(Method method, Lutin lutin) {
        Class clazz = method.getDeclaringClass();
        for(Class ifc : clazz.getInterfaces()) {
            try {
                Method ifcMethod = ifc.getMethod(method.getName(),method.getParameterTypes());
                if (ifcMethod.isAnnotationPresent(HasRole.class))
                    verifyAccessAllowed(lutin, ifcMethod.getDeclaredAnnotation(HasRole.class).value());
            } catch (NoSuchMethodException e1) {
            }
        }
    }

    private static void verifyAccessAllowed(Lutin lutin, String[] rolesAllowed) {
        for(String role : rolesAllowed) {
            if (lutin.hasRole(role)) return;
        }
        throw new AccesRefuseException();
    }

}
