package GUI;

public abstract class GlobalFlag {
    public static boolean loaded = false;

    public static boolean isLoaded() {
        return loaded;
    }

    public static void setLoaded() {
        GlobalFlag.loaded = true;
    }
}
