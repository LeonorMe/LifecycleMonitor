# Premium Android Lifecycle Monitor

A high-quality Android application built in Kotlin using XML Views that monitors and records Activity lifecycle events in real-time across multiple activities.

## Features

- **Real-time Lifecycle Tracking**: Automatically logs `onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`, and `onRestart` for all activities.
- **Visual Recognition**:
    - **Activity Colors**: Each activity has a distinct background color in the log for easy identification.
    - **State Colors**: Lifecycle states are color-coded (e.g., Green for Create, Red for Stop).
- **Session Breaks**: Insert "Session Breaks" to clearly separate different testing sessions.
- **Auto-scroll**: Toggle auto-scroll to keep the latest log entry in view.
- **Shared Log State**: A single, global log list that updates instantly and is visible across all activities.
- **Premium UI**: Built with Material 3, `CoordinatorLayout`, `CardViews`, and subtle elevations for a modern, sleek feel.

## Technical Details

- **Language**: Kotlin
- **Architecture**: Singleton Pattern with `StateFlow` for reactive state management.
- **UI Framework**: XML Views with View Binding.
- **Lifecycle Management**: Custom `BaseLifecycleActivity` that handles boilerplate logging.
- **Dependencies**: Material 3, AndroidX Core, Lifecycle, RecyclerView.

## How to Run

1. Clone the repository.
2. Open in Android Studio (Iguana or later recommended).
3. Build and Run on an emulator or physical device (Target SDK 34).

## Navigation Flow

`MainActivity` -> `SecondActivity` -> `ThirdActivity`

Use the navigation buttons within the "Action Card" to move forward and the system back button (or swipe) to move backward. Observe how the logs update in real-time as you transition between activities.
