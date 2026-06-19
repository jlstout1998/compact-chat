// https://github.com/EssentialGG/essential-gradle-toolkit
plugins {
    // id("gg.essential.loom") version "1.21.8" apply false
    id("gg.essential.multi-version.root")
}

preprocess {
    val fabric262 = createNode("26.2-fabric", 26_02_00, "official")
    val neoforge262 = createNode("1.21.8-neoforge", 26_02_00, "official")

    neoforge262.link(fabric262)    // Fabric 26.2    ->  NeoForge 26.2
}
