package utilities.catscraft;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class ItemUtility {
    private static final Map<NamespacedKey, ItemStack> pluginItems;

    public static void registerItem (NamespacedKey key, ItemStack stack) {
        pluginItems.putIfAbsent(key, stack);
    }

    /**
     * Will fetch all the items that have a similar namespace
     * Example: If you set the namespace to be minecraft, it will fetch all the items that are setup like:
     *      minecraft:{name}
     *
     * @param namespace
     */
    public static List<ItemStack> fetchItems (String namespace) {
        List<ItemStack> list = Lists.newArrayList();
        for (Map.Entry<NamespacedKey, ItemStack> entry : pluginItems.entrySet()) {
            if (entry.getKey().getNamespace().equalsIgnoreCase(namespace)) list.add(entry.getValue());
        }
        return list;
    }

    public static Map<NamespacedKey, ItemStack> getPluginItems() {
        return pluginItems;
    }

    static {
        pluginItems = Maps.newHashMap();
    }
}
