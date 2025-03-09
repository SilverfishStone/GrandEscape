package grandescape.function.compat.curios;

import grandescape.world_items.item.GrandItems;
import top.theillusivec4.curios.api.CuriosApi;

public class RegisterModCurios {
    public static void register () {
        CuriosApi.registerCurio(GrandItems.DUTCHMANS_KEY.get(), new KeyCurios());
    }
}
