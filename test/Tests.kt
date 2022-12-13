
import net.auoeke.ke.source
import net.auoeke.ke.type
import org.junit.jupiter.api.Test
import org.junit.platform.commons.annotation.Testable

@Testable
class Tests {
    @Test fun test() {
        println(this.type.source!!.resolve("META-INF/MANIFEST.MF"))
    }
}
