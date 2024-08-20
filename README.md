# VideoChat

VideoChat is an Android application that enables real-time video communication using Stream SDK, built with Jetpack Compose for the UI and Koin for dependency injection.

## Features

- **Real-time Video Chat**: Uses the Stream SDK for seamless video calls.
- **User Authentication**: Simple username-based login.
- **Join/Leave Calls**: Easily connect to and disconnect from video calls.

## Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Stream SDK**: Provides the core video functionality.
- **Koin**: Manages dependency injection.

## Prerequisites

- **Android Studio**: Flamingo or later.
- **Android SDK**: Minimum version 24.

## Getting Started

### Installation

1. Clone the repository:
```bash
git clone https://github.com/EvgenyPlaksin/VideoChat.git
```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Build and run the project on an Android device or emulator.

### Usage

1. Launch the app.
2. Enter a username in the "Choose a name" field and click **Connect**.
3. The app navigates to the video call screen where you can join or leave calls.
4. To end the call, click on the disconnect button.

## Dependencies

The project utilizes several key dependencies:

- **Stream SDK**: Provides video call capabilities.
- **Jetpack Compose**: Modern UI toolkit.
- **Koin**: Simple and lightweight dependency injection framework.
- **Serialization**: Kotlinx serialization for JSON handling.
- **Material3**: For implementing Material Design components.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
