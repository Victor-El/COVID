package me.codeenzyme.covid.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.codeenzyme.covid.models.GlobalStatResponse

@Entity
data class GlobalStat(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "active")
    val active: Int?,
    @ColumnInfo(name = "active_per_one_million")
    val activePerOneMillion: Double?,
    @ColumnInfo(name = "affected_countries")
    val affectedCountries: Int?,
    @ColumnInfo(name = "cases")
    val cases: Int?,
    @ColumnInfo(name = "cases_per_one_million")
    val casesPerOneMillion: Int?,
    @ColumnInfo(name = "critical")
    val critical: Int?,
    @ColumnInfo(name = "critical_per_one_million")
    val criticalPerOneMillion: Double?,
    @ColumnInfo(name = "deaths")
    val deaths: Int?,
    @ColumnInfo(name = "deaths_per_one_million")
    val deathsPerOneMillion: Double?,
    @ColumnInfo(name = "one_case_per_people")
    val oneCasePerPeople: Int?,
    @ColumnInfo(name = "one_death_per_people")
    val oneDeathPerPeople: Int?,
    @ColumnInfo(name = "one_test_per_people")
    val oneTestPerPeople: Int?,
    @ColumnInfo(name = "population")
    val population: Long?,
    @ColumnInfo(name = "recovered")
    val recovered: Int?,
    @ColumnInfo(name = "recovered_per_one_million")
    val recoveredPerOneMillion: Double?,
    @ColumnInfo(name = "tests")
    val tests: Int?,
    @ColumnInfo(name = "tests_per_one_million")
    val testsPerOneMillion: Double?,
    @ColumnInfo(name = "today_cases")
    val todayCases: Int?,
    @ColumnInfo(name = "today_deaths")
    val todayDeaths: Int?,
    @ColumnInfo(name = "today_recovered")
    val todayRecovered: Int?,
    @ColumnInfo(name = "updated")
    val updated: Long?
) {
    constructor(globalStatResponse: GlobalStatResponse?) : this(
        uid = 1,
        active = globalStatResponse?.active,
        activePerOneMillion = globalStatResponse?.activePerOneMillion,
        affectedCountries = globalStatResponse?.affectedCountries,
        cases = globalStatResponse?.cases,
        casesPerOneMillion = globalStatResponse?.casesPerOneMillion,
        critical = globalStatResponse?.critical,
        criticalPerOneMillion = globalStatResponse?.criticalPerOneMillion,
        deaths = globalStatResponse?.deaths,
        deathsPerOneMillion = globalStatResponse?.deathsPerOneMillion,
        oneCasePerPeople = globalStatResponse?.oneCasePerPeople,
        oneDeathPerPeople = globalStatResponse?.oneDeathPerPeople,
        oneTestPerPeople = globalStatResponse?.oneTestPerPeople,
        population = globalStatResponse?.population,
        recovered = globalStatResponse?.recovered,
        recoveredPerOneMillion = globalStatResponse?.recoveredPerOneMillion,
        todayRecovered = globalStatResponse?.todayRecovered,
        tests = globalStatResponse?.tests,
        testsPerOneMillion = globalStatResponse?.testsPerOneMillion,
        todayCases = globalStatResponse?.todayCases,
        todayDeaths = globalStatResponse?.todayDeaths,
        updated = globalStatResponse?.updated
    )
}