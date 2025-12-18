# Job Tracker App (Android)

## Overview

The **Job Tracker App** is a modern Android application designed to help users efficiently manage and track their job applications. Users can create, update, and delete job entries, track application status, and add multiple notes for each job.

The app uses **Firebase Authentication** for secure user login and **Firebase Firestore** for real-time data storage and synchronization. It is built entirely with **Jetpack Compose** and follows the **MVVM (Model–View–ViewModel)** architecture pattern.

---

## Features

### Authentication
- Email and password authentication using Firebase Authentication
- Secure, user-specific access to data

### Job Management
- Add new job applications
- Edit existing job details
- Delete jobs with a confirmation dialog
- Track job application status:
   - Applied
   - Interview
   - Offer

### Notes Management
- Add multiple notes for each job application
- Notes are associated with individual jobs
- Real-time updates using Firestore snapshot listeners
- Notes persist across app restarts

### User Interface
- Fully built using Jetpack Compose
- Material 3 design system
- Clean, minimal, and responsive UI
- Smooth navigation with back button support

---

## Technologies Used

| Category | Tools & Frameworks |
|--------|--------------------|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | MVVM |
| State Management | StateFlow, Compose State |
| Backend | Firebase Authentication, Firebase Firestore |
| Asynchronous | Kotlin Coroutines, Flow |
| Navigation | Navigation Compose |
| Version Control | Git, GitHub |

---

## Architecture (MVVM)

UI (Compose Screens)
↓
ViewModel
↓
Repository
↓
Firebase (Authentication + Firestore)


- **UI Layer**: Displays data and handles user interactions
- **ViewModel**: Manages UI state using StateFlow
- **Repository**: Handles all Firebase operations
- **Firebase**: Securely stores and synchronizes user data

---

## Project Structure

com.example.jobtracker
│
├── data
│ ├── model
│ │ ├── JobApplication
│ │ └── Note
│ └── repository
│ ├── AuthRepository
│ ├── JobRepository
│ └── NoteRepository
│
├── ui
│ ├── screens
│ │ ├── LoginScreen
│ │ ├── HomeScreen
│ │ ├── AddJobScreen
│ │ ├── EditJobScreen
│ │ └── NotesScreen
│ ├── navigation
│ │ ├── NavGraph
│ │ └── Routes
│ └── components
│
├── viewmodel
│ ├── AuthViewModel
│ ├── JobViewModel
│ └── NoteViewModel
│
└── MainActivity


---

## Screenshots

### Login Screen
<img width="450" height="982" alt="Login Screen" src="https://github.com/user-attachments/assets/3ecec99b-e4a0-4a63-90b1-4deeeadd2915" />

### Sign Up Screen
<img width="223" height="489" alt="Sign Up Screen" src="https://github.com/user-attachments/assets/144551bd-674e-464f-9ac3-da9e86d7bccd" />

### Home Screen (Job List)
<img width="446" height="992" alt="Home Screen" src="https://github.com/user-attachments/assets/8726e2fe-80c1-4594-9bf3-488b7e515b5d" />

### Add Job Screen
<img width="446" height="996" alt="Add Job Screen" src="https://github.com/user-attachments/assets/826b3cdb-cb99-4e86-b66c-2ec6c95d207a" />
<img width="229" height="496" alt="Add Job Screen Mobile" src="https://github.com/user-attachments/assets/9e2f0793-e33f-4cd5-b427-6f929f5ff3a2" />

### Edit Job Screen
<img width="456" height="1000" alt="Edit Job Screen" src="https://github.com/user-attachments/assets/c3a200c8-b7ec-4488-a301-ffa5de46deb2" />

### Delete Job Confirmation Dialog
<img width="456" height="986" alt="Delete Job Confirmation" src="https://github.com/user-attachments/assets/6ed41b17-09a8-4faa-a253-e6d7d1d08ef2" />

### Notes Screen
<img width="300" height="656" alt="Notes Screen" src="https://github.com/user-attachments/assets/690133dd-419a-43ce-8524-76cc238278ba" />

---

## Real-Time Data Handling

- Firestore snapshot listeners are used for real-time updates
- UI updates automatically without manual refresh
- All data is persistent and user-specific
- Jobs and notes remain available after app restarts

---

## Requirements Fulfilled

- Firebase Authentication
- Firebase Firestore integration
- Full CRUD functionality
- MVVM architecture
- Jetpack Compose UI
- Navigation across multiple screens
- Real-time data updates
- Persistent storage
- Clean and user-friendly interface

All project requirements have been fully implemented.

---

## How to Run the Project

1. Clone the repository:
   https://github.com/shrutikkupekar/Job-Tracking-App.git

2. Open the project in Android Studio
3. Sync Gradle files
4. Connect the project to Firebase and add `google-services.json`
5. Run the app on an emulator or physical device

---

## Future Enhancements

- Search and filter job applications
- Edit and delete notes
- Job deadlines and reminders
- Dark mode support
- Data export functionality

---

## Authors

- Shrutik Kupekar
- Janya Jaiswal
- Indrayani Bhosale
- Suyash Jadhav
