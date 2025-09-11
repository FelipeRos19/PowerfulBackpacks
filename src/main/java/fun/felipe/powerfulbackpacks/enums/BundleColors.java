package fun.felipe.powerfulbackpacks.enums;

import org.bukkit.Material;

public enum BundleColors {
    EMPTY(Material.BUNDLE),
    WHITE_DYE(Material.WHITE_BUNDLE),
    LIGHT_GRAY_DYE(Material.LIGHT_GRAY_BUNDLE),
    GRAY_DYE(Material.GRAY_BUNDLE),
    BLACK_DYE(Material.BLACK_BUNDLE),
    BROWN_DYE(Material.BROWN_BUNDLE),
    RED_DYE(Material.RED_BUNDLE),
    ORANGE_DYE(Material.ORANGE_BUNDLE),
    YELLOW_DYE(Material.YELLOW_BUNDLE),
    LIME_DYE(Material.LIME_BUNDLE),
    GREEN_DYE(Material.GREEN_BUNDLE),
    CYAN_DYE(Material.CYAN_BUNDLE),
    LIGHT_BLUE_DYE(Material.LIGHT_BLUE_BUNDLE),
    BLUE_DYE(Material.BLUE_BUNDLE),
    PURPLE_DYE(Material.PURPLE_BUNDLE),
    MAGENTA_DYE(Material.MAGENTA_BUNDLE),
    PINK_DYE(Material.PINK_BUNDLE);

    public final Material bundleResult;

    BundleColors(Material bundleResult) {
        this.bundleResult = bundleResult;
    }

    public Material getBundleResult() {
        return bundleResult;
    }
}
