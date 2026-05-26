# 🗓️ Custom Calendar with Jetpack Compose

[![Maven Central](https://img.shields.io/maven-central/v/com.avazpar/calendar-compose)](https://search.maven.org/artifact/com.avazpar/calendar-compose)  
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A Jetpack Compose calendar library with multiple features and customization options.

WIP


## 📦 Installation

Add the dependency to your `build.gradle`:
```groovy
dependencies {
    implementation 'com.avazpar:calendar-compose:1.0.0'
}
```


## 🚀 Basic Usage
````
import com.avazpar.calendar.CalendarView

@Composable
fun MyCalendar() {
    CustomCalendar(
       today = LocalDate.of(2025, 2, 17),
       selected = LocalDate.of(2025, 2, 17),
       data = CalendarData(
           availableSessions = listOf(
               LocalDate.of(2025, 2, 17),
               LocalDate.of(2025, 2, 21),
               LocalDate.of(2025, 2, 28)
               )
            )
     )
}
````

## ✨ Features
- ✅ 100% Jetpack Compose design
- ✅ Full customization support
- ✅ Multi-date selection
- ✅ Special date highlighting
- ✅ Adaptive to different sizes
- ✅ Light/dark theme support


## 🎨 Theming
Wrap the calendar with CalendarTheme to use one of the built-in palettes.
````
import com.avazpar.calendar.theme.CalendarPalette
import com.avazpar.calendar.theme.CalendarTheme

@Composable
fun BlueCalendar() {
    CalendarTheme(
        palette = CalendarPalette.BLUE
    ) {
        MyCalendar()
    }
}
````
Available built-in palettes:

CalendarPalette.BLUE
CalendarPalette.RED
CalendarPalette.GREEN

### Custom Colors
You can also provide your own CalendarColors.
````
import androidx.compose.ui.graphics.Color
import com.avazpar.calendar.theme.CalendarColors
import com.avazpar.calendar.theme.CalendarPalette
import com.avazpar.calendar.theme.CalendarTheme

private val BrandCalendarColors = CalendarColors(
    unavailableBorder = Color(0xFFB8C4D6),
    availableText = Color(0xFF123456),
    activeText = Color(0xFFFFFFFF),
    unavailableText = Color(0xFF7A8899),
    activeBackground = Color(0xFF0D1B2A),
    availableBackground = Color(0xFF2E6FBB),
    activeDot = Color(0xFFFFFFFF),
    availableDot = Color(0xFFEAF2FF),
    availableBorder = Color(0xFF123456)
)

@Composable
fun CustomPaletteCalendar() {
    CalendarTheme(
        palette = CalendarPalette.CUSTOM,
        customColors = BrandCalendarColors
    ) {
        MyCalendar()
    }
}
````

## 📱 Demo App

The repository includes a demo app showing:
1. Basic calendar usage
2. Calendar theme customization

To run the demo:
1. Clone the repository
2. Open the project in Android Studio
3. Run the app module


## 📸 Screenshots
WIP

## 🤝 Contributions
Contributions are welcome. Please open an issue or PR following the code of conduct.


## 📄 License
Copyright 2025 AVazPar

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Made with ❤️ by AVazPar
