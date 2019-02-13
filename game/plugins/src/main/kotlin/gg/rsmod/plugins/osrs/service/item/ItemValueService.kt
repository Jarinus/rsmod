package gg.rsmod.plugins.osrs.service.item

import gg.rsmod.game.Server
import gg.rsmod.game.fs.def.ItemDef
import gg.rsmod.game.model.World
import gg.rsmod.game.service.Service
import gg.rsmod.util.ServerProperties
import mu.KotlinLogging

/**
 * @author Tom <rspsmods@gmail.com>
 */
class ItemValueService : Service() {

    companion object {
        private val logger = KotlinLogging.logger {  }
    }

    private val values = hashMapOf<Int, Int>()

    override fun init(server: Server, world: World, serviceProperties: ServerProperties) {
        val items = world.definitions.getCount(ItemDef::class.java)
        for (i in 0 until items) {
            val def = world.definitions.getNullable(ItemDef::class.java, i) ?: continue

            if (!def.isNoted() && def.name.isNotBlank()) {
                values[i] = def.cost
                if (def.noteTemplateId == 0 && def.noteLinkId > 0) {
                    values[def.noteLinkId] = def.cost
                }
            }
        }
        logger.info("Loaded {} item values.", values.size)
    }

    override fun postLoad(server: Server, world: World) {
    }

    override fun terminate(server: Server, world: World) {
    }

    fun get(item: Int): Int = values[item] ?: 0
}