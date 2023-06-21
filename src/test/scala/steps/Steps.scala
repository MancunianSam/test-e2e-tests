package steps

import io.cucumber.scala.{EN, ScalaDsl}
class Steps extends ScalaDsl with EN {
  Given("^today is (.*)") { (day: String) =>
    println("AN EXAMPLE")
  }

  When("^I ask whether it's (.*) yet") { (day: String) =>
    System.out.println(s"all step definitions are implemented $day")
  }

  Then("I should be told {string}") { (res: String) =>
    System.out.println(s"The scenario passes $res")
  }
}
