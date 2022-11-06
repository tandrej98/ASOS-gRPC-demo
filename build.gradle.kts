plugins {
    kotlin("jvm") version "1.7.20" apply false
    id("com.google.protobuf") version "0.9.1" apply false
}

group = rootProject.group

val protobufVersion by extra { "3.21.9" }
val grpcVersion by extra { "1.50.2" }
val grpcKotlinVersion by extra { "1.3.0" }
val coroutinesVersion by extra { "1.6.4" }
val logbackVersion by extra { "1.4.4" }
val kotlinLogVersion by extra { "2.0.11" }
val ktorVersion by extra { "2.1.3" }