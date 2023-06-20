object Config {
    val applicationId = "com.app"

    /**
     * This block used for dependency, classpath and kapt version etc
     */
    object Versions {
        val kotlinVersion = "1.3.72"
        val gradleVersion = "4.1.3"
        val lifecycleVersion = "2.3.1"
        val lifecycleExtVersion = "2.2.0"
        val appcompatVersion = "1.3.1"
        val materialVersion = "1.4.0"
        val constrentLayoutVersion = "2.1.0"
        val coroutinesVersion = "1.4.3"
        val daggerVersion = "2.25.4"
        val retrofitVersion="2.9.0"
        val okHttpLogerVersion="4.9.1"
    }

    /**
     * This block used for sdk version control
     */
    object SdkVersions {
        val compile = 33
        val target = 33
        val min = 22
    }

    /**
     * This block used for apk version control
     */
    object ApkVersion {
        val versionCode = 1
        val versionName = "1.0"
    }
}