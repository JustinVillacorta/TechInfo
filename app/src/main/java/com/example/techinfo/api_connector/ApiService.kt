package com.example.techinfo.api_connector

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName
import retrofit2.http.Query
import java.io.Serializable

// Data class for OTP request
data class OTPRequest(
    val email: String
)

// Data class for password reset request
data class PasswordResetRequest(
    val email: String,
    val token: String,
    val password: String
)

// Processor model
data class Processor(
    val processor_id: String,
    val processor_name: String,
    val brand: String,
    val socket_type: String,
    val compatible_chipsets: String,
    val cores: String,
    val threads: String,
    val base_clock_speed: String,
    val max_turbo_boost_clock_speed: String,
    val tdp: String,
    val cache_size_mb: String,
    val integrated_graphics: String,
    val link: String,
    val performance_score: String,
    val created_at: String,
    val updated_at: String
) :  Serializable // Implementing  and Serializable

// Resolution model
data class Resolution(
    val screen_resolutions_id: String,
    val resolution_size: String,
    val resolutions_name: String,
    val created_at: String,
    val updated_at: String
) : Serializable // Implementing  and Serializable

// GPU model
data class Gpu(
    val gpu_id: Int,
    val gpu_name: String,
    val brand: String,
    val cuda_cores: String,
    val interface_type: String,
    val tdp_wattage: String,
    val gpu_length_mm: String,
    val memory_size_gb: String,
    val memory_type: String,
    val memory_interface_bits: String,
    val base_clock_ghz: String,
    val game_clock_ghz: String,
    val boost_clock_ghz: String,
    val compute_units: String,
    val stream_processors: String,
    val required_power: String,
    val required_6_pin_connectors: String,
    val required_8_pin_connectors: String,
    val required_12_pin_connectors: String,
    val link: String?,
    val performance_score: String,
    val created_at: String,
    val updated_at: String
) : Serializable // Implementing  and Serializable

// Motherboard model
data class Motherboard(
    val motherboard_id: Int,
    val motherboard_name: String,
    val brand: String,
    val socket_type: String,
    val chipset: String,
    val link: String?,                // Nullable field
    val wifi: String,                 // Yes/No or other representation for Wi-Fi
    val gpu_support: String,          // GPU support information
    val max_ram_slots: Int,           // Maximum number of RAM slots
    val max_ram_capacity: String,     // Maximum RAM capacity (e.g., "128 GB")
    val max_ram_speed: String,        // Maximum RAM speed (e.g., "5000 MHz")
    val ram_type: String,             // Type of RAM supported (e.g., DDR4)
    val has_pcie_slot: Int,           // Whether it has a PCIe slot (1 for Yes, 0 for No)
    val has_sata_ports: Int,          // Whether it has SATA ports (1 for Yes, 0 for No)
    val has_m2_slot: Int,             // Whether it has an M.2 slot (1 for Yes, 0 for No)
    val gpu_interface: String,        // GPU interface type (e.g., PCIe 4.0)
    val form_factor: String,          // Form factor (e.g., ATX, Micro-ATX)
    val performance_score: String,
    val created_at: String,           // Creation timestamp
    val updated_at: String            // Last updated timestamp
) : Serializable // Implementing Component and Serializable

// RAM model
data class Ram(
    val ram_id: Int,
    val ram_name: String,
    val brand: String,
    val ram_capacity_gb: String,
    val ram_speed_mhz: String,
    val power_consumption: String,
    val link: String?,
    val performance_score: String,
    val created_at: String,
    val updated_at: String
) :  Serializable // Implementing Component and Serializable

// Power Supply Unit model
data class PowerSupplyUnit(
    val psu_id: Int,
    val psu_name: String,
    val brand: String,
    val wattage: String,
    val continuous_wattage: String,
    val gpu_6_pin_connectors: Int,
    val gpu_8_pin_connectors: Int,
    val gpu_12_pin_connectors: Int,
    val efficiency_rating: String,
    val has_required_connectors: Int,
    val performance_score: String,
    val link: String?,
    val created_at: String,
    val updated_at: String
) :  Serializable // Implementing Component and Serializable

// Case model
data class Case(
    val case_id: Int,
    val case_name: String,
    val brand: String,
    val form_factor_supported: String,
    val max_gpu_length_mm: String,
    val max_hdd_count: Int,
    val max_ssd_count: Int,
    val current_hdd_count: Int,
    val current_ssd_count: Int,
    val airflow_rating: String,
    val max_cooler_height_mm: String,
    val link: String?,
    val created_at: String,
    val updated_at: String
) :  Serializable // Implementing Component and Serializable

