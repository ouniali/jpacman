package nl.tudelft.jpacman.hp;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

/**
 * The responsibility of this loader is to obtain the appropriate hp calculator.
 * By default the {@link DefaultHPCalculator} is returned.
 */
public class HPCalculatorLoader {

    private static Class clazz = null;

    /**
     * Load a hp calculator and return it.
     *
     * @return The (dynamically loaded) hp calculator.
     */
    public HPCalculator load() {
        try {
            if (clazz == null) {
                clazz = loadClassFromFile();
            }

            return (HPCalculator) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not dynamically load the hp calculator.", e);
        }
    }

    private Class loadClassFromFile() throws IOException, ClassNotFoundException {
        String strategyToLoad = getCalculatorClassName();

        if ("DefaultHPCalculator".equals(strategyToLoad)) {
            return DefaultHPCalculator.class;
        }

        URL[] urls = new URL[]{getClass().getClassLoader().getResource("scoreplugins/")};

        try (URLClassLoader classLoader = new URLClassLoader(urls, getClass().getClassLoader())) {
            return classLoader.loadClass(strategyToLoad);
        }
    }

    private String getCalculatorClassName() throws IOException {
        Properties properties = new Properties();

        properties.load(getClass().getClassLoader().getResourceAsStream("calc.properties"));

        return properties.getProperty("hpcalculator.name");
    }
}
