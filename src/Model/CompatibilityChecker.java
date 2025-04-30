package Model;

public class CompatibilityChecker {
    public static boolean isCompatible(String existingCategory, String requestedCategory) {
        if (existingCategory.equalsIgnoreCase("A") && requestedCategory.equalsIgnoreCase("B")) {
            return true;
        }
        return false;
    }
}
