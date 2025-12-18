Job Tracker App (Android)
Overview

The Job Tracker App is a modern Android application designed to help users manage and track their job applications efficiently. Users can create, update, and delete job applications, track application status, and add multiple notes per job. The app uses Firebase Authentication for secure login and Firebase Firestore for real-time data storage and synchronization.

This project is built using Jetpack Compose and follows the MVVM (Model–View–ViewModel) architecture pattern.

Features
Authentication

Email and password authentication using Firebase Authentication

Secure, user-specific data access

Job Management

Add new job applications

Edit existing job details

Delete jobs with confirmation dialog

Track job application status:

Applied

Interview

Offer

Notes Management

Add multiple notes for each job

Notes are associated with individual job applications

Real-time updates using Firestore snapshot listeners

Notes persist across app restarts

User Interface

Fully built using Jetpack Compose

Material 3 design system

Clean, minimal, and responsive UI

Smooth navigation with back buttons

Technologies Used
Category	Tools & Frameworks
Language	Kotlin
UI	Jetpack Compose, Material 3
Architecture	MVVM
State Management	StateFlow, Compose State
Backend	Firebase Authentication, Firebase Firestore
Asynchronous	Kotlin Coroutines, Flow
Navigation	Navigation Compose
Version Control	Git, GitHub
Architecture (MVVM)
UI (Compose Screens)
   ↓
ViewModel
   ↓
Repository
   ↓
Firebase (Authentication + Firestore)


UI Layer: Displays data and handles user interactions

ViewModel: Manages UI state using StateFlow

Repository: Handles all Firebase operations

Firebase: Stores and syncs user data securely

Project Structure
com.example.jobtracker
│
├── data
│   ├── model
│   │   ├── JobApplication
│   │   └── Note
│   └── repository
│       ├── AuthRepository
│       ├── JobRepository
│       └── NoteRepository
│
├── ui
│   ├── screens
│   │   ├── LoginScreen
│   │   ├── HomeScreen
│   │   ├── AddJobScreen
│   │   ├── EditJobScreen
│   │   └── NotesScreen
│   ├── navigation
│   │   ├── NavGraph
│   │   └── Routes
│   └── components
│
├── viewmodel
│   ├── AuthViewModel
│   ├── JobViewModel
│   └── NoteViewModel
│
└── MainActivity

Screenshots

Login Screen
<img width="450" height="982" alt="image" src="https://github.com/user-attachments/assets/3ecec99b-e4a0-4a63-90b1-4deeeadd2915" />
Home Screen (Job List)

Sign Up Screen
<img width="223" height="489" alt="Screenshot 2025-12-17 at 11 38 04 PM" src="https://github.com/user-attachments/assets/144551bd-674e-464f-9ac3-da9e86d7bccd" />


Home Screen
<img width="446" height="992" alt="image" src="https://github.com/user-attachments/assets/8726e2fe-80c1-4594-9bf3-488b7e515b5d" />


Add Job Screen
<img width="446" height="996" alt="image" src="https://github.com/user-attachments/assets/826b3cdb-cb99-4e86-b66c-2ec6c95d207a" />
<img width="229" height="496" alt="Screenshot 2025-12-17 at 10 57 06 PM" src="https://github.com/user-attachments/assets/9e2f0793-e33f-4cd5-b427-6f929f5ff3a2" />

Edit Job Screen
<img width="456" height="1000" alt="image" src="https://github.com/user-attachments/assets/c3a200c8-b7ec-4488-a301-ffa5de46deb2" />

Delete Job Confirmation Dialog
<img width="456" height="986" alt="image" src="https://github.com/user-attachments/assets/6ed41b17-09a8-4faa-a253-e6d7d1d08ef2" />

Notes Screen
<img width="300" height="656" alt="image" src="https://github.com/user-attachments/assets/690133dd-419a-43ce-8524-76cc238278ba" />


Real-Time Data Handling

Firestore snapshot listeners are used for notes

UI updates automatically without manual refresh

All data is persistent and user-specific

Notes and jobs remain available after app restarts

Requirements Fulfilled

Firebase Authentication

Firebase Firestore integration

Full CRUD functionality

MVVM architecture

Jetpack Compose UI

Navigation between multiple screens

Real-time data updates

Persistent storage

Clean and user-friendly interface

All project requirements have been fully implemented.

How to Run the Project

Clone the repository:

git clone https://github.com/shrutikkupekar/Job-Tracking-App.git


Open the project in Android Studio

Sync Gradle files

Connect the project to Firebase and add google-services.json

Run the app on an emulator or physical device

Future Enhancements

Search and filter jobs

Edit and delete notes

Job deadlines and reminders

Dark mode support

Data export functionality

Authors

Shrutik Kupekar

Janya Jaiswal

Indrayani Bhosale

Suyadh Jadhav
