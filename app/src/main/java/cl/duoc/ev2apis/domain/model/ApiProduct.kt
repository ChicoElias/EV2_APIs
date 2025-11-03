package cl.duoc.ev2apis.domain.model

data class ApiProduct(
    val id: String,
    val name: String,
    val description: String,
    val baseUrl: String,
    val priceMonthly: Int
)

val SampleApis = listOf(
    ApiProduct("1", "WeatherPro", "Clima en tiempo real", "https://api.weatherpro.io", 9990),
    ApiProduct("2", "PayLink", "Pagos y checkout", "https://api.paylink.dev", 14990),
    ApiProduct("3", "GeoLite", "Geocoding/Maps", "https://api.geolite.app", 12990)
)
