
import net.auoeke.extensions.source
import net.auoeke.extensions.type
import org.junit.jupiter.api.Test
import org.junit.platform.commons.annotation.Testable

@Testable
class Tests {
    @Test fun test() {
        println(this.type.source!!.resolve("META-INF/MANIFEST.MF"))
    }
}
