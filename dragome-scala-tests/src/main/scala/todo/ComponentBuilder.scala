package com.dragome.examples.todo

import Definitions._
import com.dragome.guia.components.interfaces.VisualComponent

object MethodRunner extends App {

    class Foo(x: Unit) {
        def like(y: Unit) = {
        }
    }

    class Bar(x: Class[_ <: VisualComponent]) {
        def as(y: Class[_ <: VisualComponent]): Foo = {
            null
        }
    }

    object bind {
        def template(in: String): Bar = {
            null
        }
    }

    bind template "OK" as textField like {
        bind template "OK" as textField like {

        }
    }
}