const val MAX_EXP = 5000
const val MAX_LEVEL_CAP = 35
const val MAX_INEBRIATION_LEVEL = 50

class Player constructor(playerName: String, hp: Int, isBlessed: Boolean, isImmortal: Boolean) {
    var healthPoints = hp
    var karmaLevel = 0

    val name = playerName
    val isBlessed = isBlessed
    val isImmortal = isImmortal

    var inebriationLevel = 0

    fun printPlayerStatus() {
        val auraColor = this.refreshAuraColor()
        val healthStatus = this.formatHealthStatus()
        val auraStatusFormatInfo = "Aura: $auraColor - Blessed: ${if (isBlessed) "yes" else "no"}"
        val healthStatusFormatInfo = "(HP: $healthPoints) $name $healthStatus"
        val inebriationStatus = "(Inebriation: $inebriationLevel"

        println(auraStatusFormatInfo)
        println(healthStatusFormatInfo)
        println(inebriationStatus)
    }

    fun refreshAuraColor(): String {
        karmaLevel = calculateKarmaLevel()

        val isAuraVisible = isBlessed && healthPoints > 50 || isImmortal
        var auraColor = AuraColors.NONE.name

        if (isAuraVisible) {
            auraColor = when (karmaLevel) {
                in 0..5 -> AuraColors.RED.name
                in 6..10 -> AuraColors.ORANGE.name
                in 11..15 -> AuraColors.PURPLE.name
                in 16..20 -> AuraColors.GREEN.name
                else -> AuraColors.NONE.name
            }
        }

        return auraColor
    }

    fun castFireball(fireballCount: Int = 2): Int {

        if(inebriationLevel < MAX_INEBRIATION_LEVEL) {
            inebriationLevel += fireballCount
            println("A glass of Fireball springs into existence! (x$fireballCount)")
        }
        else
        {
            println("No more booze for you!")
        }

        return inebriationLevel
    }

    fun formatHealthStatus(): String = when (healthPoints) {
        100 -> "is in perfect condition!"
        in 90..99 -> "has a few scratches!"
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quickly!"
        } else {
            "has some minor wounds!"
        }
        in 15..74 -> "looks pretty hurt!"
        else -> "is in awful condition!"
    }

    fun inebrationLevel(): String = when (inebriationLevel) {
        in 1..10 -> "tipsy"
        in 11..20 -> "sloshed"
        in 21..30 -> "soused"
        in 31..40 -> "stewed"
        in 41..Int.MAX_VALUE -> "..t0aSt3d"
        else -> "sober"

    }

    private fun validLevelRange(): List<Int> =
            (1..MAX_LEVEL_CAP).toList()

    private fun checkLevelRange(currentLevel: Int) =
            currentLevel in validLevelRange()

    private fun calculateKarmaLevel(): Int =
            (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20).toInt()

    fun notImplemented(): String = TODO()
}
