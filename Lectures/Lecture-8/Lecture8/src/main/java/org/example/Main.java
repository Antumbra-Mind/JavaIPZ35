package org.example;

module university.studentManager {
    exports university.student;
}

module university.courseManager {
        requires university.studentManager;
        provides university.registration.CourseProvider with university.courseManager.CourseRegistrationService;
        }
