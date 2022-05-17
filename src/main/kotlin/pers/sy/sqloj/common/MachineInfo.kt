package pers.sy.sqloj.common

import java.lang.management.ManagementFactory

fun getMachineInfo(): Any {
    val property = ManagementFactory.getRuntimeMXBean().systemProperties
    return object {
        val os = "${property["os.name"]} ${property["os.arch"]} ${property["os.version"]}"
        val java = "${property["java.runtime.name"]} ${property["java.vm.version"]}"
    }
}

object SystemInfo {
    val machineInfo = getMachineInfo()
}


