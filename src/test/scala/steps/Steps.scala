package steps
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import fs2.Stream
import fs2.interop.reactivestreams._
import io.cucumber.scala.{EN, ScalaDsl}
import org.scalatest.concurrent.Eventually
import org.scalatest.matchers.should.Matchers._
import org.scalatest.time._
import uk.gov.nationalarchives.DAS3Client

import java.io.File
import scala.io.Source
class Steps extends ScalaDsl with EN with Eventually {
  override implicit def patienceConfig: PatienceConfig =
    PatienceConfig(timeout = Span(5, Minutes), interval = Span(30, Seconds))

  val fs2Client: DAS3Client[IO] = DAS3Client[IO]()

  Given("^today is (.*)") { (day: String) =>
    println("AN EXAMPLE")
  }

  When("^I ask whether it's (.*) yet") { (day: String) =>
    System.out.println(s"all step definitions are implemented $day")
  }

  Then("I should be told {string}") { (res: String) =>
    System.out.println(s"The scenario passes $res")
  }

  Given("^An upload into the test bucket") { () =>
    val s: Stream[IO, Byte] = Stream.emits(List('t', 'e'.toByte, 's'.toByte, 't'.toByte))
    s.chunks.map(_.toByteBuffer).toUnicastPublisher.use { publisher =>
      fs2Client.upload("test-public-access-block-2023-06-13-09-20", "testfile", 4, publisher)
    }.unsafeRunSync()
  }
  Then("The file should be in the DR bucket") { () =>
    eventually {
      val a = fs2Client.download("test-public-access-block-2023-06-13-09-20", "testfile")
        .map(_.toStreamBuffered[IO](1024 * 1024).map(_.get()))
        .flatMap(f => f.compile.toList)
        .unsafeRunSync()
      print(a)
    }

  }
}
