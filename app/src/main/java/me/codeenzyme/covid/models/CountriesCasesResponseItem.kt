package me.codeenzyme.covid.models


import com.google.gson.annotations.SerializedName

data class CountriesCasesResponseItem(
    @SerializedName("active")
    val active: Int,
    @SerializedName("activePerOneMillion")
    val activePerOneMillion: Double,
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Double,
    @SerializedName("continent")
    val continent: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,
    @SerializedName("critical")
    val critical: Int,
    @SerializedName("criticalPerOneMillion")
    val criticalPerOneMillion: Double,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Double,
    @SerializedName("oneCasePerPeople")
    val oneCasePerPeople: Double,
    @SerializedName("oneDeathPerPeople")
    val oneDeathPerPeople: Double,
    @SerializedName("oneTestPerPeople")
    val oneTestPerPeople: Double,
    @SerializedName("population")
    val population: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("recoveredPerOneMillion")
    val recoveredPerOneMillion: Double,
    @SerializedName("tests")
    val tests: Int,
    @SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Double,
    @SerializedName("todayCases")
    val todayCases: Int,
    @SerializedName("todayDeaths")
    val todayDeaths: Int,
    @SerializedName("todayRecovered")
    val todayRecovered: Int,
    @SerializedName("updated")
    val updated: Long
)