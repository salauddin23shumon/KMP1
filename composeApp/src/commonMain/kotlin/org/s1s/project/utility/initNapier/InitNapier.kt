package org.s1s.project.utility.initNapier

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.s1s.project.Platform

fun initNapier() {

    Napier.base(DebugAntilog())

}