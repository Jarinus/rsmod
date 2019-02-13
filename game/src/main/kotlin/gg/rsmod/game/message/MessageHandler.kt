package gg.rsmod.game.message

import gg.rsmod.game.model.entity.Client
import mu.KotlinLogging

/**
 * A [MessageHandler] is responsible for executing [Message] logic on the
 * game-thread.
 *
 * @author Tom <rspsmods@gmail.com>
 */
interface MessageHandler<T: Message> {

    companion object {
        private val logger = KotlinLogging.logger {  }
    }

    /**
     * Handles the [message] on the game-thread with the ability to read and write
     * to the [Client].
     */
    fun handle(client: Client, message: T)

    /**
     * A default method to log the handlers.
     */
    fun log(client: Client, format: String, vararg args: Any) {
        // TODO: log normal packets if applicable (maybe have a toggle for certain
        // flagged/suspicious players to be logged)
        //logger.info(String.format(format, *args))
    }

    fun logVerificationFail(client: Client, format: String, vararg args: Any) {
        logger.warn(String.format(format, *args))
    }
}