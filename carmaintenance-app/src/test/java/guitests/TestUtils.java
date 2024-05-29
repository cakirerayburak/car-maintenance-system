package guitests;

import java.awt.Component;
import java.awt.Container;

public class TestUtils {

    public static Component getChildNamed(Component parent, String name) {
        if (name.equals(parent.getName())) {
            return parent;
        }

        if (parent instanceof Container) {
            Component[] children = ((Container) parent).getComponents();

            for (Component child : children) {
                Component result = getChildNamed(child, name);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }
}
