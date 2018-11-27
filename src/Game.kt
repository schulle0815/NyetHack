
fun main(args: Array<String>) {

    val player = Player("Uschi", 89, true, false)

    // Aura
    val auraColor = player.refreshAuraColor()

    // health status
    val healthStatus = player.formatHealthStatus()

    // player status
    player.printPlayerStatus()

    // call method with default value
    player.castFireball()

    // player.notImplemented()
}



