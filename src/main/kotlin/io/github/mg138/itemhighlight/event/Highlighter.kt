package io.github.mg138.itemhighlight.event

import io.github.mg138.itemhighlight.ItemHighlight
import io.github.mg138.itemhighlight.util.ColorUtil
import io.github.mg138.itemhighlight.util.Glow
import org.bukkit.entity.EntityType
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent
import net.md_5.bungee.api.chat.TranslatableComponent

class Highlighter : Listener {
    @EventHandler
    fun onItemSpawn(event: EntitySpawnEvent) {
        if (event.entityType == EntityType.DROPPED_ITEM) {
            val item = event.entity as Item
            val meta = item.itemStack.itemMeta ?: return

            item.isCustomNameVisible = true
            val name = when {
                meta.displayName.isNotEmpty() -> meta.displayName
                else -> null
            }
            item.isGlowing = true
            item.customName = name
            Glow.setGlowColor(ColorUtil.getMostSignificantColor(name), item)
        }
    }
}