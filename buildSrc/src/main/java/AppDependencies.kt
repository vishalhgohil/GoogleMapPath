import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * This block used for lifecycle dependencies
 */
fun DependencyHandler.lifeCycle() {
    add("implementation", "androidx.lifecycle:lifecycle-runtime-ktx:${Config.Versions.lifecycleVersion}")
    add("implementation", "androidx.lifecycle:lifecycle-extensions:${Config.Versions.lifecycleExtVersion}")
    add("implementation", "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Config.Versions.lifecycleVersion}")
    add("implementation", "androidx.lifecycle:lifecycle-viewmodel-ktx:${Config.Versions.lifecycleVersion}")
    add("implementation", "androidx.lifecycle:lifecycle-common-java8:${Config.Versions.lifecycleVersion}")
}
/**
 * This block used for coreLib dependencies
 */
fun DependencyHandler.coreLib() {
    add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Config.Versions.kotlinVersion}")
    add("implementation", "androidx.appcompat:appcompat:${Config.Versions.appcompatVersion}")
    // For loading and tinting drawables on older versions of the platform
    add("implementation", "androidx.appcompat:appcompat-resources:${Config.Versions.appcompatVersion}")
    add("implementation", "com.google.android.material:material:${Config.Versions.materialVersion}")
    add("implementation", "androidx.constraintlayout:constraintlayout:${Config.Versions.constrentLayoutVersion}")
}
/**
 * This block used for coroutines dependencies
 */
fun DependencyHandler.coroutines(){
    add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Config.Versions.coroutinesVersion}")
    add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Config.Versions.coroutinesVersion}")
}
/**
 * This block used for dagger dependencies
 */
fun DependencyHandler.dagger(){
    add("implementation", "com.google.dagger:dagger-android-support:${Config.Versions.daggerVersion}")
    add("kapt", "com.google.dagger:dagger-android-processor:${Config.Versions.daggerVersion}")
    add("kapt", "com.google.dagger:dagger-compiler:${Config.Versions.daggerVersion}")
}
/**
 * This block used for retrofit dependencies
 */
fun DependencyHandler.retrofit(){
    add("implementation", "com.squareup.retrofit2:retrofit:${Config.Versions.retrofitVersion}")
    add("implementation", "com.squareup.retrofit2:converter-gson:${Config.Versions.retrofitVersion}")
    add("implementation", "com.squareup.okhttp3:logging-interceptor:${Config.Versions.okHttpLogerVersion}")
}
