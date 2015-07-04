/*
 * Copyright 2015 Dennis Vriend
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dnvriend

import akka.actor.ActorSystem
import akka.event.{ Logging, LoggingAdapter }
import akka.stream.{ ActorMaterializer, ActorMaterializerSettings, Materializer, Supervision }

import scala.concurrent.ExecutionContext

trait CoreServices {
  implicit val system: ActorSystem = ActorSystem()
  implicit val log: LoggingAdapter = Logging(system, this.getClass)
  val decider: Supervision.Decider = {
    case _ ⇒ Supervision.Resume
  }
  implicit val mat: Materializer = ActorMaterializer(
    ActorMaterializerSettings(system).withSupervisionStrategy(decider))
  implicit val ec: ExecutionContext = system.dispatcher
}
