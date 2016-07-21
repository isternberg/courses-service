package validation

import model.AdvancedCourse
import model.Course


class CourseValidator {
    fun mayAdd(course: AdvancedCourse): Boolean {
        val prerequisites = course.prerequisites
        return validate(course, prerequisites)
    }

    private fun validate(course: AdvancedCourse, prerequisites: List<Course>): Boolean {
        if (prerequisites.contains(course)){ return false }

        if (!hasAdvancedPrerequisites(course)){ return true }

        for (prerequisite in prerequisites){
            if (prerequisite is AdvancedCourse){
                if (!validate(course, prerequisite.prerequisites)){ return false }
            }
        }
        return true
    }

    private fun hasAdvancedPrerequisites(course: AdvancedCourse) : Boolean {
        return course.prerequisites.filter { it is AdvancedCourse }.count() > 0
    }

    fun mayDelete(course: Course, allCourses: Iterable<Course>) =
            course !in allCourses.
                    filterIsInstance(AdvancedCourse::class.java).
                    flatMap { it.prerequisites }
}