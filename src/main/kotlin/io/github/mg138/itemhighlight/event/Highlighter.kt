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
            val name: String = when {
                meta.displayName.isNotEmpty() -> meta.displayName
                ItemHighlight.localeLib != null -> {
                    val key = ItemHighlight.localeLib!!.localeManager.queryMaterial(item.itemStack.type)
                    TranslatableComponent(key).toPlainText()
                }
                else -> ""
            }
            item.customName = name
            item.isGlowing = true
            Glow.setGlowColor(ColorUtil.getMostSignificantColor(name), item)
        }
    }
}