// CPU Cooler model
data class CpuCooler(
    val cooler_id: Int,
    val cooler_name: String,
    val brand: String,
    val tdp_rating: String,
    val socket_type_supported: String,
    val max_cooler_height_mm: String,
    val link: String?,
    val created_at: String,
    val updated_at: String
) :  Serializable // Implementing Component and Serializable

// HDD model
data class Hdd(
    val hdd_id: Int,
    val hdd_name: String,
    val brand: String,
    val capacity_gb: String,
    val link: String?,
    val created_at: String,
    val performance_score: String,
    val updated_at: String
) :  Serializable // Implementing Component and Serializable

// SSD model
data class Ssd(
    val ssd_id: Int,
    val ssd_name: String,
    val capacity_gb: String,
    val interface_type: String,
    val link: String?,
    val performance_score: String,
    val created_at: String,
    val updated_at: String
) :  Serializable // Implementing Component and Serializable

// Other data classes remain unchanged

data class CompatibilityResponse(
    val is_compatible: Boolean,
    val issues: List<String>,
    val components: SelectedComponents
)

// A data class to hold the selected components for troubleshooting purposes
data class SelectedComponents(
    val processor: String?,
    val motherboard: String?,
    val ram: String?,
    val gpu: String?,
    val psu: String?,
    val case: String?,
    val cooler: String?,
    val hdd: String?,
    val ssd: String?
)

// Other data classes for bottleneck requests and responses remain unchanged

data class BottleneckRequest(
    val processor_name: String,
    val gpu_name: String,
    val resolutions_name: String,
)

data class BottleneckResponse(
    val bottleneck: String,
    val cpuScore: String,
    val gpuScore: String,
    val resolution_modifier: String,
    val percentage_difference: Double,
    val message: String
)

data class BuildComparisonRequest(
    val build_one: BuildData,
    val build_two: BuildData
)

data class BuildData(
    val processor_name: String,
    val gpu_name: String,
    val ram_name: String,
    val psu_name: String,
    val ssd_name: String,
    val hdd_name: String
)

data class BuildComparisonResponse(
    val build_one: BuildMetrics,
    val build_two: BuildMetrics
)

data class BuildMetrics(
    val processor_percentage: Double,
    val gpu_percentage: Double,
    val ram_percentage: Double,
    val psu_percentage: Double,
    val ssd_percentage: Double
)

data class TroubleShoot_data(
    val title: String,
    val id: String
)

data class TroubleshootContent(
    val id: Int,
    val title: String,
    val content: String,
    @SerializedName("video_embed") val videoEmbed: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)





// Define the API service interface
interface ApiService {
    @GET("troubleshoot_articles")
    fun getTroubleshootArticles(): Call<List<TroubleshootContent>>


    @GET("screen_resolutions")
    fun getResolution(): Call<List<Resolution>>

    @GET("processors")
    fun getProcessors(): Call<List<Processor>>

    @GET("gpuses")
    fun getGpus(): Call<List<Gpu>>

    @GET("rams")
    fun getRams(): Call<List<Ram>>

    @GET("motherboards")
    fun getMotherboards(): Call<List<Motherboard>>

    @GET("power_supply_units")
    fun getPowerSupplyUnits(): Call<List<PowerSupplyUnit>>

    @GET("computer_cases")
    fun getComputerCases(): Call<List<Case>>

    @GET("cpu_coolers")
    fun getCpuCoolers(): Call<List<CpuCooler>>

    @GET("ssds")
    fun getSsds(): Call<List<Ssd>>

    @GET("hdds")
    fun getHdds(): Call<List<Hdd>>

    @GET("compatibility_checker")
    fun checkCompatibility(
        @Query("processor_name") processorName: String,
        @Query("motherboard_name") motherboardName: String,
        @Query("ram_name") ramName: String,
        @Query("gpu_name") gpuName: String,
        @Query("psu_name") psuName: String,
        @Query("case_name") caseName: String,
        @Query("cooler_name") coolerName: String,
        @Query("hdd_name") hddName: String,
        @Query("ssd_name") ssdName: String
    ): Call<CompatibilityResponse> // Use a different return type if the API returns a response body

    @POST("bottleneck")
    fun postBottleneckData(@Body data: BottleneckRequest): Call<BottleneckResponse>

    @POST("pc_compare")
    fun getBuildComparison(@Body data: BuildComparisonRequest): Call<BuildComparisonResponse>

    // Other API endpoints as needed
}
