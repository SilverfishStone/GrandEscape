package grandescape.world_items.item.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import grandescape.GrandEscape;
import grandescape.world_items.item.GrandItems;

public class KeyItem extends Item {
    public boolean CANOPEN = Boolean.getBoolean("canloot");
    public KeyItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        LivingEntity player = (LivingEntity) pEntity;
        if (!GrandEscape.curiosInstalled()) {
            canloot(pEntity, GrandItems.inHandSlots(player, this));
        } else {
            if (GrandItems.inHandSlots(player, this)) {
                canloot(pEntity, true);
            } else {
                canloot(pEntity, player.getPersistentData().getBoolean("keyinslot"));
            }
        }
    }
    public void canloot (Entity entity, boolean canloot) {
        entity.getPersistentData().putBoolean("canloot", canloot);
    }
}
