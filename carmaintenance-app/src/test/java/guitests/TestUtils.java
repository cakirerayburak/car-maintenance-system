/**
 * @file TestUtils.java
 * @brief This file contains utility methods for GUI tests.
 */

package guitests;

import java.awt.Component;
import java.awt.Container;

/**
 * @brief Utility methods for GUI tests.
 */
public class TestUtils {

    /**
     * @brief Recursively searches for a child component with the specified name.
     * @param parent The parent component to search within.
     * @param name The name of the child component to find.
     * @return The child component with the specified name, or null if not found.
     */
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
