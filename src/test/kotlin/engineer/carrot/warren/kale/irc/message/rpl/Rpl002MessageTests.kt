package engineer.carrot.warren.kale.irc.message.rpl

import engineer.carrot.warren.kale.irc.message.IMessageFactory
import engineer.carrot.warren.kale.irc.message.IrcMessage
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class Rpl002MessageTests {
    lateinit var factory: IMessageFactory<Rpl002Message>

    @Before fun setUp() {
        factory = Rpl002Message.Factory
    }

    @Test fun test_parse_SourceTargetContents() {
        val message = factory.parse(IrcMessage(command = "002", prefix = "imaginary.bunnies.io", parameters = listOf("test-nickname", "Your host is imaginary.server, running version")))

        assertEquals(Rpl002Message(source = "imaginary.bunnies.io", target = "test-nickname", contents = "Your host is imaginary.server, running version"), message)
    }

    @Test fun test_parse_TargetContents_SourceIsEmptyString() {
        val message = factory.parse(IrcMessage(command = "002", parameters = listOf("test-nickname2", "Your host is imaginary.server, running version")))

        assertEquals(Rpl002Message(source = "", target = "test-nickname2", contents = "Your host is imaginary.server, running version"), message)
    }

    @Test fun test_parse_TooFewParameters() {
        val message = factory.parse(IrcMessage(command = "002", parameters = listOf("test-nickname3")))

        assertNull(message)
    }

    @Test fun test_serialise_SourceTargetContents() {
        val message = factory.serialise(Rpl002Message(source = "", target = "test-nickname2", contents = "Your host is imaginary.server, running version"))

        assertEquals(IrcMessage(command = "002", prefix = "", parameters = listOf("test-nickname2", "Your host is imaginary.server, running version")), message)
    }

    @Test fun test_serialise_TargetContents_SourceIsEmptyString() {
        val message = factory.serialise(Rpl002Message(source = "", target = "test-nickname2", contents = "Your host is imaginary.server, running version"))

        assertEquals(IrcMessage(command = "002", prefix = "", parameters = listOf("test-nickname2", "Your host is imaginary.server, running version")), message)
    }
}