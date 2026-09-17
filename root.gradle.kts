// https://github.com/EssentialGG/essential-gradle-toolkit
plugins {
    // id("gg.essential.loom") version "1.21.8" apply false
    id("gg.essential.multi-version.root")
}

preprocess {
    val fabric263 = createNode("26.3-fabric", 26_03_00, "official")
    val neoforge263 = createNode("26.3-neoforge", 26_03_00, "official")

    neoforge263.link(fabric263)    // Fabric 26.3    ->  NeoForge 26.3
}
