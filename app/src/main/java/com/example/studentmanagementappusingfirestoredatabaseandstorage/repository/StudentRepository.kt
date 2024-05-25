package com.example.studentmanagementappusingfirestoredatabaseandstorage.repository

import com.example.studentmanagementappusingfirestoredatabaseandstorage.models.Student
import com.google.firebase.firestore.FirebaseFirestore

class StudentRepository {
    private val firestore = FirebaseFirestore.getInstance()

    fun addStudent(student: Student, onComplete: (Boolean) -> Unit) {
        val docRef = firestore.collection("students").document(student.studentId)
        docRef.set(student)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    fun updateStudent(student: Student, onComplete: (Boolean) -> Unit) {
        val docRef = firestore.collection("students").document(student.studentId)
        docRef.set(student)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    fun deleteStudent(studentId: String, onComplete: (Boolean) -> Unit) {
        val docRef = firestore.collection("students").document(studentId)
        docRef.delete()
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    fun getStudents(onComplete: (List<Student>) -> Unit) {
        firestore.collection("students")
            .get()
            .addOnSuccessListener { documents ->
                val students = documents.map { document -> document.toObject(Student::class.java) }
                onComplete(students)
            }
            .addOnFailureListener {
                onComplete(emptyList())
            }
    }
}