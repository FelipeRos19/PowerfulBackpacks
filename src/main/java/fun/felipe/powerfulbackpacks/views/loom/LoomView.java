package fun.felipe.powerfulbackpacks.views.loom;

import fun.felipe.inventoryCreator.core.models.InventoryModel;
import fun.felipe.powerfulbackpacks.PowerfulBackpacks;
import fun.felipe.powerfulbackpacks.utils.StringUtils;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.format.NamedTextColor;

public class LoomView extends InventoryModel {

    public LoomView() {
        super(6*9, StringUtils.format("\ue103\uE001").color(NamedTextColor.WHITE)
                .font(Key.key("powerfulbackpacks:gui")));

        this.registerItems();
        this.registerActions();
        this.registerCraftSlots();
    }
    //7,8,11,12,13,20,21,22,29,30,31,24
    private void registerItems() {
        this.setItem(7, PowerfulBackpacks.getInstance().getIconsManager().getIcon("knowledge_book"));
        this.setItem(8, PowerfulBackpacks.getInstance().getIconsManager().getIcon("reinforced_anvil"));
        /*
        int[] reservedSlots = new int[]{7,8,11,12,13,20,21,22,24,29,30,31};
        for (int i = 0; i < 54; i++) {
            int temp = i;
            if (Arrays.stream(reservedSlots).noneMatch(x -> x == temp)) {
                this.setItem(temp, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }
        }
         */
    }

    private void registerActions() {

    }

    private void registerCraftSlots() {
        this.canInput(11);
        this.canInput(12);
        this.canInput(13);
        this.canInput(20);
        this.canInput(21);
        this.canInput(22);
        this.canInput(29);
        this.canInput(30);
        this.canInput(31);
        this.canInput(42);
    }

    @Override
    protected void onInputChanged() {
        //TODO: AQUI EU PRECISO CHAMAR O CRAFT SYSTEM PRA VALIDAR A RECIPE
    }
}
