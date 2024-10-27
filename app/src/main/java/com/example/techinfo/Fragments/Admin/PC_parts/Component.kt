package com.example.techinfo.Fragments.Admin.PC_parts

// Component.kt
sealed class Component {
    data class Processor(val name: String) : Component()
    data class Gpu(val name: String) : Component()
    data class Ram(val name: String) : Component()
    data class Motherboard(val name: String) : Component()
    data class PowerSupplyUnit(val name: String) : Component()
    data class Case(val name: String) : Component()
    data class CpuCooler(val name: String) : Component()
    data class Ssd(val name: String) : Component()
    data class Hdd(val name: String) : Component()
}
