package chat.willow.kale.irc.message.rfc1459.rpl

import chat.willow.kale.irc.message.IrcMessage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class Rpl473MessageTests {
    lateinit var factory: Rpl473Message.Factory

    @Before fun setUp() {
        factory = Rpl473Message
    }

    @Test fun test_parse_SourceTargetContents() {
        val message = factory.parse(IrcMessage(command = "473", prefix = "imaginary.bunnies.io", parameters = listOf("test-nickname", "#channel", "Cannot join channel (+i)")))

        assertEquals(Rpl473Message(source = "imaginary.bunnies.io", target = "test-nickname", channel = "#channel", contents = "Cannot join channel (+i)"), message)
    }

    @Test fun test_parse_TargetContents_SourceIsEmptyString() {
        val message = factory.parse(IrcMessage(command = "473", parameters = listOf("test-nickname2", "#channel", "Cannot join channel (+i)")))

        assertEquals(Rpl473Message(source = "", target = "test-nickname2", channel = "#channel", contents = "Cannot join channel (+i)"), message)
    }

    @Test fun test_parse_TooFewParameters() {
        val message = factory.parse(IrcMessage(command = "473", parameters = listOf("test-nickname3")))

        assertNull(message)
    }

    @Test fun test_serialise_SourceTargetContents() {
        val message = factory.serialise(Rpl473Message(source = "", target = "test-nickname2", channel = "#channel", contents = "Cannot join channel (+i)"))

        assertEquals(IrcMessage(command = "473", prefix = "", parameters = listOf("test-nickname2", "#channel", "Cannot join channel (+i)")), message)
    }

    @Test fun test_serialise_TargetContents_SourceIsEmptyString() {
        val message = factory.serialise(Rpl473Message(source = "", target = "test-nickname2", channel = "#channel", contents = "Cannot join channel (+i)"))

        assertEquals(IrcMessage(command = "473", prefix = "", parameters = listOf("test-nickname2", "#channel", "Cannot join channel (+i)")), message)
    }
}