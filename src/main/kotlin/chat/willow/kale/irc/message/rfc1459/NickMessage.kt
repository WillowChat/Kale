package chat.willow.kale.irc.message.rfc1459

import chat.willow.kale.irc.message.IMessage
import chat.willow.kale.irc.message.IMessageParser
import chat.willow.kale.irc.message.IMessageSerialiser
import chat.willow.kale.irc.message.IrcMessage
import chat.willow.kale.irc.prefix.Prefix
import chat.willow.kale.irc.prefix.PrefixParser
import chat.willow.kale.irc.prefix.PrefixSerialiser

data class NickMessage(val source: Prefix? = null, val nickname: String, val hopcount: Int? = null): IMessage {
    override val command: String = "NICK"

    companion object Factory: IMessageParser<NickMessage>, IMessageSerialiser<NickMessage> {

        override fun serialise(message: NickMessage): IrcMessage? {
            val prefix = if (message.source != null) { PrefixSerialiser.serialise(message.source) } else { null }

            var parameters = listOf(message.nickname)

            if (message.hopcount != null) {
                parameters += message.hopcount.toString()
            }

            return IrcMessage(command = message.command, prefix = prefix, parameters = parameters)
        }

        override fun parse(message: IrcMessage): NickMessage? {
            if (message.parameters.isEmpty()) {
                return null
            }

            val source = PrefixParser.parse(message.prefix ?: "")
            val nickname = message.parameters[0]

            if (message.parameters.size < 2) {
                return NickMessage(source = source, nickname = nickname)
            } else {
                val hopcount = try {
                    message.parameters[1].toInt()
                } catch (exception: NumberFormatException) {
                    return null
                }

                return NickMessage(source = source, nickname = nickname, hopcount = hopcount)
            }
        }
    }

}