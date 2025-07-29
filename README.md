📱 Firebase Student Records App
An Android app that allows users to add, view, edit, delete, and search student records in real-time using Firebase Realtime Database.
It features a clean, Material Design UI with FirebaseUI for easy database binding and Glide for image handling.
--
🚀 Features
🔄 Real-time fetching, display, and syncing of student data from Firebase

➕ Add student records with name, course, email, and profile image URL

🖊️ Edit existing student details

❌ Delete records with confirmation dialog

🔍 Live search filtering by course name

🖼️ Glide integration for loading profile pictures

📦 Uses FirebaseUI and RecyclerView for seamless data binding
--
📂 Tech Stack
Java

Firebase Realtime Database

FirebaseUI (for RecyclerView)

Glide (image loading)

Material Design Components

DialogPlus (edit dialog)

RecyclerView with custom adapter
--
🧑‍💻 How It Works
1. MainActivity.java
Fetches student data using FirebaseRecyclerOptions

Initializes RecyclerView and sets a custom myadapter

Includes a SearchView to filter by course name

Floating Action Button opens AddData.java

2. myadapter.java
Extends FirebaseRecyclerAdapter to bind Firebase data

Provides UI to edit or delete individual records

Opens a dialog for editing (via DialogPlus)

3. AddData.java
Form to add new student record to Firebase

Fields: name, course, email, profile image URL

Data pushed to Firebase under students/ node
--
✅ Requirements

1-Android Studio

2-Firebase Project with Realtime Database enabled

3-Internet connection
