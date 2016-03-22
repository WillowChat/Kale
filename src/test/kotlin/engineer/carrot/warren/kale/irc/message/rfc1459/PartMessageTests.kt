package engineer.carrot.warren.kale.irc.message.rfc1459

import engineer.carrot.warren.kale.irc.message.IMessageFactory
import engineer.carrot.warren.kale.irc.message.IrcMessage
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class PartMessageTests {
    lateinit var factory: IMessageFactory<PartMessage>

    @Before fun setUp() {
        factory = PartMessage.Factory
    }

    @Test fun test_parse_OneChannel() {
        val message = factory.parse(IrcMessage(command = "PART", parameters = listOf("channel1")))

        assertEquals(message, PartMessage(channels = listOf("channel1")))
    }

    @Test fun test_parse_MultipleChannels() {
        val message = factory.parse(IrcMessage(command = "PART", parameters = listOf("channel1,channel2,channel3")))

        assertEquals(message, PartMessage(channels = listOf("channel1", "channel2", "channel3")))
    }

    @Test fun test_parse_NoParameters() {
        val message = factory.parse(IrcMessage(command = "PART"))

        assertEquals(message, null)
    }

    @Test fun test_serialise_OneChannel() {
        val message = factory.serialise(PartMessage(channels = listOf("channel1")))

        assertEquals(message, IrcMessage(command = "PART", parameters = listOf("channel1")))
    }

    @Test fun test_serialise_MultipleChannels() {
        val message = factory.serialise(PartMessage(channels = listOf("channel1", "channel2")))

        assertEquals(message, IrcMessage(command = "PART", parameters = listOf("channel1,channel2")))
    }

}