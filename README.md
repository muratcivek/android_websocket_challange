# Android WebSocket Control App

## Proje Hakkında
Bu proje, Android platformunda WebSocket üzerinden veri iletişimi yaparak cihazların kontrol edilmesini sağlayan bir uygulama geliştirmek için tasarlanmıştır. Uygulama, Kotlin diliyle geliştirilmiştir ve WebSocket aracılığıyla JSON verileri gönderip alır. Uygulama Android lifecycle ilkelerine uygun şekilde tasarlanmıştır.

## Özellikler
- WebSocket üzerinden cihazlar ile iletişim kurma
- JSON formatında veri alıp gönderme
- Android fragment yapısı ile tasarlanmış arayüz
- Kullanıcı girişi ve kontrol listesi ekranları
- Lamba ve panel kontrolü

## Geliştirme

Bu uygulama **Kotlin** ile geliştirilmiştir. WebSocket üzerinden veri iletişimi sağlanarak JSON formatında istekler gönderilir ve alınan cevaplarla arayüz güncellenir.

### Gereksinimler
- Android Studio
- Kotlin
- WebSocket kütüphanesi 

![Proje Görseli]((https://github.com/muratcivek/android_websocket_challange/blob/master/gorseller/1.png))
![Proje Görseli]((https://github.com/muratcivek/android_websocket_challange/blob/master/gorseller/2.png))
![Proje Görseli]((https://github.com/muratcivek/android_websocket_challange/blob/master/gorseller/3.png))

### Bağımlılıklar
```gradle

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.activity:activity-ktx:1.7.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.okio:okio:3.2.0")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")
}


