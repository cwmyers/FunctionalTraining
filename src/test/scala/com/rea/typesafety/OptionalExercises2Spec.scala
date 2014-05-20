package com.rea.typesafety

import org.specs2.mutable.Specification
import OptionalExercises2._

class OptionalExercises2Spec extends Specification {
  "Get Environment for Host" should {
    "be prod for host1" in {
      getEnvForHost("host1") === "prod"
    }
    "be test for host2" in {
      getEnvForHost("host2") === "test"
    }

    "be couldn't resolve for host3 and host 4" in {
      getEnvForHost("host3") === "couldn't resolve"
      getEnvForHost("host4") === "couldn't resolve"
    }

  }

  "Connect to ReaHostsOnly" should {
    "connected to rea.com for host1" in {
      connectToReaHostsOnly("host1") === "connected to rea.com"
    }
    "connected to test.rea.com for host2" in {
      connectToReaHostsOnly("host2") === "connected to test.rea.com"
    }

    "not be connected to netflix.com for host3" in {
      connectToReaHostsOnly("host3") === "not connected to netflix.com"
    }

    "not be connected to unknown for host4" in {
      connectToReaHostsOnly("host4") === "not connected to unknown host"
    }
  }

}
