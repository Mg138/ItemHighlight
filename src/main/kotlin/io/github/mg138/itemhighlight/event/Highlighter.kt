package io.github.mg138.itemhighlight.event

import io.github.mg138.itemhighlight.ItemHighlight
import io.github.mg138.itemhighlight.util.ColorUtil
import io.github.mg138.itemhighlight.util.Glow
import net.md_5.bungee.api.chat.TranslatableComponent
import net.md_5.bungee.chat.ComponentSerializer
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftItem
import org.bukkit.craftbukkit.v1_16_R3.util.CraftChatMessage
import org.bukkit.entity.EntityType
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent

class Highlighter : Listener {
    @EventHandler
    fun onItemSpawn(event: EntitySpawnEvent) {
        if (event.entityType == EntityType.DROPPED_ITEM) {
            val itemEntity = event.entity as Item
            val item = itemEntity.itemStack
            val meta = item.itemMeta

            itemEntity.isCustomNameVisible = true
            if (itemEntity.customName == null) {
                when {
                    meta != null && meta.displayName.isNotEmpty() -> {
                        itemEntity.customName = meta.displayName
                    }
                    ItemHighlight.localeManager != null -> {
                        val craftItemEntity = itemEntity as CraftItem

                        craftItemEntity.handle.customName = CraftChatMessage.fromJSON(
                            ComponentSerializer.toString(
                                TranslatableComponent(
                                    ItemHighlight.localeManager!!.queryMaterial(item.type)
                                )
                            )
                        )
                    }
                    else -> itemEntity.customName = null
                }
            }
            itemEntity.isGlowing = true
            Glow.setGlowColor(ColorUtil.getMostSignificantColor(itemEntity.customName), itemEntity)
        }
    }
}